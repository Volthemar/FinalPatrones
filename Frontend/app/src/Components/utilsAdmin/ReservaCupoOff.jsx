import React, { useState } from 'react';
import './ReservaCupoOff.css';

export default function ReservaCupoOff({ isOpen, onClose }) {
    const nombre = localStorage.getItem('userName');
    const [selectedVehicle, setSelectedVehicle] = useState('');
    const handleVehicleChange = (e) => {
        setSelectedVehicle(e.target.value);
    };

    if (!isOpen) return null;

    return (
        <div className="reservaCupoOff-backdrop">
            <div className="reservaCupoOff-container">
                <h1>Crear ticket</h1>
                <h2>{nombre}</h2>
                <div className="reservaCupoOff-cupos">
                    <select id="vehicle" value={selectedVehicle} onChange={handleVehicleChange}>
                        <option value="">Seleccione un tipo de vehículo</option>
                        <option value="1">Moto</option>
                        <option value="2">Carro</option>
                        <option value="3">Bicicleta</option>
                    </select>
                </div>
                <div className="reservaCupoOff-form">
                    <label htmlFor="reservaCupoOff-nombre">Nombre de quien reserva:</label>
                    <input type="text" id="reservaCupoOff-nombre" name="nombreReserva" />
                    <label htmlFor="reservaCupoOff-horas">Horas:</label>
                    <input type="number" id="reservaCupoOff-horas" name="horasReserva" />
                </div>
                <button className='reservaCupoOff-continuar' onClick={() => alert(`Reservado para: ${selectedVehicle}`)}>Continuar</button>
                <button className='reservaCupoOff-cerrar' onClick={onClose}>Cerrar</button>
            </div>
        </div>
    );
}
