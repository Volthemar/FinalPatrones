import React from 'react';
import '../styles/WelcomeBanner.css';
import logo from '../assets/logo.png'; // Asegúrate de que este archivo está en la carpeta correcta

function WelcomeBanner() {
  return (
    <div className="WelcomeBanner">
      <img src={logo} alt="Logo" className="Logo" />
      <h1>Bienvenido!</h1>
      <p>Bienvenido de nuevo, ingresa para poder apartar un lugar</p>
    </div>
  );
}

export default WelcomeBanner;