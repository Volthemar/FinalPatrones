import { useState } from 'react'
import Logo from '../../assets/logo.png'
import backgroundLogin from '../../assets/backgroundLogin.svg'
import './Login.css'

function Login() {
  const URL_POST = 'http://localhost:3241/login'; // Endpoint para confirmar datos
  const URL_USER = 'user'; // Endpoint del perfil de usuario
  const URL_REGISTRO = 'registro'; // Endpoint para registro
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  function login(event) {
    event.preventDefault();
    if (username.trim() === '' || password.trim() === '') {
      alert('Por favor complete todos los campos.');
      return;
    }
    const userData = {
      usuario: username,
      contrasena: password
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
            <div id='username'>
              <label>Username</label>
              <input type='text' id='inputUsername' value={username} onChange={(e) => setUsername(e.target.value)}></input>
            </div>
            <div id='password'>
              <label>Password</label>
              <input type='password' id='inputPassword' value={password} onChange={(e) => setPassword(e.target.value)}></input>
            </div>
            <button type='button' id='btnIngresar' onClick={login}>Ingresar</button>
          </form>
          <p>Aún no tienes una cuenta? <a href={URL_REGISTRO}>Registrate</a></p>
        </div>
      </div>
    </>
  )
}

export default Login;