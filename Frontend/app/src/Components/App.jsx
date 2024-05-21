import './App.css'
import Cliente from '../Views/Cliente/PerfilCliente'; // Importando el componente NewUser
import Registro from '../Views/Registro/VistaRegistro';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Gerente from '../Views/Gerente/VistaGerente';
import LogIn from '../Views/Login/VistaLogin'
import Codigo from '../Components/Codigo/Codigo';
import TarjetaCredito from './Registro/TarjetaCredito';
import Ciudad from './utilsAdmin/ciudad';

import Impacto from '../Components/Impacto/Impacto.jsx'

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LogIn />} />
        <Route path="/user/:userId" element={<Cliente />} />
        <Route path="/registro" element={<Registro />} />
        <Route path="/admin" element={<Gerente />} />
        <Route path="/gerente" element={<Gerente />}/>
        <Route path="gerente/Impacto" element={<Impacto/>}/>
        <Route path="/tarjeta" element={<TarjetaCredito />} />
        <Route path="/codigo" element={<Codigo />} />
        <Route path="/admin/ciudad" element={<Ciudad />} />
      </Routes>
    </Router>
  );
}

export default App;
