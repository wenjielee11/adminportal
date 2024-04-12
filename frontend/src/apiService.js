const API_BASE_URL = 'http://ec2-54-251-129-250.ap-southeast-1.compute.amazonaws.com:8000/admin'; 

const apiService = {
  // Adds a user with provided data.
  addUser: async (userData) => {
    const response = await fetch(`${API_BASE_URL}/add`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(userData),
    });
    if (!response.ok) {
      const errorData = await response.json(); 
      throw new Error(`Error adding user ${userData.lastName} ${userData.firstName}: ${errorData.message}`);
    }
    const data = await response.json(); 
    return { message: 'User successfully added', data };
  },

  // Edits a user with provided data.
  editUser: async (userData) => {
    const response = await fetch(`${API_BASE_URL}/edit`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(userData),
    });
    if (response.status!== 418) { // Teapot
      const errorData = await response.json(); 
      throw new Error(`Error editing user ${userData.lastName} ${userData.firstName}: ${errorData.message}`);
    }
    const data = await response.json(); 
    return { message: 'User successfully edited', data };
  },

  // Deletes a user with provided data.
  deleteUser: async (userData) => {
    const response = await fetch(`${API_BASE_URL}/delete`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(userData),
    });
    if (!response.ok) {
      const errorData = await response.json();
      throw new Error(`Error deleting user ${userData.lastName} ${userData.firstName}: ${errorData.message}`);
    }
    const data = await response.json(); 
    return { message: `User ${userData.lastName} ${userData.firstName} successfully deleted`, data };
  },

  // Fetches a list of active users.
  getActiveUsers: async () => {
    const response = await fetch(`${API_BASE_URL}`, {
      method: 'GET',
    });
    if (!response.ok) {
      const errorData = await response.json(); 
      throw new Error(`Error getting users: ${errorData.message}`);
    }
    const data = await response.json(); 
    return { message: 'Active users fetched', data };
  },
};

export default apiService;
