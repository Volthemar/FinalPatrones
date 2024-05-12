import React, { useState } from 'react';
import './NewUser.css'
import Persona from './assets/Persona.svg'
import { Map, Marker } from "pigeon-maps"
import Parqueadero from './Parqueadero';

function NewUser() {
  // Simulando datos del usuario
  const [hue, setHue] = useState(0)
  const color = `hsl(${hue % 350}deg, 100%, 50%)`
  const [isParqueaderoOpen, setParqueaderoOpen] = useState(false);
  const [NombreParqueadero, setNombreParqueadero] = useState('');
  const nombre = localStorage.getItem('nombre');
  console.log(nombre)
  const userData = {
    name: nombre,
    paymentMethod: "VISA **** 4029",
    paymentNumber: "310 5544 391"
  };

  // Simulando datos del historial de usos
  const usageHistory = [
    { 
        date: "11 Mar 2023", 
        locations: ["Parkway", "U Javeriana"], 
        status: "En progreso...", 
        time: "7m 23s", 
        amount: "$ -" 
    },
    { 
        date: "9 Mar 2023", 
        locations: ["Rosales (Kr 25 Cl 30)"], 
        status: "25m 54s", 
        time: "4m 23s", 
        amount: "$ 7950" 
    },
    { 
        date: "9 Mar 2023", 
        locations: ["Rosales (Kr 25 Cl 30)"], 
        status: "4m 23s", 
        time: "38m 13s", 
        amount: "$ 2550" 
    },
    
];

  return (
    <div className="card">
      <header className="UserHeader">
        <img src={Persona} alt="Perfil" className="UserImage" />
        <div className="UserProfile">
          <h1>Hola, {userData.name}!</h1>
          <hr />
          <p>Medio de pago: {userData.paymentMethod}</p>
          <p>Número: {userData.paymentNumber}</p>
          <div className='botones'>
            <button className='Configuracion'>Configuracion</button>
            <button className='Salir'>Salir</button>
          </div>
        </div>
      </header>
      <h2><b>Historial de usos</b></h2>
      <section className="UserUsageHistory">
        <ul>
          {usageHistory.map((entry, index) => (
            <li key={index}>
              <div className="Date">{entry.date}</div>
              <div className="Locations">{entry.locations.join(', ')}</div>
              <div className="Status">{entry.status}</div>
              <div className="Time">{entry.time}</div>
              <div className="Amount">{entry.amount}</div>
            </li>
          ))}
        </ul>
      </section>
      
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

export default NewUser;