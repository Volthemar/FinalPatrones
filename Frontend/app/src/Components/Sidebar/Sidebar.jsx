import React, { useState, useEffect } from 'react';
import SidebarButton from '../SidebarButton/SidebarButton.jsx'
import URL_IMG_USER from '../../assets/Persona.svg'
import URL_IMPACTO from '../../assets/LogoImpacto.svg'
import URL_UBICACIONES from '../../assets/LogoUbicaciones.svg'
import URL_CUENTAS from '../../assets/LogoCuentas.svg'
import URL_TRAZABILIDAD from '../../assets/LogoTrazabilidad.svg'
import URL_MODIFICACIONES from '../../assets/LogoModificaciones.svg'
import URL_RENTABILIDAD from '../../assets/LogoRentabilidad.svg'
import URL_CERRAR_SESION from '../../assets/LogoCerrarSesion.svg'

import './Sidebar.css'

function Sidebar() {
    const [usuario, setUsuario] = useState([]);
    const URL_NOMBRE_USUARIO = '/endpointxd'
    useEffect(() => {
        fetch(URL_NOMBRE_USUARIO)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Respuesta no ok :(');
                }
                return response.json();
            })
            .then(data => {
                setUsuario(data);
            })
            .catch(error => {
                console.error('aun no xd', error);
            });
    }, []);
  return (
    <div id='sidebar'>
        <div id='usuario'>
            <img src={URL_IMG_USER} alt="" />
            <label>Pedro Navaja</label>
        </div>
        <form>
            <input type='text' id='busqueda' placeholder='Filtrar...'/>
        </form>
        <div id='botones'>
            <SidebarButton URL_BTN='/Impacto' nombre='Impacto' URL_IMG={URL_IMPACTO}></SidebarButton>
            <SidebarButton URL_BTN='/Ubicaciones' nombre='Ubicaciones' URL_IMG={URL_UBICACIONES}></SidebarButton>
            <SidebarButton URL_BTN='/Cuentas' nombre='Cuentas' URL_IMG={URL_CUENTAS}></SidebarButton>
            <SidebarButton URL_BTN='/Trazabilidad' nombre='Trazabilidad' URL_IMG={URL_TRAZABILIDAD}></SidebarButton>
            <SidebarButton URL_BTN='/Modificaciones' nombre='Modificaciones' URL_IMG={URL_MODIFICACIONES}></SidebarButton>
            <SidebarButton URL_BTN='/Rentabilidad' nombre='Rentabilidad' URL_IMG={URL_RENTABILIDAD}></SidebarButton>
        </div>
        <div id='logout'>
            <SidebarButton URL_BTN='/logout' nombre='Cerrar sesión' URL_IMG={URL_CERRAR_SESION}></SidebarButton>
        </div>
    </div>
  );
}
export default Sidebar;