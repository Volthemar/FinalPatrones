import { useState } from 'react'
import Logo from '../assets/logo.png' // Placeholder para el logo
import backgroundLogin from '../assets/backgroundLogin.svg'
import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import NewUser from '../Views/Cliente/NewUser'; // Importando el componente NewUser
import { useNavigate } from 'react-router-dom';
import tarjetaCredito from './TarjetaCredito';

function Registro() {
  const URL_POST = 'http://localhost:3241/registroPersona'; // Endpoint para confirmar datos
  const URL_USER = 'user'; // Endpoint del perfil de usuario
  const URL_REGISTRO = '/'; // Endpoint para registro
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [tarjetaCredito, settarjetaCredito] = useState('');
  const navigate = useNavigate();

  function login(event) {
    event.preventDefault();
    if (username.trim() === '' || password.trim() === '' || tarjetaCredito.trim() == '') {
      alert('Por favor complete todos los campos.');
      return;
    }

    let info = {
      "nombre": username,
      "identificacion": password,
      "correo": tarjetaCredito
    }

    fetch(URL_POST, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(info)
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Error al enviar el post');
        }
        return response.json();
      })
      .then(data => {
        console.log("algo bueno");
        console.log(data);
        //navigate(`/`);
      })
      .catch(error => {
        console.error("algo malo");
      });
    /* event.preventDefault();
      if (username.trim() === '' || password.trim() === '' || tarjetaCredito.trim() == '') {
        alert('Por favor complete todos los campos.');
        return;
      }
      const myHeaders = new Headers();
      myHeaders.append("Content-Type", "application/json");
  
      const raw = JSON.stringify({
        "nombre": username,
        "identificacion": password,
        "correo": tarjetaCredito
      });
  
      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
        redirect: "follow"
      };
  
      fetch("http://localhost:3241/registroPersona", requestOptions)
      .then(response => {
          if (response.ok) {
            navigate(`/user/${userId}`, { state: { key: userId } })// Redirect to user profile page on successful login
          } else {
            throw new Error('Usuario no válido'); // Throw error to catch block
          }
        })
        .catch(error => {
          console.error(error);
          alert('Hubo un error.'); // Show alert on failed login
        });*/
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
              <div><label>Identificación</label></div>
              <input type='containerPassword' id='inputPassword' value={password} onChange={(e) => setPassword(e.target.value)}></input>
            </div>
            <div id='containerTarjetaCredito'>
              <div><label>Correo electrónico</label></div>
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
