const API_BASE_URL = 'http://localhost:8080/admin'; 

const apiService = {
  addUser: async (userData) => {
    const response = await fetch(`${API_BASE_URL}/add`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        "Access-Control-Allow-Origin": 'http://localhost:3000',
      },
      body: JSON.stringify(userData),
    });
    const data = await response.json();
    if (!response.ok) {
      throw new Error(`Error adding user ${userData.lastName} ${userData.firstName}:\n${response.body}`);
    }
    return { message: 'User successfully added', data };
  },

  editUser: async (userData) => {
   
    const response = await fetch(`${API_BASE_URL}/edit`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        "Access-Control-Allow-Origin": 'http://localhost:3000',
      },
      body: JSON.stringify(userData),
    });
    if (response.status !==418) {
      throw new Error(`Error editing user ${userData.lastName} ${userData.firstName} ${response.body}`);
    }
    const data = await response.json();
    return { message: 'User successfully edited', data };
  },

  deleteUser: async (userData) => {
    const response = await fetch(`${API_BASE_URL}/delete`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        "Access-Control-Allow-Origin": 'http://localhost:3000',
      },
      body: JSON.stringify(userData),
    });
    if (!response.ok) {
      throw new Error(`User ${userData.lastName} ${userData.firstName} cannot be deleted.`);
    }
    return { message: `User ${userData.lastName} ${userData.firstName} successfully deleted` };
  },

  getActiveUsers: async () => {
    const response = await fetch(`${API_BASE_URL}`, {
      method: 'GET',
    });
    if (!response.ok) {
      throw new Error('Error fetching active users');
    }
    const data = await response.json();
    return { message: 'Active users fetched', data };
  },
};

export default apiService;
