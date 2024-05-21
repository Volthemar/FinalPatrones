import React, { useState, useEffect } from 'react';
import './ParkingManagement.css'; // Importar los estilos CSS
import Sidebar from '../Sidebar/Sidebar';
const fetchParkingLot = async (parkingLotId) => {
  try {
    const response = await fetch('http://localhost:3241/obtenerParqueadero', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ parqueadero_id: parkingLotId }),
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error fetching parking lot:', error);
    return null;
  }
};

const ParkingManagement = () => {
  const [parkingData, setParkingData] = useState(null);
  const [disponible, setDisponible] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      const data = await fetchParkingLot(1);
      setParkingData(data);
    };

    fetchData();
  }, []);

  return (
    <div className="parking-management-container">
        <Sidebar></Sidebar>
      <h1 id="title-parkingManagement">Administración de cupos</h1>
      <div className="buttons-parkingManagement">
        <button
          className="button-parkingManagement green-parkingManagement"
          onClick={() => setDisponible(true)}
          disabled={disponible}
        >
          Ver Cupos Disponibles
        </button>
        <button
          className="button-parkingManagement red-parkingManagement"
          onClick={() => setDisponible(false)}
          disabled={!disponible}
        >
          Ver Cupos Reservados
        </button>
        <button className="button-parkingManagement blue-parkingManagement">Reservar cupo</button>
        <button className="button-parkingManagement yellow-parkingManagement">Vista General del Parqueadero</button>
      </div>
      
      <div className="data-container-parkingManagement">
        {parkingData ? (
          <div>
            <h2 id="parking-name">{parkingData.data.nombre}</h2>
            <table className="parking-table" id="parking-table">
              <thead>
                <tr>
                  <th>Vehículo </th>
                  <th>{disponible ? 'Cupos Utilizados' : 'Cupos Totales'}</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Moto</td>
                  <td>{disponible ? parkingData.data.cupo_uti_moto : parkingData.data.cupo_moto_total}</td>
                </tr>
                <tr>
                  <td>Carro</td>
                  <td>{disponible ? parkingData.data.cupo_uti_carro : parkingData.data.cupo_carro_total}</td>
                </tr>
                <tr>
                  <td>Bicicleta</td>
                  <td>{disponible ? parkingData.data.cupo_uti_bici : parkingData.data.cupo_bici_total}</td>
                </tr>
              </tbody>
            </table>
          </div>
        ) : (
          <p>Loading...</p>
        )}
      </div>
    </div>
  );
};

export default ParkingManagement;
