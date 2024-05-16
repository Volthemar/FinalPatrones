import React from "react";
import './Parqueadero.css'

export default function Parqueadero({ isOpen, onClose, name }) {
  if (!isOpen) return null;
  
  return (
    <div className="backdrop">
      <div className="parqueadero">
        <h1>{name}</h1>
        <div className="cupos">
          <ul>
            <li>Cupos Carro: <span >7</span>
              <button className="btn" onClick={() => alert("Realizando acción...")}>Apartar</button></li>
            <li>Cupos Moto: <span >6</span>
              <button className="btn" onClick={() => alert("Realizando acción...")}>Apartar</button></li>
            <li>Cupos Bicicleta: <span>5</span>
              <button className="btn" onClick={() => alert("Realizando acción...")}>Apartar</button></li>
          </ul>
        </div>

        <button className='cerrar' onClick={onClose}>Cerrar</button>
      </div>
    </div>
    
  );
}
