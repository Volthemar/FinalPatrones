import React from 'react';
import './Factura.css';
import logo from '../../assets/logo.png'; // Asegúrate de que la ruta al logo sea correcta

export default function Factura({ data }) {
    const { codigo, horaLlegada, vehiculo, parqueadero, ciudad, horasPedidas } = data;

    return (
        <div className="factura-container">
            <div className="factura-header">
                <img src={logo} alt="Logo" className="factura-logo" />
                <div className="factura-info">
                    <h2>FACTURA</h2>
                    <p><strong>Fecha generado:</strong> {new Date().toLocaleDateString()}</p>
                </div>
            </div>
            <div className="factura-body">
                <p><strong>CODIGO:</strong> {codigo}</p>
                <p><strong>Hora llegada:</strong> {horaLlegada}</p>
                <p><strong>Vehiculo:</strong> {vehiculo}</p>
                <p><strong>Parqueadero:</strong> {parqueadero}</p>
                <p><strong>Ciudad:</strong> {ciudad}</p>
                <p><strong>Horas pedidas:</strong> {horasPedidas}</p>
            </div>
            <button className="factura-print" onClick={() => window.print()}>IMPRIMIR</button>
        </div>
    );
}
