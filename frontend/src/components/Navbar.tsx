/**
 * Navbar Component
 *
 * Flexible Navbar, die sich an verschiedene Positionen anpassen kann:
 * - TOP (horizontal, oben)
 * - BOTTOM (horizontal, unten)
 * - LEFT (vertikal, links)
 * - RIGHT (vertikal, rechts)
 *
 * Die Position kommt aus dem Context und wird in der Datenbank gespeichert
 */

import React from 'react';
import { NavbarPosition } from '../types';
import '../styles/Navbar.css';

interface NavbarProps {
  position: NavbarPosition;
}

export const Navbar: React.FC<NavbarProps> = ({ position }) => {
  const getNavbarClass = (): string => {
    switch (position) {
      case NavbarPosition.TOP:
        return 'navbar navbar--top';
      case NavbarPosition.BOTTOM:
        return 'navbar navbar--bottom';
      case NavbarPosition.LEFT:
        return 'navbar navbar--left';
      case NavbarPosition.RIGHT:
        return 'navbar navbar--right';
      default:
        return 'navbar navbar--top';
    }
  };

  return (
    <nav className={getNavbarClass()}>
      <div className="navbar__container">
        <div className="navbar__logo">
          <h1>Job Tracker</h1>
        </div>

        <ul className="navbar__menu">
          <li className="navbar__item">
            <a href="/" className="navbar__link">Home</a>
          </li>
          <li className="navbar__item">
            <a href="/jobs" className="navbar__link">Jobs</a>
          </li>
          <li className="navbar__item">
            <a href="/settings" className="navbar__link">Settings</a>
          </li>
          <li className="navbar__item">
            <a href="/profile" className="navbar__link">Profile</a>
          </li>
        </ul>
      </div>
    </nav>
  );
};

export default Navbar;
