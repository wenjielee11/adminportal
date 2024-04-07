import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import "./App.css"
import AdminPortal from './components/adminPortal';
import UserPortal from './components/userPortal';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate replace to="/admin" />} />
        <Route path="/admin" element={<AdminPortal />} />
        <Route path="/user" element={<UserPortal />} />
      </Routes>
    </Router>
  );
};


export default App;
