import React from 'react';
import './Parqueadero.css';

export default function Parqueadero({ isOpen, onClose, name, cupoCarro, cupoMoto, cupoBici, tipo }) {
  if (!isOpen) return null;

  return (
    <div className="backdrop">
      <div className="parqueadero">
        <h1>{name}</h1>
        <h2>{tipo}</h2>
        <div className="cupos">
          <ul>
            <li>Cupos <span>Disponibilidad</span><span></span></li>
            <li>Cupos Carro: <span>{cupoCarro}</span>
              <button className="btn" onClick={() => alert("Realizando acción...")}>Apartar</button></li>
            <li>Cupos Moto: <span>{cupoMoto}</span>
              <button className="btn" onClick={() => alert("Realizando acción...")}>Apartar</button></li>
            <li>Cupos Bicicleta: <span>{cupoBici}</span>
              <button className="btn" onClick={() => alert("Realizando acción...")}>Apartar</button></li>
          </ul>
        </div>

        <button className='cerrar' onClick={onClose}>Cerrar</button>
      </div>
    </div>
  );
}
