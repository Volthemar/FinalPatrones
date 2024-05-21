import React, { useState, useEffect } from 'react';
import './GerenteParqueaderos.css';
import Sidebar from '../Sidebar/Sidebar';

function GerenteParqueaderos() {
  const URL_CIUDADES = "http://localhost:3241/obtenerCiudades";
  const URL_PARQUEADEROS_CIUDAD = "http://localhost:3241/parqueaderoCiudad";

  const [ciudadSeleccionada, setCiudadSeleccionada] = useState("");
  const [ciudades, setCiudades] = useState([]);
  const [parqueaderos, setParqueaderos] = useState([]);
  const [parqueaderoSeleccionado, setParqueaderoSeleccionado] = useState("");
  const [parqueaderoDetalles, setParqueaderoDetalles] = useState(null);

  // Fetch cities on component mount
  useEffect(() => {
    const fetchCiudades = async () => {
      try {
        const response = await fetch(URL_CIUDADES);
        if (!response.ok) {
          throw new Error('Error fetching cities');
        }
        const data = await response.json();
        setCiudades(data.data);
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchCiudades();
  }, []);

  // Fetch parking lots when a city is selected
  const handleChangeCiudad = async (event) => {
    const ciudad = event.target.value;
    setCiudadSeleccionada(ciudad);
    setParqueaderoSeleccionado("");
    setParqueaderoDetalles(null);

    try {
      const response = await fetch(URL_PARQUEADEROS_CIUDAD, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ ciudad_fk: ciudad }),
      });

      if (!response.ok) {
        throw new Error('Error fetching parking lots');
      }

      const data = await response.json();
      setParqueaderos(data.data);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const handleChangeParqueadero = (event) => {
    const parqueaderoId = event.target.value;
    setParqueaderoSeleccionado(parqueaderoId);

    const selectedParqueadero = parqueaderos.find(parqueadero => parqueadero.id === parseInt(parqueaderoId));
    setParqueaderoDetalles(selectedParqueadero);
  };

  return (
    <>
      <Sidebar vista={'Gerente'} />
      <div id="contenidoGerente">
        <div className='infoParqueaderos'>
          <h2>Estadísticas de uso</h2>
          <label htmlFor="ciudades">Selecciona una ciudad:</label>
          <select id="ciudades" name="ciudades" value={ciudadSeleccionada} onChange={handleChangeCiudad} className="select-ciudades">
            <option value="">Selecciona una ciudad</option>
            {ciudades.map((ciudad) => (
              <option key={ciudad.id} value={ciudad.id}>
                {ciudad.nombre}
              </option>
            ))}
          </select>

          {ciudadSeleccionada && (
            <>
              <label htmlFor="parqueaderos">Selecciona un parqueadero:</label>
              <select id="parqueaderos" name="parqueaderos" value={parqueaderoSeleccionado} onChange={handleChangeParqueadero} className="select-parqueaderos">
                <option value="">Selecciona un parqueadero</option>
                {parqueaderos.map((parqueadero) => (
                  <option key={parqueadero.id} value={parqueadero.id}>
                    {parqueadero.nombre} - {parqueadero.tipo}
                  </option>
                ))}
              </select>
            </>
          )}
        </div>


        {parqueaderoDetalles && (
          <div className="parqueadero-detalles">
            <h3>Detalles del Parqueadero</h3>
            <form className='form-gerenteParqueadero'>

              <label htmlFor="cupo_carro">Cupo Total carro:</label>
              <input type="number" id="cupo_carro" value={parqueaderoDetalles.cupo_disponible_carro} readOnly className="input-detalle" />

              <label htmlFor="cupo_moto">Cupo Total moto:</label>
              <input type="number" id="cupo_moto" value={parqueaderoDetalles.cupo_disponible_moto} readOnly className="input-detalle" />

              <label htmlFor="cupo_bici">Cupo Total bici:</label>
              <input type="number" id="cupo_bici" value={parqueaderoDetalles.cupo_disponible_bici} readOnly className="input-detalle" />

              <label htmlFor="tipo">Tipo:</label>
              <input type="text" id="tipo" value={parqueaderoDetalles.tipo} readOnly className="input-detalle" />

              <label htmlFor="longitud">Longitud:</label>
              <input type="text" id="longitud" value={parqueaderoDetalles.longitud} readOnly className="input-detalle" />

              <label htmlFor="latitud">Latitud:</label>
              <input type="text" id="latitud" value={parqueaderoDetalles.latitud} readOnly className="input-detalle" />

            </form>
          </div>
        )}
      </div>
    </>
  );
}

export default GerenteParqueaderos;
