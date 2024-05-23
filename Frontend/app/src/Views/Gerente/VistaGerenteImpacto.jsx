import Sidebar from '../../Components/Sidebar/Sidebar.jsx'
import Impacto from '../../Components/Impacto/Impacto.jsx'
import './VistaGerenteImpacto.css'
function VistaGerenteImpacto() {

    return (
        <>
            <Sidebar vista='gerente' ></Sidebar>
            <Impacto/>
        </>
    );
}
export default VistaGerenteImpacto;