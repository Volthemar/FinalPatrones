import React, { useState } from 'react';
import './NuevoUsuario.css'
import Persona from '../../assets/Persona.svg'
import { Map, Marker } from "pigeon-maps"
import Parqueadero from './Parqueadero';
import Historial from '../../Components/Historial/Historial';

function NuevoUsuario() {
  const [hue, setHue] = useState(0)
  const color = `hsl(${hue % 350}deg, 100%, 50%)`
  const [isParqueaderoOpen, setParqueaderoOpen] = useState(false);
  const [NombreParqueadero, setNombreParqueadero] = useState('');
  const nombre = localStorage.getItem('userName');

  const userData = {
    name: nombre,
    paymentMethod: "VISA **** 4029",
    paymentNumber: "310 5544 391"
  };

  return (
    <div className="card">
      <header>
        <img src={Persona} alt="Perfil" id='imagen-usuario' />
        <div className="perfil-usuario">
          <h1>Hola, {userData.name}!</h1>
          <hr/>
          <p>Medio de pago: {userData.paymentMethod}</p>
          <p>Número: {userData.paymentNumber}</p>
          <div className='botones'>
            <button className='Configuracion'>Configuracion</button>
            <button className='Salir'>Salir</button>
          </div>
        </div>
      </header>
      <Historial/>
      <Map height={300} defaultCenter={[4.630423, -74.066546]} defaultZoom={18}>
        <Marker
          width={50}
          anchor={[4.630423, -74.066546]}
          color={color}
          onClick={() => {
            setParqueaderoOpen(true);
            setNombreParqueadero('Cuatro Parques'); // Esto asume que puedes obtener el nombre del evento
          }
        }
        />
      </Map>
      <Parqueadero isOpen={isParqueaderoOpen} onClose={() => setParqueaderoOpen(false)} name={NombreParqueadero}/>
    </div>
  );
}

export default NuevoUsuario;