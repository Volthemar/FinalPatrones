import React from 'react';
import { Link } from 'react-router-dom';
import './LandingPage.css';
import Logo from '../../assets/logo.png';
import Bogota from '../../assets/bogota.png';
import Cali from '../../assets/cali.png';
import Medellin from '../../assets/medellin.png';

function LandingPage() {
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
                <section className="hero-section">
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


            <section className="contact-form">
                <h2>Contacto</h2>
                <form>
                    <input type="text" placeholder="Nombre" required />
                    <input type="email" placeholder="Email" required />
                    <textarea placeholder="Mensaje" required></textarea>
                    <button type="submit">Enviar</button>
                </form>
            </section>

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
