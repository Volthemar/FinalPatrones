import Sidebar from '../../Components/Sidebar/Sidebar.jsx'
import FormCrearCiudad from '../../Components/FormCrearCiudad/FormCrearCiudad.jsx'
import FormCrearParqueadero from '../../Components/FormCrearParqueadero/FormCrearParqueadero.jsx'
import './VistaGerenteImpacto.css'


function VistaGerenteModificaciones() {

    return (
        <>
            {/*<Sidebar vista='Gerente'></Sidebar>*/}
            <FormCrearCiudad/>
            <FormCrearParqueadero/>
        </>
    );
}
export default VistaGerenteModificaciones;