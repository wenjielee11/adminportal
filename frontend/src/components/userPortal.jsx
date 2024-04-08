import React, { useState, useEffect } from 'react';
import { Table } from 'react-bootstrap';
import apiService from '../apiService';
import Navigation from './navBar';
import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';

/**
 * UserPortal component displays a list of users and updates in real-time using WebSockets.
 */
const UserPortal = () => {
  const [users, setUsers] = useState([]);

  // Fetches initial list of users when the component mounts.
  useEffect(() => {
    apiService.getActiveUsers()
      .then(response => setUsers(response.data))
      .catch(error => console.error("Failed to fetch users:", error));
  }, []);

  const [isConnected, setIsConnected] = useState(false);
  const [stompClient, setStompClient] = useState(null);

  // Establishes WebSocket connection and sets up subscription to user updates.
  useEffect(() => {
    if (!stompClient) {
      const socket = new SockJS('http://localhost:8080/get-users');
      const client = Stomp.over(socket);

      client.connect({}, frame => {
        setIsConnected(true);
        client.subscribe('/topic/userUpdate', message => {
          setUsers(JSON.parse(message.body));
        });
      }, error => setIsConnected(false));

      setStompClient(client);
    }

    // Disconnects on cleanup.
    return () => {
      if (stompClient && isConnected) {
        stompClient.disconnect();
        setIsConnected(false);
      }
    };
  }, [stompClient]);

  // Formats timestamp for display.
  const getDate = (timestamp) => new Date(timestamp).toLocaleString('en-US', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit'
  });

  return (
    <>
      <Navigation />
      <Table striped bordered hover style={{ margin: '30px' }}>
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
              <td>{getDate(user.updatedAt)}</td>
              <td>{user.firstName}</td>
              <td>{user.lastName}</td>
              <td><ShapeColor shape={user.shape} color={user.color} /></td>
            </tr>
          ))}
        </tbody>
      </Table>
    </>
  );
};

/**
 * Renders a shape with a specific color.
 * @param {Object} props shape and color properties
 */
const ShapeColor = ({ shape, color }) => {
  const svgStyles = { fill: color, stroke: color };

  switch (shape) {
    case 'Circle':
      return <svg width="50" height="50" viewBox="0 0 50 50"><circle cx="25" cy="25" r="20" style={svgStyles} /></svg>;
    case 'Triangle':
      return <svg width="50" height="50" viewBox="0 0 50 50"><polygon points="25,5 45,45 5,45" style={svgStyles} /></svg>;
    case 'Square':
      return <svg width="50" height="50" viewBox="0 0 50 50"><rect width="40" height="40" x="5" y="5" style={svgStyles} /></svg>;
    default:
      return null; // Handle unexpected shapes gracefully
  }
};

export default UserPortal;
