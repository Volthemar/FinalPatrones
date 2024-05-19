import React, { useState } from 'react';
import './Parqueadero.css';

function Horario({ isOpen, onClose, name }) {
  const [arrivalTime, setArrivalTime] = useState('');
  const [departureTime, setDepartureTime] = useState('');
  const currentDate = new Date().toLocaleDateString();

  const creditCards = [
    { type: 'Visa', lastFour: '1234' },
    { type: 'Visa', lastFour: '5678' },
    { type: 'Visa', lastFour: '9012' },
  ];

  if (!isOpen) return null;

  return (
    <div className="backdrop">
      <div className="parqueadero">
        <h1>{name}</h1>
        <p>Hoy es: {currentDate}</p>
        <div>
          <label htmlFor="arrivalTime">Hora de llegada: </label>
          <input
            type="time"
            id="arrivalTime"
            value={arrivalTime}
            onChange={(e) => setArrivalTime(e.target.value)}
          />
          <label htmlFor="departureTime">Hora de salida: </label>
          <input
            type="time"
            id="departureTime"
            value={departureTime}
            onChange={(e) => setDepartureTime(e.target.value)}
          />
        </div>
        <div className="cupos">
          <ul>
            <li>Cupos Carro: <span className="spaan">7</span>
              <button className="btn" onClick={() => alert("Realizando acción...")}>Apartar</button></li>
            <li>Cupos Moto: <span className="spaan">6</span>
              <button className="btn" onClick={() => alert("Realizando acción...")}>Apartar</button></li>
            <li>Cupos Bicicleta: <span className="spaan">5</span>
              <button className="btn" onClick={() => alert("Realizando acción...")}>Apartar</button></li>
          </ul>
        </div>
        <div className="credit-card-selection">
          <ul>
            {creditCards.map((card, index) => (
              <li key={index}>
                <input type="radio" name="creditCard" id={`card${index}`} />
                <label htmlFor={`card${index}`}>{card.type} ****{card.lastFour}</label>
              </li>
            ))}
          </ul>
        </div>
        <button className='cerrar' onClick={onClose}>Cerrar</button>
      </div>
    </div>
  );
}

export default  Horario;