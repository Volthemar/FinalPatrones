import React from 'react';
import LoginPage from './LoginPage'; // Asegúrate de importar tu LoginPage aquí
import '../styles/ImageCardLayout.css';
import logo from '../assets/card.png';

function ImageCardLayout() {
  return (
    <div className="ImageCardLayout">
      <div className="ImageContainer">
        <img src={logo} alt="Descriptive Alt Text" /> {/* Asegúrate de actualizar el src */}
      </div>
      <LoginPage />
    </div>
  );
}

export default ImageCardLayout;
