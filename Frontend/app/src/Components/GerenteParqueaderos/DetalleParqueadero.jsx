import React from 'react';
import './DetalleParqueadero.css';

function DetalleParqueadero({ parqueaderoDetalles }) {
  return (
    <div className="parqueadero-detalles">
      <h3>Detalles del Parqueadero</h3>
      <table className="tabla-detalles">
        <tbody>
          <tr>
            <td>Nombre:</td>
            <td>{parqueaderoDetalles.nombre}</td>
          </tr>
          <tr>
            <td>Tipo:</td>
            <td>{parqueaderoDetalles.tipo}</td>
          </tr>
          <tr>
            <td>Cupo Total carro:</td>
            <td>{parqueaderoDetalles.cupo_disponible_carro}</td>
          </tr>
          <tr>
            <td>Cupo Total moto:</td>
            <td>{parqueaderoDetalles.cupo_disponible_moto}</td>
          </tr>
          <tr>
            <td>Cupo Total bici:</td>
            <td>{parqueaderoDetalles.cupo_disponible_bici}</td>
          </tr>
          <tr>
            <td>Longitud:</td>
            <td>{parqueaderoDetalles.longitud}</td>
          </tr>
          <tr>
            <td>Latitud:</td>
            <td>{parqueaderoDetalles.latitud}</td>
          </tr>
        </tbody>
      </table>
      <div className="acciones-detalles">
        <button className="accion-editar">Editar</button>
        <button className="accion-mostrar">Mostrar</button>
        <button className="accion-eliminar">Eliminar</button>
      </div>
    </div>
  );
}

export default DetalleParqueadero;
