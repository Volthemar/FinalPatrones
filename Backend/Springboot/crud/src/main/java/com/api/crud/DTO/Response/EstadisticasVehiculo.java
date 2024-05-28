package com.api.crud.DTO.Response;

public class EstadisticasVehiculo {
    private String tipoVehiculo;
    private int totalCupos;
    private int cuposOcupados;
    private int cuposDisponibles;
    private int ingresosGenerados;

    // Getters and Setters
    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public int getTotalCupos() {
        return totalCupos;
    }

    public void setTotalCupos(int totalCupos) {
        this.totalCupos = totalCupos;
    }

    public int getCuposOcupados() {
        return cuposOcupados;
    }

    public void setCuposOcupados(int cuposOcupados) {
        this.cuposOcupados = cuposOcupados;
    }

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(int cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    public int getIngresosGenerados() {
        return ingresosGenerados;
    }

    public void setIngresosGenerados(int ingresosGenerados) {
        this.ingresosGenerados = ingresosGenerados;
    }
}
