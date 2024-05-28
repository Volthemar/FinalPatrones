import React from 'react';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './LandingPage.css';
import Logo from '../../assets/logo.png';
import Bogota from '../../assets/bogota.png';
import Cali from '../../assets/cali.png';
import Medellin from '../../assets/medellin.png';
import carrusel1 from '../../assets/carrusel1.jpg';
import carrusel2 from '../../assets/carrusel2.jpg';
import carrusel3 from '../../assets/carrusel3.jpg';

function LandingPage() {
    const [imagenActual, setImagenActual] = useState(carrusel1);
    const [siguienteImagen, setSiguienteImagen] = useState(carrusel2);

    useEffect(() => {
        const intervalo = setInterval(() => {
            
            if (imagenActual === carrusel1) {
                setSiguienteImagen(carrusel2);
            } else if (imagenActual === carrusel2) {
                setSiguienteImagen(carrusel3);
            } else {
                setSiguienteImagen(carrusel1);
            }
        }, 2000);

        return () => clearInterval(intervalo);
    }, [imagenActual]);


    useEffect(() => {
        const timeout = setTimeout(() => {
            setImagenActual(siguienteImagen);
        }, 100); 

        return () => clearTimeout(timeout);
    }, [siguienteImagen]);

    return (
        <div className="landing-page">
            <header className="header">
                <img id='logo' src={Logo} alt="Logo"></img>
                <nav>
                    <Link to="/login" className="login-link">
                        <button className='landing-button'>
                        Ingresa
                        </button>
                        </Link>
                </nav>
            </header>
            <div className='info'>
                <section className="hero-section" style={{ backgroundImage: `url(${imagenActual})` }}>
                    <h1>¡Bienvenido a Four Parks!</h1>
                    <p>Reserva tu parqueadero de manera fácil, rápida y segura.</p>
                    <Link to="/registro">
                        <button className='landing-button'>Regístrate</button>
                    </Link>
                </section>

                <section className="parking-options">
                    <h2>Nuestras Ubicaciones</h2>
                    <div className="locations">
                        <div className="location">
                            <img id='Cali' src={Cali} alt="Cali"></img>
                            <h3>Cali</h3>
                        </div>
                        <div className="location">
                            <img id='Medellin' src={Medellin} alt="Medellin"></img>
                            <h3>Medellín</h3>
                        </div>
                        <div className="location">
                            <img id='Bogota' src={Bogota} alt="Bogota"></img>
                            <h3>Bogotá</h3>
                        </div>
                    </div>
                </section>
            </div>

            <footer className="footer">
                <p>© 2024 Four Parks. Todos los derechos reservados.</p>
                <div className="social-media">
                    <a href="#">Facebook</a>
                    <a href="#">Twitter</a>
                    <a href="#">Instagram</a>
                </div>
            </footer>
        </div>
    );
};

export default LandingPage;
