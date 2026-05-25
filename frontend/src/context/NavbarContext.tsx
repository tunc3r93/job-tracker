/**
 * NavbarContext - Global State Management
 *
 * Verwaltet:
 * - Aktuelle Navbar-Position
 * - Benutzer-Daten
 * - Lade- und Fehlerstate
 *
 * Kommt aus dem Backend und wird mit der Datenbank synchronisiert
 */

import React, { createContext, useContext, useState, useEffect } from 'react';
import { NavbarContextType, NavbarPosition, User } from '../types';

const NavbarContext = createContext<NavbarContextType | undefined>(undefined);

interface NavbarProviderProps {
  children: React.ReactNode;
  userId?: string;
}

export const NavbarProvider: React.FC<NavbarProviderProps> = ({ children, userId = 'test-user-1' }) => {
  const [currentUser, setCurrentUser] = useState<User | null>(null);
  const [navbarPosition, setNavbarPositionState] = useState<NavbarPosition>(NavbarPosition.TOP);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  // Beim Component Mount: Benutzer laden und Navbar-Position abrufen
  useEffect(() => {
    const loadUserPreferences = async () => {
      try {
        setIsLoading(true);
        setError(null);

        // TODO: Backend API Call
        // const response = await fetch(`/api/users/${userId}`);
        // const data = await response.json();

        // Für jetzt: Test-Daten
        const testUser: User = {
          id: userId,
          name: 'Test User',
          email: 'test@example.com',
          navbarPosition: NavbarPosition.TOP,
          createdAt: new Date().toISOString(),
        };

        setCurrentUser(testUser);
        setNavbarPositionState(testUser.navbarPosition);
      } catch (err) {
        setError(err instanceof Error ? err.message : 'Fehler beim Laden der Benutzer-Einstellungen');
      } finally {
        setIsLoading(false);
      }
    };

    loadUserPreferences();
  }, [userId]);

  const setNavbarPosition = async (position: NavbarPosition) => {
    try {
      setError(null);
      setNavbarPositionState(position);

      if (currentUser) {
        // TODO: Backend API Call
        // await fetch(`/api/users/${currentUser.id}/navbar-position`, {
        //   method: 'PUT',
        //   headers: { 'Content-Type': 'application/json' },
        //   body: JSON.stringify({ navbarPosition: position }),
        // });

        // Lokal aktualisieren
        setCurrentUser({
          ...currentUser,
          navbarPosition: position,
        });
      }
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Fehler beim Speichern der Position');
      // Position zurücksetzen bei Fehler
      if (currentUser) {
        setNavbarPositionState(currentUser.navbarPosition);
      }
    }
  };

  const value: NavbarContextType = {
    currentUser,
    navbarPosition,
    setNavbarPosition,
    isLoading,
    error,
  };

  return (
    <NavbarContext.Provider value={value}>
      {children}
    </NavbarContext.Provider>
  );
};

/**
 * Hook: useNavbar
 *
 * Verwendung:
 * const { navbarPosition, setNavbarPosition } = useNavbar();
 */
export const useNavbar = (): NavbarContextType => {
  const context = useContext(NavbarContext);
  if (!context) {
    throw new Error('useNavbar muss innerhalb von NavbarProvider verwendet werden');
  }
  return context;
};
