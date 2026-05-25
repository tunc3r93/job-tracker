/**
 * NavbarSettings Component
 *
 * Ermöglicht Benutzern, die Navbar-Position zu ändern
 * mit Live-Vorschau der verschiedenen Positionen
 */

import React, { useState } from 'react';
import { useNavbar } from '../context/NavbarContext';
import { NavbarPosition } from '../types';
import '../styles/NavbarSettings.css';

export const NavbarSettings: React.FC = () => {
  const { position, updatePosition } = useNavbar();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const positions = [
    { value: NavbarPosition.TOP, label: 'Top', description: 'Horizontal oben' },
    { value: NavbarPosition.BOTTOM, label: 'Bottom', description: 'Horizontal unten' },
    { value: NavbarPosition.LEFT, label: 'Left', description: 'Vertikal links' },
    { value: NavbarPosition.RIGHT, label: 'Right', description: 'Vertikal rechts' }
  ];

  const handlePositionChange = async (newPosition: NavbarPosition) => {
    setLoading(true);
    setError(null);

    try {
      await updatePosition(newPosition);
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Ein Fehler ist aufgetreten');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="navbar-settings">
      <h2>Navbar Position</h2>

      <div className="navbar-settings__options">
        {positions.map(pos => (
          <button
            key={pos.value}
            className={`navbar-settings__button ${position === pos.value ? 'navbar-settings__button--active' : ''}`}
            onClick={() => handlePositionChange(pos.value)}
            disabled={loading}
          >
            <span className="navbar-settings__label">{pos.label}</span>
            <span className="navbar-settings__description">{pos.description}</span>
          </button>
        ))}
      </div>

      {error && <div className="navbar-settings__error">{error}</div>}
      {loading && <div className="navbar-settings__loading">Wird gespeichert...</div>}
    </div>
  );
};
