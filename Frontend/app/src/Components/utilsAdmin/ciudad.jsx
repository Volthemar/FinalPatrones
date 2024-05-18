import React, { useState } from 'react';
import './ciudad.css'
import Sidebar from '../Sidebar/Sidebar';
const Ciudad = () => {
    const [parkingType, setParkingType] = useState('');
    const [numCars, setNumCars] = useState('');
    const [numMotorcycles, setNumMotorcycles] = useState('');
    const [numBicycles, setNumBicycles] = useState('');
    const [altitude, setAltitude] = useState('');
    const [latitude, setLatitude] = useState('');
    const [city, setCity] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        const data = {
            parkingType,
            numCars,
            numMotorcycles,
            numBicycles,
            altitude,
            latitude,
            city
        };

        fetch('https://your-server-endpoint.com/api/parking', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(result => {
                console.log('Success:', result);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    };

    return (
        <div>
            <Sidebar></Sidebar>
            <div className="card2">

                <form onSubmit={handleSubmit}>
                    <div>
                        <label>Tipo Parqueadero</label>
                        <select
                            value={parkingType}
                            onChange={(e) => setParkingType(e.target.value)}
                        >
                            <option value="">Seleccione una opción</option>
                            <option value="cubierto">Cubierto</option>
                            <option value="descubierto">Descubierto</option>
                            <option value="con_suscripcion">Con suscripción</option>
                            <option value="sin_suscripcion">Sin suscripción</option>
                        </select>
                    </div>
                    <div>
                        <label>N CARROS</label>
                        <input
                            type="number"
                            value={numCars}
                            onChange={(e) => setNumCars(e.target.value)}
                        />                    </div>
                    <div>                        <label>N MOTOS</label>
                        <input
                            type="number"
                            value={numMotorcycles}
                            onChange={(e) => setNumMotorcycles(e.target.value)}
                        />
                    </div>                    <div>                        <label>N BICICLETAS</label>
                        <input
                            type="number"
                            value={numBicycles}
                            onChange={(e) => setNumBicycles(e.target.value)}
                        />
                    </div>                    <div>                        <label>Altitud</label>
                        <input
                            type="number"
                            value={altitude}
                            onChange={(e) => setAltitude(e.target.value)}
                        />
                    </div>                    <div>
                        <label>Latitud</label>
                        <input
                            type="number"
                            value={latitude}
                            onChange={(e) => setLatitude(e.target.value)}
                        />
                    </div>
                    <div>
                        <label>Ciudad</label>
                        <input
                            type="text"
                            value={city}
                            onChange={(e) => setCity(e.target.value)}
                        />
                    </div>
                    <button type="submit">Enviar</button>
                </form>
            </div>
        </div>

    );
};

export default Ciudad;
