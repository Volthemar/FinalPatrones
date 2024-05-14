import './Historial.css'


function Historial() {
    const URL_USER_HISTORY = 'endpointxd';
    /*
    fetch(URL_USER_HISTORY)
        .then(response => {
            if (!response.ok) {
                throw new Error('problema xd');
            }
            return response.json();
        })
        .then(data => {
           console.log(data);
        })
        .catch(error => {
            console.error('bromitaxd: ', error);
        });
    */
    const usageHistory = [
        { 
            status: "En curso",
            date: "11 Mar 2023", 
            location: "Parkway", 
            time: "En progreso...", 
            amount: "$ -" 
        },
        { 
            status: "Finalizado",
            date: "9 Mar 2023", 
            location: "Rosales", 
            time: "4m 23s", 
            amount: "$ 7950" 
        },
        { 
            status: "Finalizado",
            date: "9 Mar 2023", 
            location: "Universidad Javeriana", 
            time: "38m 13s", 
            amount: "$ 2550" 
        }
    ];

    const agrupadoPorFecha = {};
    usageHistory.forEach(item => {
        const date = item.date;
        if (!agrupadoPorFecha[date]) {
            agrupadoPorFecha[date] = [];
        }
        agrupadoPorFecha[date].push(item);
    });
    console.log(agrupadoPorFecha);

    return (
        <section className="historial-usuario">
            <h2>Historial de usos</h2>
            {
                Object.keys(agrupadoPorFecha).map(fecha =>
                    <div className='registro' key={fecha}>
                        <label className='fecha'>{fecha}</label>
                        <hr/>
                        <ul>
                            {agrupadoPorFecha[fecha].map((registro, index) =>
                                <li key={index}>
                                    {registro.status === 'Finalizado' ? (
                                        <label className="statusFinalizado"></label>
                                    ): (registro.status === 'En curso' || registro.status === 'Proximo') ? (
                                        <label className="statusActivo"></label>
                                    ) : (
                                        null
                                    )}
                                    <label className="ubicacion">{registro.location}</label>
                                    <label className="tiempo">{registro.time}</label>
                                    <label className="costo">{registro.amount}</label>
                                </li>
                            )}
                        </ul>
                    </div>
            )
        }
            {/*
            <ul>
                {usageHistory.map((registro) => (
                    <li>
                        <div className="Date">{registro.date}</div>
                        <div className="Locations">{registro.location}</div>
                        <div className="Status">{registro.status}</div>
                        <div className="Time">{registro.time}</div>
                        <div className="Amount">{registro.amount}</div>
                    </li>
                ))}
            </ul>*/}
        </section>
    )
}

export default Historial;