import Sidebar from '../../Components/Sidebar/Sidebar.jsx'
import './VistaGerente.css'

function VistaGerente() {
    return (
        <>
            <Sidebar></Sidebar>
            <div id="contenidoGerente">
            {/*<Router>
                <Routes>
                    <Route path="/Impacto" element={A} />
                    <Route path="/Ubicaciones" element={B} />
                    <Route path="/Cuentas" element={C} />
                    <Route path="/Trazabilidad" element={D} />
                    <Route path="/Modificaciones" element={E} />
                    <Route path="/Rentabilidad" element={F} />
                </Routes>
            </Router>*/}
                <h2>Estadísticas de uso</h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Maiores, quae adipisci. Ea quaerat 
                    quidem unde placeat sequi, rem quae, ipsum illo enim nulla ad suscipit esse, earum amet minus 
                    veritatis!</p>
            </div>
        </>
    );
}
export default VistaGerente;