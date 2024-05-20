import React, { useState } from 'react';
import './CrearParqueadero.css';
import Sidebar from '../Sidebar/Sidebar';

const CrearCiudad = () => {
    const [cityName, setCityName] = useState('');
    const [altitude, setAltitude] = useState('');
    const [latitude, setLatitude] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        const data = {
            cityName,
            altitude,
            latitude
        };

        console.log('Form data submitted:', data);
        // Handle form submission, such as sending data to a server
    };

    return (
        <div>
            <Sidebar />
            <div className="card2">
                <form className='form-ciudades' onSubmit={handleSubmit}>
                    <div className='div-input-ciudades'>
                        <label>Nombre de la ciudad</label>
                        <input className='input-ciudades'
                            type="text"
                            value={cityName}
                            onChange={(e) => setCityName(e.target.value)}
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
                    <button className='button-ciudades' type="submit">Enviar</button>
                </form>
            </div>
        </div>
    );
};

export default CrearCiudad;
