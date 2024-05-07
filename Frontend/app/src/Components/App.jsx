import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import NewUser from './NewUser'; // Importando el componente NewUser
import Registro from './Registro';
import Login from './Login/Login.jsx';
import Sidebar from './Sidebar/Sidebar.jsx';
import Gerente from '../Views/Gerente/Gerente.jsx'

function App() {
  return (
    <Gerente></Gerente>
    /*<Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/user/:userId" element={<NewUser />} />
        <Route path="/registro" element={<Registro />} />
        <Route path="/admin" element={<Registro />} />
      </Routes>
    </Router>*/
    
  );
}
export default App;
