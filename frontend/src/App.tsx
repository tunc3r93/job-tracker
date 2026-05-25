/**
 * App Component - Root des Frontend
 *
 * Hier werden folgende Dinge zusammengebracht:
 * - NavbarProvider (Global State)
 * - Navbar Komponente
 * - Main Content Area
 */

import React from 'react';
import { NavbarProvider, useNavbar } from './context/NavbarContext';
import Navbar from './components/Navbar';
import './App.css';

const AppContent: React.FC = () => {
  const { navbarPosition, isLoading, error } = useNavbar();

  if (isLoading) {
    return <div className="loading">Benutzer-Einstellungen werden geladen...</div>;
  }

  if (error) {
    return <div className="error">Fehler: {error}</div>;
  }

  return (
    <div className="app">
      <Navbar position={navbarPosition} />
      <main className="app__main">
        <h2>Willkommen zu Job Tracker!</h2>
        <p>Navbar-Position: {navbarPosition}</p>
        <p>Gehe zu Settings um die Position zu ändern.</p>
      </main>
    </div>
  );
};

export const App: React.FC = () => {
  return (
    <NavbarProvider userId="test-user-1">
      <AppContent />
    </NavbarProvider>
  );
};

export default App;
