import React, { useState } from 'react';
import './NuevoUsuario.css';
import Persona from '../../assets/Persona.svg';
import { Map, Marker } from "pigeon-maps";
import Parqueadero from './Parqueadero';
import Historial from '../../Components/Historial/Historial';

function NuevoUsuario() {
  const [hue, setHue] = useState(0);
  const color = `hsl(${hue % 350}deg, 100%, 50%)`;
  const [isParqueaderoOpen, setParqueaderoOpen] = useState(false);
  const [NombreParqueadero, setNombreParqueadero] = useState('');
  const [selectedCity, setSelectedCity] = useState('');
  const [mapKey, setMapKey] = useState(0); // Estado para forzar la recarga del mapa
  const nombre = localStorage.getItem('userName');

  const userData = {
    name: nombre,
    paymentMethod: "VISA **** 4029",
    paymentNumber: "310 5544 391"
  };

  const cityCoordinates = {
    bogota: [4.7110, -74.0721],
    medellin: [6.2442, -75.5812],
    cali: [3.4516, -76.5320]
  };

  const handleCityChange = (e) => {
    setSelectedCity(e.target.value);
    setMapKey(prevKey => prevKey + 1); // Cambiar la clave del mapa para forzar la recarga
  };

  const parqueaderos = {
    "parqueadero1": {
      "nombre": "Cuatro Parques",
      "altitud": 4.7110,
      "latitud": -74.0721
    },
    "parqueadero2": {
      "nombre": "Tres Parques",
      "altitud": 6.2442,
      "latitud": -75.5812
    },
    "parqueadero3": {
      "nombre": "Tres Parques",
      "altitud": 3.4516,
      "latitud": -76.5320
    }
  };

  return (
    <div className="card-user">
      <div className='container'>
        <Parqueadero isOpen={isParqueaderoOpen} onClose={() => setParqueaderoOpen(false)} name={NombreParqueadero} />
        <header>
          <div className='imagen'>
            <img src={Persona} alt="Perfil" className='imagen-usuario' />
          </div>
          <div className="perfil-usuario">
            <h1 className='nombreUsuario'>Hola, {userData.name}!</h1>
            <hr />
            <div className='datos-usuario'>
              <p className='datos-usuario-p'>Medio de pago: {userData.paymentMethod}</p>
              <p className='datos-usuario-p'>Número: {userData.paymentNumber}</p>
            </div>
            <div className='botones'>
              <button className='Configuracion'>Configuración</button>
              <button className='Salir'>Salir</button>
            </div>
          </div>
        </header>

        <Historial />
        <div className='contenedor-mapa'>
          <div className='city-select'>
            <label htmlFor="city">Selecciona una ciudad:</label>
            <select id="city" value={selectedCity} onChange={handleCityChange}>
              <option value="">Seleccione una ciudad</option>
              <option value="bogota">Bogotá</option>
              <option value="medellin">Medellín</option>
              <option value="cali">Cali</option>
            </select>
          </div>
          {selectedCity && (
            <div className='mapa'>
              <Map key={mapKey} defaultCenter={cityCoordinates[selectedCity]} defaultZoom={18} minHeight={300}>
                {Object.keys(parqueaderos).map(key => (
                  <Marker
                    key={key}
                    width={50}
                    anchor={[parqueaderos[key].altitud, parqueaderos[key].latitud]}
                    color={color}
                    onClick={() => {
                      setParqueaderoOpen(true);
                      setNombreParqueadero(parqueaderos[key].nombre);
                    }}
                  />
                ))}
              </Map>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default NuevoUsuario;
