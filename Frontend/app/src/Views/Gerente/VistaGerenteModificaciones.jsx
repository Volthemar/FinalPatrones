import { useState } from 'react'
import Sidebar from '../../Components/Sidebar/Sidebar.jsx'
import FormCrearCiudad from '../../Components/FormCrearCiudad/FormCrearCiudad.jsx'
import FormCrearParqueadero from '../../Components/FormCrearParqueadero/FormCrearParqueadero.jsx'
import FormModificarParqueadero from '../../Components/FormModificarParqueadero/FormModificarParqueadero.jsx'
import FormModificarCiudad from '../../Components/FormModificarCiudad/FormModificarCiudad.jsx'
import './VistaGerenteModificaciones.css'


function VistaGerenteModificaciones() {
    const [visibilidadCrearCiudad, setVisibilidadCrearCiudad] = useState(false)
    const [visibilidadCrearParqueadero, setVisibilidadCrearParqueadero] = useState(false);
    const [visibilidadModificarCiudad, setVisibilidadModificarCiudad] = useState(false);
    const [visibilidadModificarParqueadero, setVisibilidadModificarParqueadero] = useState(false);
    const [labelClickeado, setLabelClickeado] = useState(null);

    const handleClick = (event) => {
        switch (event.target.id) {
            case 'crearCiudad':
                setVisibilidadCrearCiudad(true);
                setVisibilidadCrearParqueadero(false);
                setVisibilidadModificarCiudad(false);
                setVisibilidadModificarParqueadero(false);
                break;

            case 'crearParqueadero':
                setVisibilidadCrearCiudad(false);
                setVisibilidadCrearParqueadero(true);
                setVisibilidadModificarCiudad(false);
                setVisibilidadModificarParqueadero(false);
                break;

            case 'modificarCiudad':
                setVisibilidadCrearCiudad(false);
                setVisibilidadCrearParqueadero(false);
                setVisibilidadModificarCiudad(true);
                setVisibilidadModificarParqueadero(false);
                break;

            case 'modificarParqueadero':
                setVisibilidadCrearCiudad(false);
                setVisibilidadCrearParqueadero(false);
                setVisibilidadModificarCiudad(false);
                setVisibilidadModificarParqueadero(true);
                break;
        }
    };

    return (
        <div id='view-container'>
            <div id='container-modificaciones'>
                <Sidebar vista='Gerente'></Sidebar>
                <div id='cabecera'>
                    <label className='titulos' id='crearCiudad' 
                    onClick={(e) => {handleClick(e); setLabelClickeado(1)}} 
                    style={{backgroundColor: labelClickeado === 1 ? '#E2F22B' : '#FFFFFF',transition: 'background-color 0.3s ease-in-out'}}>Crear ciudad</label>
                    <label className='titulos' id='crearParqueadero' 
                    onClick={(e) => {handleClick(e); setLabelClickeado(2)}}
                    style={{backgroundColor: labelClickeado === 2 ? '#E2F22B' : '#FFFFFF',transition: 'background-color 0.3s ease-in-out'}}>Crear Parqueadero</label>
                    <label className='titulos' id='modificarCiudad' 
                    onClick={(e) => {handleClick(e); setLabelClickeado(3)}}
                    style={{backgroundColor: labelClickeado === 3 ? '#E2F22B' : '#FFFFFF',transition: 'background-color 0.3s ease-in-out'}}>Modificar ciudad</label>
                    <label className='titulos' id='modificarParqueadero' 
                    onClick={(e) => {handleClick(e); setLabelClickeado(4)}}
                    style={{backgroundColor: labelClickeado === 4 ? '#E2F22B' : '#FFFFFF',transition: 'background-color 0.3s ease-in-out'}}>Modificar parqueadero</label>
                </div>
                <div id='container-funciones'>
                {visibilidadCrearCiudad && (<FormCrearCiudad />)}
                {visibilidadCrearParqueadero && (<FormCrearParqueadero />)}
                {visibilidadModificarParqueadero && (<FormModificarParqueadero />)}
                {visibilidadModificarCiudad && (<FormModificarCiudad />)}
                </div>
            </div>
        </div>

    );
}
export default VistaGerenteModificaciones;