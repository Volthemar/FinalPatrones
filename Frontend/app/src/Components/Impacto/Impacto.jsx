import React, { useState } from 'react';
import './Impacto.css'

function Impacto() {
    /*
    Creo no hay un endpoint 
    */
    const URL_PARQUEADEROS_CIUDAD = "http://localhost:3241/parqueaderoCiudadBasico" 
    const [ciudadSeleccionada, setCiudadSeleccionada] = useState("");
    const [datos, setDatos] = useState(null);

    const handleChange = async (event) => {
        const ciudad = event.target.value;
        setCiudadSeleccionada(ciudad);

        try {
            const response = await 
            fetch(URL_PARQUEADEROS_CIUDAD, {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json'
                },
                body: JSON.stringify({ 
                  ciudad_fk: ciudad 
                })
              })
              .then(response => {
                if (!response.ok) {
                  throw new Error('Error al realizar la solicitud');
                }
                return response.json();
              })
              .then(data => {
                setDatos(data)
                console.log(data);
              })
        } catch (error) {
            console.error("Error:", error);
        }
    };

    return (
        <div id="contenidoImpacto">
            <h2>Estadísticas de uso</h2>
            <label htmlFor="ciudades">Selecciona una ciudad:</label>
            <select id="ciudades" name="ciudades" value={ciudadSeleccionada} onChange={handleChange}>
                <option value="idmedellin?">Medellín</option>
                <option value="idcali?">Cali</option>
                <option value="idbogotá?">Bogotá</option>
            </select>
            {datos && (
                <div>
                    {/* definir que graficas con las estadisticas o hacer un componente de estadisticas al que se le pasa todo*/}
                    <p>{datos}</p>
                </div>
            )}
        </div>
    );
}
export default Impacto;