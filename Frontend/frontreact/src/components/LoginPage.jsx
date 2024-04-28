import React from 'react';
import WelcomeBanner from './WelcomeBanner';
import LoginForm from './LoginForm';
import RegisterLink from './RegisterLink';
import Background from './Background'; // Incluyendo el Background
import '../styles/CardContainer.css';
import '../styles/LoginPage.css';

function LoginPage() {
  return (
    <Background> {/* Envolver todo en el componente Background */}
      <div className="LoginPage">
        <div className="CardContainer"> {/* Contenedor de la tarjeta */}
          <WelcomeBanner />
          <LoginForm />
          <RegisterLink />
        </div>
      </div>
    </Background>
  );
}

export default LoginPage;