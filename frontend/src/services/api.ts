const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api';

export const UserApi = {
  async getUser(id: string) {
    const response = await fetch(`${API_BASE_URL}/users/${id}`);
    if (!response.ok) throw new Error('Failed to get user');
    return response.json();
  },

  async createTestUser() {
    const response = await fetch(`${API_BASE_URL}/users`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email: 'test@example.com', name: 'Test User' })
    });
    if (!response.ok) throw new Error('Failed to create user');
    return response.json();
  }
};

export const NavbarPositionApi = {
  async updatePosition(userId: string, position: string) {
    const response = await fetch(`${API_BASE_URL}/users/${userId}/navbar-position`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ position })
    });
    if (!response.ok) throw new Error('Failed to update position');
    return response.json();
  }
};
