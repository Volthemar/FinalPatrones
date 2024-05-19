import './App.css'
import Cliente from '../Views/Cliente/PerfilCliente'; // Importando el componente NewUser
import Registro from '../Views/Registro/VistaRegistro';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Gerente from '../Views/Gerente/VistaGerente';
import LogIn from '../Views/Login/VistaLogin'
import Codigo from '../Components/Codigo/Codigo';
import TarjetaCredito from './Registro/TarjetaCredito';
import Ciudad from './utilsAdmin/ciudad';
import LandingPage from './LangingPage/LandingPage';
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LogIn />} />
        <Route path="/" element={<LandingPage />} />
        <Route path="/user/:userId" element={<Cliente />} />
        <Route path="/registro" element={<Registro />} />
        <Route path="/admin" element={<Gerente />} />
        <Route path="/gerente" element={<Gerente />} />
        <Route path="/tarjeta" element={<TarjetaCredito />} />
        <Route path="/codigo" element={<Codigo />} />
        <Route path="/admin/ciudad" element={<Ciudad />} />
      </Routes>
    </Router>
  );
}

export default App;
