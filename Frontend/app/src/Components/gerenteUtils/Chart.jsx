import React from 'react';
import { Line } from 'react-chartjs-2';
import { Chart as ChartJS, LineElement, PointElement, LinearScale, Title, CategoryScale } from 'chart.js';

// Registramos los elementos de la gráfica
ChartJS.register(LineElement, PointElement, LinearScale, Title, CategoryScale);

const Chart = () => {
    const data = {
        labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio'],
        datasets: [
            {
                label: 'Ventas',
                data: [65, 59, 80, 81, 56, 55],
                borderColor: 'rgba(75,192,192,1)',
                backgroundColor: 'rgba(75,192,192,0.2)',
            },
        ],
    };

    const options = {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Gráfica de Ventas',
            },
        },
    };

    return <Line data={data} options={options} />;
};

export default Chart;
