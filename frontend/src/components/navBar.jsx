import React from 'react';
import { Navbar, Nav, Container } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

const Navigation = () => {
  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Container>
        <LinkContainer to="/">
          <Navbar.Brand style={{ color: 'white' }}>Admin/User Portal</Navbar.Brand>
        </LinkContainer>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <LinkContainer to="/admin">
              <Nav.Link style={{ color: 'white' }}>Admin</Nav.Link>
            </LinkContainer>
            <LinkContainer to="/user">
              <Nav.Link style={{ color: 'white' }}>User</Nav.Link>
            </LinkContainer>
            {/* Add more navigation links as needed */}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default Navigation;

