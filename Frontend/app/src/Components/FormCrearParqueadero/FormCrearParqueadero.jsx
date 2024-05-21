import React, { useState, useEffect } from 'react';
import './FormCrearParqueadero.css'

function FormCrearParqueadero() {
    const URL_OBTENER_CIUDADES = "http://localhost:3241/obtenerCiudades"
    const URL_OBTENER_TIPOS_PARQUEADEROS = 'http://localhost:3241/tiposParqueadero'
    const requestOptions = {
                    method: "GET",
                    redirect: "follow"
                };
    const [parkingType, setParkingType] = useState('');
    const [nombreParqueadero, setNombreParqueadero] = useState('');
    const [numCars, setNumCars] = useState('');
    const [numMotorcycles, setNumMotorcycles] = useState('');
    const [numBicycles, setNumBicycles] = useState('');
    const [longitud, setLongitud] = useState('');
    const [latitud, setLatitud] = useState('');
    const [city, setCity] = useState('');
    const [cities, setCities] = useState([]);
    
    //New
    const [tiposParqueadero, setTiposParqueadero] = useState([]);
    //...

    useEffect(() => {
        const fetchCities = async () => {
            try {
                const response = await fetch(URL_OBTENER_CIUDADES, requestOptions);
                const result = await response.json();
                setCities(result.data);
            } catch (error) {
                console.error('Problema:', error);
            }
        };
        fetchCities();
    }, []);

    useEffect(() => {
        const fetchTiposParqueadero = async () => {
            try {
                const response = await fetch(URL_OBTENER_TIPOS_PARQUEADEROS, requestOptions);
                const result = await response.json();
                setTiposParqueadero(result.data);
                console.log(result.data);
            } catch (error) {
                console.error('Problema:', error);
            }
        };
        fetchTiposParqueadero();
    },[])

    const handleSubmit = async (e) => {
        e.preventDefault();

        const selectedCity = cities.find(c => c.nombre === city);
        if (!selectedCity) {
            alert('Please select a valid city');
            return;
        }

        const data = {
            nombre: nombreParqueadero,
            ciudad_fk: selectedCity.id,
            cupo_carro_total: parseInt(numCars, 10),
            cupo_moto_total: parseInt(numMotorcycles, 10),
            cupo_bici_total: parseInt(numBicycles, 10),
            tipo_fk: parkingType === 'cubierto' ? 1 : parkingType === 'descubierto' ? 2 : parkingType === 'con_suscripcion' ? 3 : 4,
            longitud: parseFloat(altitude),
            latitud: parseFloat(latitude)
        };

        try {
            const requestOptions = {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data),
                redirect: "follow"
            };

            const response = await fetch("http://localhost:3241/guardarParqueadero", requestOptions);
            const result = await response.json();
            console.log('Response from server:', result);

            if (result && result.msg === 'Ciudad creada') {
                alert('Parqueadero creado');
                // Reset form fields
                setParkingType('');
                setNombreParqueadero('');
                setNumCars('');
                setNumMotorcycles('');
                setNumBicycles('');
                setAltitude('');
                setLatitude('');
                setCity('');
            }
        } catch (error) {
            console.error('Error submitting data:', error);
        }
    };

    return (
            <div id='container-crear-parqueadero'>
                <form id='form-crear-parqueadero' onSubmit={handleSubmit}>
                    <div className='div-select-form-parqueadero'>
                        <label>Tipo Parqueadero: </label>
                        <select className='select-form-parqueadero'
                            value={parkingType}
                            onChange={(e) => setParkingType(e.target.value)}
                            required>
                                {tiposParqueadero.map((tipo) => (
                                <option key={tipo.id} value={tipo.nombre}>{tipo.tipo}</option>
                            ))}
                        </select>
                    </div>
                    <div className='div-input-crear-parqueadero'>
                        <label>Nombre del parqueadero</label>
                        <input className='input-crear-parqueadero'
                            type="text"
                            value={nombreParqueadero}
                            placeholder='Ingrese el nombre del parqueadero.'
                            onChange={(e) => setNombreParqueadero(e.target.value)}
                            required
                        /></div>
                    <div className='div-input-crear-parqueadero'>
                        <label>Cupos para carros:</label>
                        <input className='input-crear-parqueadero'
                            type="number"
                            value={numCars}
                            placeholder='Ingrese el número de cupos para carros.'
                            onChange={(e) => setNumCars(e.target.value)}
                            required
                        /></div>
                    <div className='div-input-crear-parqueadero'>
                        <label>Cupos para Motos:</label>
                        <input className='input-crear-parqueadero'
                            type="number"
                            value={numMotorcycles}
                            placeholder='Ingrese el número de cupos para motos.'
                            onChange={(e) => setNumMotorcycles(e.target.value)}
                            required
                        />
                    </div>
                    <div className='div-input-crear-parqueadero'>
                        <label>Cupos para Bicicletas:</label>
                        <input className='input-crear-parqueadero'
                            type="number"
                            value={numBicycles}
                            placeholder='Ingrese el número de cupos para bicicletas.'
                            onChange={(e) => setNumBicycles(e.target.value)}
                            required
                        />
                    </div>
                    <div className='div-input-crear-parqueadero'>
                        <label>Longitud:</label>
                        <input className='input-crear-parqueadero'
                            type="number"
                            placeholder='Ingrese la longitud'
                            value={longitud}
                            onChange={(e) => setLongitud(e.target.value)}
                            required
                        />
                    </div>
                    <div className='div-input-crear-parqueadero'>
                        <label>Latitud:</label>
                        <input className='input-crear-parqueadero'
                            type="number"
                            value={latitud}
                            placeholder='Ingrese la Latitud'
                            onChange={(e) => setLatitud(e.target.value)}
                            required
                        />
                    </div>
                    <div className='div-select-form-parqueadero'>
                        <label>Ciudad:</label>
                        <select className='select-tipo-parqueadero'
                            value={city}
                            onChange={(e) => setCity(e.target.value)}
                            required>
                            {cities.map((city) => (
                                <option key={city.id} value={city.nombre}>
                                    {city.nombre}
                                </option>
                            ))}
                        </select>
                    </div>
                    <button id='btn-crear-parqueadero' type="submit">Enviar</button>
                </form>
            </div>
    );
};

export default FormCrearParqueadero;