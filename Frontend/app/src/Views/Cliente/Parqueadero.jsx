import React, { useState } from 'react';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import './Parqueadero.css';

export default function Parqueadero({ isOpen, onClose, idParqueadero, name, cupoCarro, cupoMoto, cupoBici, tipo }) {
  const [selectedVehicle, setSelectedVehicle] = useState('');
  const [selectedDateTime, setSelectedDateTime] = useState(null);
  const [reservationHours, setReservationHours] = useState(1);
  const [isButtonActive, setIsButtonActive] = useState(false);
  const [paymentInfo, setPaymentInfo] = useState(null);

  const handleVehicleChange = (e) => {
    setSelectedVehicle(e.target.value);
  };

  const handleDateTimeChange = (date) => {
    setSelectedDateTime(date);
  };

  const handleHoursChange = (e) => {
    setReservationHours(e.target.value);
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
        hours: reservationHours,
      }),
    });

    const data = await response.json();

    if (data.status === 'yes') {
      setIsButtonActive(true);
      const tariffResponse = await fetch('/tarifaParqueaderoVehiculo', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          parqueadero_fk: idParqueadero,
          vehiculo_fk: selectedVehicle,
          horas: reservationHours,
        }),
      });

      const tariffData = await tariffResponse.json();
      setPaymentInfo(tariffData.data.Precio);
    } else {
      alert('No se puede reservar');
    }
  };

  const filterPassedTime = (time) => {
    const currentDate = new Date();
    const selectedDate = new Date(time);

    return currentDate.getTime() < selectedDate.getTime();
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
            <h2>Seleccione la hora de llegada</h2>
            <DatePicker
              selected={selectedDateTime}
              onChange={handleDateTimeChange}
              showTimeSelect
              dateFormat="Pp"
              minDate={new Date()}
              filterTime={filterPassedTime}
              timeIntervals={60}
              placeholderText="Seleccione fecha y hora"
              className="date-picker"
            />
          </div>
          {selectedDateTime && (
            <div className="Select-Hours">
              <label htmlFor="hours">Horas a reservar:</label>
              <input
                type="number"
                id="hours"
                value={reservationHours}
                min="1"
                onChange={handleHoursChange}
              />
            </div>
          )}
        </div>
        <button
          className='reservar'
          onClick={handleSubmit}
          disabled={!isButtonActive}
        >
          Reservar
        </button>
        <button className='cerrar' onClick={onClose}>Cerrar</button>
        {paymentInfo && (
          <div className="payment-info">
            <p>Debe pagar tanto: {paymentInfo} $$$$</p>
            <div>
              <label>Seleccionar tarjeta de crédito:</label>
              <select>
                <option value="1">Tarjeta de configuración</option>
                {/* Add more options as needed */}
              </select>
            </div>
            <button className='pagar'>PAGAR</button>
          </div>
        )}
      </div>
    </div>
  );
}
