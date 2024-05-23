import Sidebar from '../../Components/Sidebar/Sidebar.jsx'
import FormCrearCiudad from '../../Components/FormCrearCiudad/FormCrearCiudad.jsx'
import FormCrearParqueadero from '../../Components/FormCrearParqueadero/FormCrearParqueadero.jsx'
import FormModificarParqueadero from '../../Components/FormModificarParqueadero/FormModificarParqueadero.jsx'
import FormModificarCiudad from '../../Components/FormModificarCiudad/FormModificarCiudad.jsx'
import './VistaGerenteImpacto.css'


function VistaGerenteModificaciones() {

    return (
        <>
            {/*<Sidebar vista='Gerente'></Sidebar>*/}
            <FormCrearCiudad/>
            <FormCrearParqueadero/>
            <FormModificarParqueadero/>
            <FormModificarCiudad/>
        </>
    );
}
export default VistaGerenteModificaciones;