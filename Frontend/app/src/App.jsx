import { useState } from 'react'
import Logo from '/vite.svg' // REEMPLAZAR CUANDO METAN EL LOGO
import backgroundLogin from './assets/backgroundLogin.svg'
import './App.css'

function Login() {
  const URL_POST = 'unaURLxd'; // aca va el endpoint de confirmar datos
  const URL_USER = 'otraURLxd'; // Aca va el endpoint de ya el perfil de cada usuario 
  const URL_REGISTRO = 'otraURLx2'; //Aca va el endpoint que lleva a registro o la funcion whatever
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');


  function login(event) {
    event.preventDefault();
    if (username.trim() === '' || password.trim() === '') {
      alert('Por favor complete todos los campos.');
      return;
    }
    const userData = {
      username: username, //Clave modificable segun back
      password: password // x2
    };
    fetch(URL_POST, {
              method: 'POST',
              body: JSON.stringify(userData),
              headers: {
                'Content-Type': 'application/json'
              }
            })
              .then(response => {
                let urlActual = window.location.href;
                window.location.href = urlActual + URL_USER;
              })
              .catch(error => console.error(error));
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
          <label>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer libero arcu</label>
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

function App() {
  return (
    <Login />
  )
}

export default App
