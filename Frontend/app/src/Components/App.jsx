import './App.css'
import Cliente from '../Views/Cliente/PerfilCliente'; // Importando el componente NewUser
import Registro from '../Views/Registro/VistaRegistro';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Gerente from '../Views/Gerente/VistaGerente';
import LogIn from '../Views/Login/VistaLogin'
import Codigo from '../Components/Codigo/Codigo';
import TarjetaCredito from './Registro/TarjetaCredito';
import CrearParqueadero from './utilsAdmin/CrearParqueadero';
import LandingPage from './LangingPage/LandingPage';
import CrearCiudad from './utilsAdmin/CrearCiudad';
import ParkingManagement from './gerenteUtils/ParkingManagement';
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
        <Route path="/gerente/CrearParqueadero" element={<CrearParqueadero />} />        
        <Route path="/gerente/CrearCiudad" element={<CrearCiudad />} />
        <Route path="/admin/Administracion" element={<ParkingManagement />} />
      </Routes>
    </Router>
  );
}

export default App;
