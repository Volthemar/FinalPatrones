import React, { useState, useEffect } from 'react';
import './Impacto.css'
import Sidebar from '../Sidebar/Sidebar';
import Chart from '../utilsAdmin/Chart';
function Impacto() {

  /*
  Ya quedó el consumo de ciudades y de parqueaderos por ciudad, El endpoint de un parqueadero
  específico parece no estar funcionando ni se ha definido cuales son los datos a proporcionar
  para generar las graficas por tanto hace falta 

  1. consumir los datos del parqueadero específico y generar las graficas.
  */

  const URL_CIUDADES = "http://localhost:3241/obtenerCiudades"
  const URL_PARQUEADEROS = "http://localhost:3241/parqueaderoCiudadBasico"
  const [datosCiudades, setDatosCiudades] = useState(null);
  const [ciudadSeleccionada, setCiudadSeleccionada] = useState("");
  const [visibilidadSegundoSelect, setVisibilidadSegundoSelect] = useState(false);
  const [datosParqueaderos, setDatosParqueaderos] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(URL_CIUDADES);
        if (!response.ok) {
          throw new Error('Problema: ' + response.statusText);
        }
        const ciudades = await response.json();
        setDatosCiudades(ciudades);
      } catch (error) {
        console.error('Problema:', error);
      }
    };
    fetchData();
  }, []);

  const handleChange = async (event) => {
    const idCiudadSeleccionada = event.target.value;
    setCiudadSeleccionada(idCiudadSeleccionada);
    console.log(idCiudadSeleccionada);
    fetch(URL_PARQUEADEROS, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ ciudad_fk: idCiudadSeleccionada })
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Problema: ' + response.statusText);
        }
        return response.json();
      })
      .then(data => {
        console.log("data:");
        console.log(data);
        setDatosParqueaderos(data);
        setVisibilidadSegundoSelect(true);
      })
      .catch(error => {
        console.error('Problema:', error);
      });
  }

  const handleChangeParking = async (event) =>{

  }

  return (
    <>
      <Sidebar vista={'Gerente'}></Sidebar>
      <div id="contenidoImpacto">
        <Chart></Chart>
      </div>
    </>
  );
}
export default Impacto;