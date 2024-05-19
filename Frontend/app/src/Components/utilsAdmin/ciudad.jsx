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

                <form className='form-ciudades' onSubmit={handleSubmit}>
                    <div className='div-select-ciudades'>
                        <label>Tipo Parqueadero</label>
                        <select className='select-ciudades'
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
                    <div className='div-input-ciudades'>
                        <label>N CARROS</label>
                        <input className='input-ciudades'
                            type="number"
                            value={numCars}
                            onChange={(e) => setNumCars(e.target.value)}
                        /></div>
                    <div className='div-input-ciudades'>
                        <label>N MOTOS</label>
                        <input className='input-ciudades'
                            type="number"
                            value={numMotorcycles}
                            onChange={(e) => setNumMotorcycles(e.target.value)}
                        />
                    </div>
                    <div className='div-input-ciudades'>
                        <label>N BICICLETAS</label>
                        <input className='input-ciudades'
                            type="number"
                            value={numBicycles}
                            onChange={(e) => setNumBicycles(e.target.value)}
                        />
                    </div>
                    <div className='div-input-ciudades'>
                        <label>Altitud</label>
                        <input className='input-ciudades'
                            type="number"
                            value={altitude}
                            onChange={(e) => setAltitude(e.target.value)}
                        />
                    </div>
                    <div className='div-input-ciudades'>
                        <label>Latitud</label>
                        <input className='input-ciudades'
                            type="number"
                            value={latitude}
                            onChange={(e) => setLatitude(e.target.value)}
                        />
                    </div>
                    <div className='div-input-ciudades'>
                        <label>Ciudad</label>
                        <input className='input-ciudades'
                            type="text"
                            value={city}
                            onChange={(e) => setCity(e.target.value)}
                        />
                    </div>
                    <button className='button-ciudades' type="submit">Enviar</button>
                </form>
            </div>
        </div>

    );
};

export default Ciudad;
