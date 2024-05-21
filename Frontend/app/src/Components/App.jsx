import './App.css'
import Cliente from '../Views/Cliente/PerfilCliente'; // Importando el componente NewUser
import Registro from '../Views/Registro/VistaRegistro';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Impacto from '../Views/Gerente/VistaGerenteImpacto.jsx';
import Modificaciones from '../Views/Gerente/VistaGerenteModificaciones.jsx' ;
import LogIn from '../Views/Login/VistaLogin'
import Codigo from '../Components/Codigo/Codigo';
import TarjetaCredito from './Registro/TarjetaCredito';
import CrearParqueadero from './utilsAdmin/CrearParqueadero';
import LandingPage from './LangingPage/LandingPage';
import CrearCiudad from './utilsAdmin/CrearCiudad';
import ParkingManagement from './gerenteUtils/ParkingManagement';
import Admin from '../Views/Admin/Admin';
import GerenteParqueaderos from './GerenteParqueaderos/GerenteParqueaderos.jsx';
function App() {
  return (
    <Router>
      <Routes>
        {/*Listos*/}
        <Route path="/" element={<LandingPage />} />
        <Route path="/login" element={<LogIn />} />
        <Route path="/registro" element={<Registro />} />

        {/*Mejorables*/}
        <Route path="/user/:userId" element={<Cliente />} />      
        
        {/*Detalles por definir*/}
        <Route path="/Gerente/Impacto" element={<Impacto />} />

        {/*Por trabajar*/}
        <Route path="/Gerente/Modificaciones" element={<Modificaciones/>} />
        <Route path="/Gerente/VerComo" element={<Impacto />} />
        <Route path="/Gerente/Cuentas" element={<Impacto />} />
             

        <Route path="/Gerente/CrearParqueadero" element={<CrearParqueadero />} />        
        <Route path="/Gerente/CrearCiudad" element={<CrearCiudad />} />
        
        <Route path="/Gerente/GerenteParqueaderos" element={<GerenteParqueaderos />} />
        <Route path="/Admin/Administracion" element={<ParkingManagement />} />

        {/*Prescindibles*/}
        <Route path="/Admin" element={<Admin />} />

        {/*No funcionales*/}
        <Route path="/tarjeta" element={<TarjetaCredito />} />
        <Route path="/codigo" element={<Codigo />} />
      </Routes>
    </Router>
  );
}

export default App;
