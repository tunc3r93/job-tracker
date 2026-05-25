# React Frontend Architecture

## Overview
The frontend is built with React 18, TypeScript, and Context API for state management.

## Components
- **Navbar**: Main navigation component supporting 4 positions (TOP, BOTTOM, LEFT, RIGHT)
- **NavbarSettings**: Settings panel for changing navbar position
- **HomePage**: Landing page with feature overview
- **SettingsPage**: Settings page containing NavbarSettings

## State Management
Uses Context API with custom hooks:
- `NavbarContext`: Global state for navbar position
- `useNavbar`: Hook to access navbar state and update methods

## Services
- `UserApi`: User management API calls
- `NavbarPositionApi`: Navbar position API calls

## Styling
BEM CSS pattern with responsive design and mobile-first approach.
