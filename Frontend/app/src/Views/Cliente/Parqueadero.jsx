import React, { useState } from 'react';
import './Parqueadero.css';

export default function Parqueadero({ isOpen, onClose, name, cupoCarro, cupoMoto, cupoBici, tipo }) {
  const [selectedVehicle, setSelectedVehicle] = useState('');
  const [selectedDateTime, setSelectedDateTime] = useState('');
  const [isButtonActive, setIsButtonActive] = useState(false);

  const handleVehicleChange = (e) => {
    setSelectedVehicle(e.target.value);
  };

  const handleDateTimeChange = (e) => {
    setSelectedDateTime(e.target.value);
  };

  const handleSubmit = async () => {
    const response = await fetch('/api/reserve', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        name,
        vehicleType: selectedVehicle,
        dateTime: selectedDateTime,
      }),
    });

    const data = await response.json();

    if (data.status === 'yes') {
      setIsButtonActive(true);
    } else {
      alert('No se puede reservar');
    }
  };

  if (!isOpen) return null;

  return (
    <div className="backdrop">
      <div className="parqueadero">
        <h1>{name}</h1>
        <h2>{tipo}</h2>
        <div className="cupos">
          <div className="Select-Parqueadero">
            <select id="vehicle" value={selectedVehicle} onChange={handleVehicleChange}>
              <option value="">Seleccione un tipo de vehículo</option>
              <option value="1">Moto</option>
              <option value="2">Carro</option>
              <option value="3">Bicicleta</option>
            </select>
          </div>
          <div className="Select-DateTime">
            <input
              type="datetime-local"
              id="datetime"
              value={selectedDateTime}
              onChange={handleDateTimeChange}
            />
          </div>
        </div>
        <button
          className='reservar'
          onClick={handleSubmit}
          disabled={!isButtonActive}
        >
          Reservar
        </button>
        <button className='cerrar' onClick={onClose}>Cerrar</button>
      </div>
    </div>
  );
}
