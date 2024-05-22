import React from 'react';
import { Bar, Pie } from 'react-chartjs-2';
import { Chart as ChartJS, BarElement, ArcElement, CategoryScale, LinearScale, Tooltip, Legend } from 'chart.js';
import './Chart.css';

ChartJS.register(BarElement, ArcElement, CategoryScale, LinearScale, Tooltip, Legend);


const Chart = () => {
    const dataBar1 = {
        labels: ['Autos', 'Motos', 'Bicicletas'],
        datasets: [
            {
                label: 'Disponible',
                data: [25, 38, 45],
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1,
            },
            {
                label: 'Ocupado',
                data: [75, 62, 55],
                backgroundColor: 'rgba(255, 99, 132, 0.5)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1,
            },
        ],
    };

    const dataPie1 = {
        labels: ['Clientes logeados', 'Visitan la aplicación'],
        datasets: [
            {
                data: [33, 67],
                backgroundColor: ['rgba(54, 162, 235, 0.5)', 'rgba(255, 99, 132, 0.5)'],
                borderColor: ['rgba(54, 162, 235, 1)', 'rgba(255, 99, 132, 1)'],
                borderWidth: 1,
            },
        ],
    };

    const dataPie2 = {
        labels: ['Libre', 'Ocupado'],
        datasets: [
            {
                data: [44, 56],
                backgroundColor: ['rgba(54, 162, 235, 0.5)', 'rgba(255, 99, 132, 0.5)'],
                borderColor: ['rgba(54, 162, 235, 1)', 'rgba(255, 99, 132, 1)'],
                borderWidth: 1,
            },
        ],
    };

    const dataBar2 = {
        labels: ['Cali 2', 'Cali 1', 'Villavicencio 1', 'Barranquilla 1', 'Antioquia 2', 'Antioquia 1', 'Bogotá 3', 'Bogotá 2', 'Bogotá 1'],
        datasets: [
            {
                label: 'Disponibilidad',
                data: [80, 60, 100, 87, 35, 15, 84, 36, 0],
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1,
            },
            {
                label: 'Ocupado',
                data: [20, 40, 0, 13, 65, 85, 16, 64, 100],
                backgroundColor: 'rgba(255, 99, 132, 0.5)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1,
            },
        ],
    };

    return (
        <div className="dashboard-charts">
            <div className="chart">
                <h2>Comportamiento por tipo de vehículo</h2>
                <Bar data={dataBar1} options={{ responsive: true, plugins: { legend: { position: 'top' }, title: { display: true, text: 'Comportamiento por tipo de vehículo' } } }} />
            </div>
            <div className="chart">
                <h2>Comportamiento de la aplicación</h2>
                <Pie data={dataPie1} options={{ responsive: true, plugins: { legend: { position: 'top' }, title: { display: true, text: 'Comportamiento de la aplicación' } } }} />
            </div>
            <div className="chart">
                <h2>Ocupado vs libre</h2>
                <Pie data={dataPie2} options={{ responsive: true, plugins: { legend: { position: 'top' }, title: { display: true, text: 'Ocupado vs libre' } } }} />
            </div>
            <div className="chart">
                <h2>Disponibilidad vs Ocupado</h2>
                <Bar data={dataBar2} options={{ responsive: true, plugins: { legend: { position: 'top' }, title: { display: true, text: 'Disponibilidad vs Ocupado' } } }} />
            </div>
        </div>
    );
};

export default Chart;
