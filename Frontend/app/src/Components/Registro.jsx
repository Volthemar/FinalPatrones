import { useState } from 'react'
import Logo from '../assets/logo.png' // Placeholder para el logo
import backgroundLogin from '../assets/backgroundLogin.svg'
import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import NewUser from './NewUser'; // Importando el componente NewUser

function Registro() {
  const URL_POST = 'http://localhost:3241/login'; // Endpoint para confirmar datos
  const URL_USER = 'user'; // Endpoint del perfil de usuario
  const URL_REGISTRO = '/'; // Endpoint para registro
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [tarjetaCredito, settarjetaCredito] = useState('');

  function login(event) {
    event.preventDefault();
    if (username.trim() === '' || password.trim() === ''|| tarjetaCredito.trim() =='') {
      alert('Por favor complete todos los campos.');
      return;
    }
    const userData = {
      usuario: username,
      contrasena: password,
      tarjetaCredito: tarjetaCredito
    };
    fetch(URL_POST, {
      method: 'POST',
      body: JSON.stringify(userData),
      headers: {
        'Content-Type': 'application/json'
      }
    })
    .then(response => {
      if (response.ok) {
        window.location.href = URL_USER; // Redirect to user profile page on successful login
      } else {
        throw new Error('Usuario no válido'); // Throw error to catch block
      }
    })
    .catch(error => {
      console.error(error);
      alert('Usuario no válido. Intente nuevamente.'); // Show alert on failed login
    });
}


  return (
    <>
      <div id='container'>
        <div id='backgroundContainer'>
          <img src={backgroundLogin} alt="Background"></img>  
        </div>
        <div id='contentContainer'>
          <img id='logo' src={Logo} alt="Logo"></img>
          <h2>Bienvenido!</h2>
          <label>Bienvenido de nuevo, que placer tenerte acá </label>
          <form>
            <div id='containerUsername'>
              <div><label>Usuario</label></div>
              <input type='text' id='inputUsername' value={username} onChange={(e) => setUsername(e.target.value)}></input>
            </div>
            <div id='containerPassword'>
              <div><label>Contraseña</label></div>
              <input type='password' id='inputPassword' value={password} onChange={(e) => setPassword(e.target.value)}></input>
            </div>
            <div id='containerTarjetaCredito'>
              <div><label>Tarjeta de credito</label></div>
              <input type='tarjetaCredito' id='tarjetaCredito' value={tarjetaCredito} onChange={(e) => settarjetaCredito(e.target.value)}></input>
            </div>
            <button type='button' id='btnIngresar' onClick={login}>Registrarse</button>
          </form>
          <p>¿Ya tienes una cuenta?   <a href="/">Inicia sesión</a></p>
        </div>
      </div>
    </>
  )
}


export default Registro;
