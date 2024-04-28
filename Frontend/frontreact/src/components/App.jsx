import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ImageCardLayout from './ImageCardLayout';
import RegisterPage from './RegisterPage'; // Asegúrate de importar tu nueva página aquí
import '../styles/App.css';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<ImageCardLayout />} /> {/* Usa ImageCardLayout aquí */}
        <Route path="/RegisterPage" element={<RegisterPage />} /> {/* Definiendo la nueva ruta */}
      </Routes>
    </Router>
  );
}

export default App;
