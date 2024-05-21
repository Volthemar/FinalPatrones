import Sidebar from '../../Components/Sidebar/Sidebar.jsx'
import Historial from '../../Components/Historial/Historial.jsx'
<<<<<<< Updated upstream:Frontend/app/src/Views/Gerente/VistaGerenteImpacto.jsx
import Impacto from '../../Components/Impacto/Impacto.jsx';
import './VistaGerenteImpacto.css'

function VistaGerenteImpacto() {
=======
import './VistaGerente.css'
import CrearCiudad from '../../Components/utilsAdmin/CrearCiudad.jsx';
function VistaGerente() {
>>>>>>> Stashed changes:Frontend/app/src/Views/Gerente/VistaGerente.jsx

    return (
        <>
            <Sidebar vista='gerente' ></Sidebar>
            <CrearCiudad></CrearCiudad>
        </>
    );
}
export default VistaGerenteImpacto;