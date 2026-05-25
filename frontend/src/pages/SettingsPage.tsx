import React from 'react';
import { NavbarSettings } from '../components/NavbarSettings';
import '../styles/SettingsPage.css';

export const SettingsPage: React.FC = () => {
  return (
    <div className="settings-page">
      <h1>Einstellungen</h1>
      <NavbarSettings />
    </div>
  );
};
