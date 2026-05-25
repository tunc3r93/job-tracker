/**
 * Frontend Type Definitions
 * Zentrale Stelle für alle TypeScript-Typen der Anwendung
 */

// ========================
// Navbar Types
// ========================

export enum NavbarPosition {
  TOP = 'TOP',
  BOTTOM = 'BOTTOM',
  LEFT = 'LEFT',
  RIGHT = 'RIGHT',
}

export interface User {
  id: string;
  name: string;
  email: string;
  navbarPosition: NavbarPosition;
  createdAt: string;
}

export interface CreateUserRequest {
  name: string;
  email: string;
}

export interface UpdateNavbarPositionRequest {
  navbarPosition: NavbarPosition;
}

// ========================
// API Response Types
// ========================

export interface ApiResponse<T> {
  success: boolean;
  data: T;
  message?: string;
  timestamp?: string;
}

export interface ErrorResponse {
  success: false;
  error: string;
  message?: string;
  timestamp?: string;
}

// ========================
// Context State Types
// ========================

export interface NavbarContextType {
  currentUser: User | null;
  navbarPosition: NavbarPosition;
  setNavbarPosition: (position: NavbarPosition) => void;
  isLoading: boolean;
  error: string | null;
}
