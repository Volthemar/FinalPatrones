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
    const [currentImage, setCurrentImage] = useState(carrusel1);
    const [nextImage, setNextImage] = useState(carrusel2); // La próxima imagen a mostrar

    useEffect(() => {
        const interval = setInterval(() => {
            
            if (currentImage === carrusel1) {
                setNextImage(carrusel2);
            } else if (currentImage === carrusel2) {
                setNextImage(carrusel3);
            } else {
                setNextImage(carrusel1);
            }
        }, 2000);

        return () => clearInterval(interval);
    }, [currentImage]);


    useEffect(() => {
        const timeout = setTimeout(() => {
            setCurrentImage(nextImage);
        }, 100); 

        return () => clearTimeout(timeout);
    }, [nextImage]);

    return (
        <div className="landing-page">
            <header className="header">
                <img id='logo' src={Logo} alt="Logo"></img>
                <nav>
                    <Link to="/login" className="login-link">
                        <button id='buttonIngreso'>
                        Ingresa
                        </button>
                        </Link>
                </nav>
            </header>
            <div className='info'>
                <section className="hero-section" style={{ backgroundImage: `url(${currentImage})` }}>
                    <h1>Bienvenido a Four Parks</h1>
                    <p>Reserva tu parqueadero en Cali, Medellín o Bogotá de manera fácil y rápida.</p>
                    <Link to="/registro">
                        <button>Regístrate</button>
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
