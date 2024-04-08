import React from 'react';
import { Navbar, Nav, Container } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

/**
 * Navigation component utilizing React Bootstrap for consistent and responsive navigation.
 * Uses `LinkContainer` from `react-router-bootstrap` for SPA routing without page reloads.
 */
const Navigation = () => {
  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Container>
        {/* Brand and Link to home */}
        <LinkContainer to="/">
          <Navbar.Brand style={{ color: 'white' }}>Admin/User Portal</Navbar.Brand>
        </LinkContainer>
        {/* Toggle and collapse for responsive navigation */}
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          {/* Navigation links */}
          <Nav className="me-auto">
            {/* Admin section link */}
            <LinkContainer to="/admin">
              <Nav.Link style={{ color: 'white' }}>Admin</Nav.Link>
            </LinkContainer>
            {/* User section link */}
            <LinkContainer to="/user">
              <Nav.Link style={{ color: 'white' }}>User</Nav.Link>
            </LinkContainer>

          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default Navigation;
