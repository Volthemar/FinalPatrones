import React, { useState } from 'react';
import DatePicker from 'react-datepicker';
import { toast, ToastContainer } from 'react-toastify';
import { format } from 'date-fns';
import 'react-toastify/dist/ReactToastify.css';
import 'react-datepicker/dist/react-datepicker.css';
import './Parqueadero.css';

export default function Parqueadero({ isOpen, onClose, idParqueadero, name, cupoCarro, cupoMoto, cupoBici, tipo }) {
  const [selectedVehicle, setSelectedVehicle] = useState('');
  const [selectedDateTime, setSelectedDateTime] = useState(null);
  const [reservationHours, setReservationHours] = useState(1);
  const [isButtonActive, setIsButtonActive] = useState(false);
  const [paymentInfo, setPaymentInfo] = useState(null);

  const tarjetaId = localStorage.getItem('tarjetaId');
  const usuarioId = localStorage.getItem('usuarioId');

  const handleVehicleChange = (e) => {
    setSelectedVehicle(e.target.value);
  };

  const handleDateTimeChange = (date) => {
    setSelectedDateTime(date);
  };

  const handleHoursChange = (e) => {
    setReservationHours(e.target.value);
  };

  const formatDateTime = (date) => {
    return format(date, 'yyyy-MM-dd HH:00:00');
  };

  const handleSubmit = async () => {
    if (!selectedVehicle || !selectedDateTime) {
      toast.error('Por favor, seleccione el tipo de vehículo y la fecha/hora de llegada.');
      return;
    }

    const formattedDateTime = formatDateTime(selectedDateTime);

    try {
      const response = await fetch('http://localhost:3241/reservarCupo', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          tarjetaId: parseInt(tarjetaId, 10),
          usuarioId: parseInt(usuarioId, 10),
          parqueaderoId: parseInt(idParqueadero, 10),
          vehiculoId: parseInt(selectedVehicle, 10),
          hora_llegada: formattedDateTime,
          horas: parseInt(reservationHours, 10),
        }),
      });

      const data = await response.json();

      if (response.ok) {
        setIsButtonActive(true);
        setPaymentInfo(data.data.codigo);
        toast.success('Cupo reservado con éxito.');
      } else {
        toast.error(data.msg || 'No se puede reservar');
      }
    } catch (error) {
      toast.error('Error en la reserva, intente nuevamente.');
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
        <ToastContainer />
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
          disabled={!selectedVehicle || !selectedDateTime}
        >
          Reservar
        </button>
        <button className='cerrar' onClick={onClose}>Cerrar</button>
        {paymentInfo && (
          <div className="payment-info">
            <p>Código de reserva: {paymentInfo}</p>
          </div>
        )}
      </div>
    </div>
  );
}
