import React , { useState } from 'react';
import './NewUser.css'
import Persona from '../assets/Persona.svg'
import { Map, Marker } from "pigeon-maps"

function NewUser() {
  // Simulando datos del usuario
  const [hue, setHue] = useState(0)
  const color = `hsl(${hue % 350}deg, 100%, 50%)`

  const userData = {
    name: "Juan Perez",
    paymentMethod: "VISA **** 4029",
    paymentNumber: "310 5544 391"
  };

  // Simulando datos del historial de usos
  const usageHistory = [
    { date: "11 Mar 2023", locations: ["Parkway", "U Javeriana"], status: "En progreso...", time: "7m 23s", amount: "-" },
    // ...otros datos
  ];

  return (
    <div className="card">
      <header className="UserHeader">
        <div className="UserProfile">
          <img src={Persona} alt="Perfil" className="UserImage" />
          <h1>Hola, {userData.name}!</h1>
        </div>
        <div className="UserPaymentInfo">
          <p>Medio de pago: {userData.paymentMethod}</p>
          <p>Número: {userData.paymentNumber}</p>
        </div>
      </header>
      <section className="UserUsageHistory">
        <h2>Historial de usos</h2>
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

          onClick={() => setHue(hue + 20)}
        />
        <Marker
          width={50}
          anchor={[4.630423, -74.066546]}
          color={color}
          onClick={() => setHue(hue + 20)}
        >

        </Marker>
      </Map>
    </div>
  );
}

export default NewUser;