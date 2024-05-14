import { useState } from 'react'
import Logo from '../../assets/logo.png'
import backgroundLogin from '../../assets/backgroundLogin.svg'
import './LoginDiv.css'

function LoginDiv() {
  const URL_POST = 'http://localhost:3241/login';
  const URL_USER = '/user'; 
  const URL_REGISTRO = 'registro';
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [UserDataName, setUserDataName] = useState('')
  const [userId, setUserId] = useState(null);
  const [error, setError] = useState('');

  const irAOtraRuta = () => {
    window.location.href = URL_USER + '/' + userId;
  };

//muchas funciones para el login que luego tienen que separarse
  function login(event) {
    event.preventDefault();

    if (username.trim() === '' || password.trim() === '') {
      alert('Por favor complete todos los campos.');
      return;
    }
    
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    
    let raw = JSON.stringify({
      "usuario": username,
      "contrasena": password
    });

    let requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: raw,
      redirect: "follow"
    };

    
  
    //primer post para usuario y contraseña
    fetch(URL_POST, requestOptions)
      .then(response => {
        if (response.ok) {
          return response.json(); // Procesamos la respuesta JSON si es exitosa
        } else {
          console.log(response);
          alert(response);
          throw new Error('Failed to fetch data'); // Manejamos errores en caso de fallo en la petición
        }
      })
      .then(data => {
        if (data && data.data) {
          const id = data.data.id;
          const nombre = data.data.nombre;
          setUserId(id)
          localStorage.setItem('userId', id);
          localStorage.setItem('userName', nombre);
          promptForAccessCode(userId);
        }
      })
      .catch(error => {
        console.error(error);
        alert('Usuario no válido. Intente nuevamente.'); // Show alert on failed login
      });
      
      //segundo post para el codigo
    function promptForAccessCode(userId) {
      const code = prompt("Escriba el código de acceso:"); 
      fetch(URL_POST, requestOptions)
      .then(response => {
        if (response.ok) {
          return response.json(); // Procesamos la respuesta JSON si es exitosa
        } else {
          console.log(response);
          alert(response);
          throw new Error('Failed to fetch data'); // Manejamos errores en caso de fallo en la petición
        }
      })
      .then(data => {
        if (data && data.data) {
          const id = data.data.id;      // Guardar el id
          const nombre = data.data.nombre; // Guardar el nombre
          const cod_verificacion = data.data.cod_verificacion;
          if (code==cod_verificacion){
            irAOtraRuta(); // Redireccionar al usuario, modificar según tu necesidad
          }
          else{
            alert('codigo incorrecto');
          }
        }
      })
      .catch(error => {
        console.error(error);
        alert('Usuario no válido. Intente nuevamente.');
      });
      
    }
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
              <label>Usuario</label>
              <input type='text' id='inputUsername' value={username} onChange={(e) => setUsername(e.target.value)}></input>
            </div>
            <div id='password'>
              <label>Contraseña</label>
              <input type='password' id='inputPassword' value={password} onChange={(e) => setPassword(e.target.value)}></input>
            </div>
            <div id='cod'>
              <label>Codigo</label>
              <input type='cod' id='inputUserId' value={userId} onChange={(e) => setUserId(e.target.value)}></input>
            </div>
            <button type='button' id='btnIngresar' onClick={login}>Ingresar</button>
          </form>
          <p>Aún no tienes una cuenta? <a href={URL_REGISTRO}>Registrate</a></p>
        </div>
      </div>
    </>
  )
}

export default LoginDiv;
