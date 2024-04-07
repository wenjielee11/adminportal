import React, { useState, useEffect } from 'react';
import { Table } from 'react-bootstrap';
import apiService from '../apiService'; // Adjust the path as necessary
import Navigation from './navBar';

const UserPortal = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        // Fetch active users when the component mounts
        apiService.getActiveUsers()
            .then(response => {
                // Assume response contains the array of active users
                setUsers(response.data); // Adjust depending on the actual structure of your response
            })
            .catch(error => {
                console.error("Failed to fetch users:", error);
            });
    }, []); // Empty dependency array to run only once on mount

    const [webSocket, setWebSocket] = useState(null);

    useEffect(() => {
        // Connect to WebSocket
        const ws = new WebSocket('ws://localhost:8080/ws-endpoint'); // Replace with your actual WebSocket endpoint
        setWebSocket(ws);

        ws.onopen = () => {
            console.log('WebSocket connection established');
        };

        ws.onmessage = (message) => {
            // Assuming the message contains the updated list of users
            const data = JSON.parse(message.data);
            setUsers(data); // Update the state with the new users
        };

        ws.onclose = () => {
            console.log('WebSocket connection closed');
        };

        // Clean up on unmount
        return () => {
            ws.close();
        };
    }, []);
    
    return (
        <>
        <Navigation/>
        <Table striped bordered hover style={{margin:'30px'}}>
            <thead>
                <tr>    
                    <th>id</th>
                    <th>Timestamp (Last updated)</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Shape/Color</th>
                </tr>
            </thead>
            <tbody>
                {users.map((user, index) => (
                    <tr key={index}>
                        <td>{user.id}</td>
                        <td>{user.updatedAt}</td>
                        <td>{user.firstName}</td>
                        <td>{user.lastName}</td>
                        <td>
                            <ShapeColor shape={user.shape} color={user.color} />
                        </td>
                    </tr>
                ))}
            </tbody>
        </Table>
        </>
    );
};

const ShapeColor = ({ shape, color }) => {
    const svgStyles = {
      fill: color,
      stroke: color
    };
  
    switch (shape) {
      case 'Circle':
        return (
          <svg width="50" height="50" viewBox="0 0 50 50">
            <circle cx="25" cy="25" r="20" style={svgStyles} />
          </svg>
        );
      case 'Triangle':
        return (
          <svg width="50" height="50" viewBox="0 0 50 50">
            <polygon points="25,5 45,45 5,45" style={svgStyles} />
          </svg>
        );
      case 'Square':
        return (
          <svg width="50" height="50" viewBox="0 0 50 50">
            <rect width="40" height="40" x="5" y="5" style={svgStyles} />
          </svg>
        );
      default:
        return null; // or a default shape if you have one
    }
  };

export default UserPortal;
