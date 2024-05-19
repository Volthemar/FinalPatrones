import React from 'react';
import { Link } from 'react-router-dom';
import './LandingPage.css';
import Logo from '../../assets/logo.png'

function LandingPage(){
  return (
    <div className="landing-page">
      <header className="header">
      <img id='logo' src={Logo} alt="Logo"></img>
        <nav>
          <Link to="/login" className="login-link">Login</Link>
        </nav>
      </header>
      
      <section className="hero-section">
        <h1>Bienvenido a Four Parks</h1>
        <p>Reserva tu parqueadero en Cali, Medellín o Bogotá de manera fácil y rápida.</p>
        <button>Regístrate Ahora</button>
      </section>
      
      <section className="parking-options">
        <h2>Nuestras Ubicaciones</h2>
        <div className="locations">
          <div className="location">
            <img src="cali.jpg" alt="Cali" />
            <h3>Cali</h3>
          </div>
          <div className="location">
            <img src="medellin.jpg" alt="Medellín" />
            <h3>Medellín</h3>
          </div>
          <div className="location">
            <img src="bogota.jpg" alt="Bogotá" />
            <h3>Bogotá</h3>
          </div>
        </div>
      </section>
      
      <section className="registration-form">
        <h2>Regístrate</h2>
        <form>
          <input type="text" placeholder="Nombre" required />
          <input type="email" placeholder="Email" required />
          <input type="password" placeholder="Contraseña" required />
          <button type="submit">Registrarse</button>
        </form>
      </section>
      
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
