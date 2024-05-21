import Sidebar from '../../Components/Sidebar/Sidebar.jsx'
import './Admin.css'
import Chart from '../../Components/gerenteUtils/Chart.jsx';
function VistaAdmin() {
    return (
        <>
            <Sidebar></Sidebar>
            <div id="contenidoAdmin">
            
            <Chart> </Chart>
            </div>
        </>
    );
}
export default VistaAdmin;