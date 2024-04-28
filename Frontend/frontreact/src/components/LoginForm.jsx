import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/LoginForm.css';

function LoginForm() {
  let navigate = useNavigate();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('https://example.com/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          usuario: username, //TODO EN ESPAÑOL
          contrasena: password,
        }),
      });

      if (response.ok) {
        const data = await response.json();
        // Handle the response data as needed...
        console.log(data);
        navigate('/new-page'); // Redireccionar a la nueva página
      } else {
        // Manejar errores, como mostrar un mensaje al usuario
        throw new Error('Something went wrong on api server!');
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <form className="LoginForm" onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={handleUsernameChange}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={handlePasswordChange}
      />
      <button type="submit">Ingresar</button>
    </form>
  );
}

export default LoginForm;
