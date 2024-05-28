import React, { useState, useEffect } from 'react';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './Pago.css';
import Factura from '../Factura/Factura';

export default function Pago({ isOpen, onClose, data }) {
    const [precio, setPrecio] = useState(null);
    const [tarjetas, setTarjetas] = useState([]);
    const [selectedTarjeta, setSelectedTarjeta] = useState('');
    const [isFacturaOpen, setIsFacturaOpen] = useState(false);
    const [facturaData, setFacturaData] = useState(null);

    useEffect(() => {
        if (isOpen && data) {
            const fetchTarifa = async () => {
                try {
                    const response = await fetch('http://localhost:3241/tarifaParqueaderoVehiculo', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify({
                            parqueadero_fk: data.idParqueadero,
                            vehiculo_fk: data.vehiculoId,
                            horas: data.horas,
                        }),
                    });
                    const result = await response.json();
                    if (response.ok) {
                        setPrecio(result.data.Precio);
                    } else {
                        alert(result.msg || 'Error al obtener el precio');
                    }
                } catch (error) {
                    alert('Error al obtener el precio');
                }
            };

            const fetchTarjetas = async () => {
                const usuarioId = localStorage.getItem('userId');
                console.log(usuarioId);
                try {
                    const response = await fetch('http://localhost:3241/tarjetaUsuario', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify({ usuario: parseInt(usuarioId, 10) }),
                    });
                    const result = await response.json();
                    if (response.ok) {
                        setTarjetas(result.data);
                        setSelectedTarjeta(result.data[0]?.id || '');
                    } else {
                        alert(result.msg || 'Error al obtener las tarjetas');
                    }
                } catch (error) {
                    alert('Error al obtener las tarjetas');
                }
            };

            fetchTarifa();
            fetchTarjetas();
        }
    }, [isOpen, data]);

    const handleTarjetaChange = (e) => {
        setSelectedTarjeta(e.target.value);
    };

    const handlePagoClose = () => {
        setPrecio(null);
        setTarjetas([]);
        setSelectedTarjeta('');
        onClose();
    };

    const handlePago = async () => {
        try {
            const response = await fetch('http://localhost:3241/reservarCupo', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    tarjetaId: selectedTarjeta,
                    usuarioId: data.usuarioId,
                    parqueaderoId: data.idParqueadero,
                    vehiculoId: data.vehiculoId,
                    hora_llegada: data.hora_llegada,
                    horas: data.horas,
                }),
            });

            const result = await response.json();
            if (response.ok) {
                toast.success(`Cupo reservado con éxito. Código: ${result.data.codigo}`);
                setFacturaData({
                    codigo: result.data.codigo,
                    horaLlegada: data.hora_llegada,
                    vehiculo: data.vehiculoId,
                    parqueadero: data.idParqueadero,
                    ciudad: 'Ciudad Ejemplo', 
                    horasPedidas: data.horas,
                });
                setIsFacturaOpen(true);
            } else {
                toast.error(result.msg || 'Error al reservar el cupo');
            }
        } catch (error) {
            toast.error('Error al reservar el cupo, intente nuevamente');
        }
    };

    if (!isOpen || !data) return null;

    return (
        <div className="pago-backdrop">
            <div className="pago-container">
                <ToastContainer />
                <h2>Valor a pagar : {precio} $</h2>
                <div className="pago-tarjeta">
                    <label>Seleccionar tarjeta de crédito:</label>
                    <select value={selectedTarjeta} onChange={handleTarjetaChange}>
                        {tarjetas.map((tarjeta) => (
                            <option key={tarjeta.id} value={tarjeta.id}>
                                {tarjeta.numero} - {tarjeta.nombre_propietario}
                            </option>
                        ))}
                    </select>
                </div>
                <button className="pago-button" onClick={handlePago}>PAGAR</button>
                <button className="cerrar" onClick={handlePagoClose}>Cerrar</button>
                {isFacturaOpen && <Factura data={facturaData} />}
            </div>
        </div>
    );
}
