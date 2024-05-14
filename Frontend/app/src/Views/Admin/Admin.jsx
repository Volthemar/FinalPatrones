import React from 'react';
import './Admin.css'; // Import the Admin specific styles
import '../../Index.css'; // Import the global styles

const ViewComponent = () => {
    return (
        <div className="container">
            <div className="sidebar">
                <div>
                    <img src="../../assets/Persona.png" alt="User Profile" />
                    <div>Pedro Navaja</div>
                    <button>
                        <img src="../../assets/LogoTrazabilidad.svg" alt="Report Icon" />
                        Reporte del parqueadero
                    </button>
                    <button>
                        <img src="../../Assets/LogoModificaciones.svg" alt="Manage Icon" />
                        Administración de cupos
                    </button>
                </div>
                <button className="logout">
                    <img src="../../assets/LogoCerrarSesion.svg" alt="Logout Icon" />
                    Cerrar sesión
                </button>
            </div>
            <div className="main-content">
                <h1>Administración de cupos</h1>
                <div className="buttons-container">
                    <button className="green-button">Ver Cupos Disponibles</button>
                    <button className="red-button">Ver Cupos Reservados</button>
                    <button className="blue-button">Reservar cupo</button>
                    <button className="yellow-button">Vista General del Parqueadero</button>
                </div>
                <div className="parking-lot"></div>
            </div>
        </div>
    );
};

export default ViewComponent;
