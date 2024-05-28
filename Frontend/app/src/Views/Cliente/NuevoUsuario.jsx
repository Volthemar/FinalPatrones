import React, { useState, useEffect } from 'react';
import './NuevoUsuario.css';
import Persona from '../../assets/Persona.svg';
import { Map, Marker } from "pigeon-maps";
import Parqueadero from './Parqueadero';
import Historial from '../../Components/Historial/Historial';

function NuevoUsuario() {
  const [hue, setHue] = useState(0);
  const [isParqueaderoOpen, setParqueaderoOpen] = useState(false);
  const [selectedParqueadero, setSelectedParqueadero] = useState({});
  const [selectedCity, setSelectedCity] = useState('');
  const [mapKey, setMapKey] = useState(0); // Estado para forzar la recarga del mapa
  const [cities, setCities] = useState([]);
  const [cityCoordinates, setCityCoordinates] = useState({});
  const [parqueaderos, setParqueaderos] = useState([]);
  const nombre = localStorage.getItem('userName');

  const userData = {
    name: nombre,
    paymentMethod: "VISA **** 4029",
    paymentNumber: "310 5544 391"
  };

  useEffect(() => {
    // Fetch city data
    const fetchCities = async () => {
      try {
        const requestOptions = {
          method: "GET",
          redirect: "follow"
        };

        const response = await fetch("http://localhost:3241/obtenerCiudades", requestOptions);
        const result = await response.json();
        setCities(result.data);
        
        // Set city coordinates for use in the map
        const coordinates = {};
        result.data.forEach(city => {
          coordinates[city.nombre.toLowerCase()] = [city.latitud, city.longitud];
        });
        setCityCoordinates(coordinates);
      } catch (error) {
        console.error('Error fetching cities:', error);
      }
    };

    fetchCities();
  }, []);

  const fetchParqueaderos = async (cityId) => {
    try {
      const myHeaders = new Headers();
      myHeaders.append("Content-Type", "application/json");

      const raw = JSON.stringify({
        "ciudad_fk": cityId
      });

      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
        redirect: "follow"
      };

      const response = await fetch("http://localhost:3241/parqueaderoCiudad", requestOptions);
      const result = await response.json();

      // Map the colors
      const mappedParqueaderos = result.data.map(parqueadero => {
        return {
          ...parqueadero,
          color: mapColor(parqueadero.color)
        };
      });

      setParqueaderos(mappedParqueaderos);
    } catch (error) {
      console.error('Error fetching parqueaderos:', error);
    }
  };

  const mapColor = (color) => {
    switch (color) {
      case 'NEGRO':
        return `hsl(48, 100%, 0%, 1)`;
      case 'VERDE':
        return 'hsl(86, 100%, 43%, 1'; // Green
      case 'AMARILLO':
        return 'hsl(48, 100%, 48%, 1)'; // Yellow
      default:
        return 'hsl(0deg, 0%, 50%)'; // Default to gray
    }
  };

  const handleCityChange = (e) => {
    const selectedCityName = e.target.value;
    setSelectedCity(selectedCityName);
    setMapKey(prevKey => prevKey + 1); // Cambiar la clave del mapa para forzar la recarga

    // Fetch parqueaderos for the selected city
    const selectedCity = cities.find(city => city.nombre.toLowerCase() === selectedCityName.toLowerCase());
    if (selectedCity) {
      fetchParqueaderos(selectedCity.id);
    }
  };

  const handleMarkerClick = (parqueadero) => {
    setSelectedParqueadero(parqueadero);
    setParqueaderoOpen(true);
  };

  return (
    <div className="card-user">
      <div className='container'>
        <Parqueadero 
          isOpen={isParqueaderoOpen} 
          onClose={() => setParqueaderoOpen(false)} 
          name={selectedParqueadero.nombre} 
          cupoCarro={selectedParqueadero.cupo_disponible_carro}
          cupoMoto={selectedParqueadero.cupo_disponible_moto}
          cupoBici={selectedParqueadero.cupo_disponible_bici}
          idParqueadero={selectedParqueadero.id}
          tipo={selectedParqueadero.tipo}
        />
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

        
        <div className='contenedor-mapa'>
          <div className='city-select'>
            <label htmlFor="city">Selecciona una ciudad:</label>
            <select id="city" value={selectedCity} onChange={handleCityChange}>
              <option value="">Seleccione una ciudad</option>
              {cities.map((city) => (
                <option key={city.id} value={city.nombre.toLowerCase()}>
                  {city.nombre}
                </option>
              ))}
            </select>
          </div>
          {selectedCity && cityCoordinates[selectedCity] && (
            <div className='mapa'>
              <Map key={mapKey} defaultCenter={cityCoordinates[selectedCity]} defaultZoom={12} minHeight={300}>
                {parqueaderos.map(parqueadero => (
                  <Marker
                    key={parqueadero.id}
                    width={50}
                    anchor={[parqueadero.latitud, parqueadero.longitud]}
                    color={parqueadero.color}
                    onClick={() => handleMarkerClick(parqueadero)}
                  />
                ))}
              </Map>
            </div>
          )}
        </div>
        <Historial />
      </div>
    </div>
  );
}

export default NuevoUsuario;
