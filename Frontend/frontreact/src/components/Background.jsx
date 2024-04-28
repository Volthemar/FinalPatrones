import React from 'react';
import '../styles/Background.css';
import cardImage from '../assets/card.png'; // Asegúrate de que este archivo está en la carpeta correcta

function Background({ children }) { // Aceptando children para renderizar dentro
  return (
    <div className="Background">
      {children} {/* Renderizando children dentro del background */}
    </div>
  );
}

export default Background;
