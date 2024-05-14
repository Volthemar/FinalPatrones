import { useState } from 'react'
import Logo from '../../assets/logo.png' // Placeholder para el logo
import backgroundLogin from '../../assets/backgroundLogin.svg'
import '../Login/LoginDiv.css'
import { useNavigate } from 'react-router-dom';

function RegistroDiv() {
  const URL_POST = 'http://localhost:3241/registroPersona'; // Endpoint para confirmar datos
  const URL_USER = 'user'; // Endpoint del perfil de usuario
  const URL_REGISTRO = '/'; // Endpoint para registro
  const [nombre, setNombre] = useState('');
  const [identificacion, setIdentificacion] = useState('');
  const [correo, setCorreo] = useState('');
  const navigate = useNavigate();

  function registrar(event) {
    event.preventDefault();
    if (nombre.trim() === '' || identificacion.trim() === '' || correo.trim() == '') {
      alert('Por favor complete todos los campos.');
      return;
    }

    let info = {
      "nombre": nombre,
      "identificacion": identificacion,
      "correo": correo
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
      })
      .catch(error => {
        console.error("algo malo");
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
          <h2>Registrate!</h2>
          <label>Un gusto que te unas a nosotros!</label>
          <form>
            <div id='containerUsername'>
              <div><label>Nombre</label></div>
              <input type='text' id='inputUsername' value={nombre} onChange={(e) => setNombre(e.target.value)}></input>
            </div>
            <div id='containerPassword'>
              <div><label>Identificación</label></div>
              <input type='identificacion' id='inputPassword' value={identificacion} onChange={(e) => setIdentificacion(e.target.value)}></input>
            </div>
            <div id='containerTarjetaCredito'>
              <div><label>Correo electrónico</label></div>
              <input type='correo' id='correo' value={correo} onChange={(e) => setCorreo(e.target.value)}></input>
            </div>
            <button type='button' id='btnIngresar' onClick={registrar}>Registrarse</button>
          </form>
          <p>¿Ya tienes una cuenta?   <a href="/">Inicia sesión</a></p>
        </div>
      </div>
    </>
  )
}

export default RegistroDiv;
