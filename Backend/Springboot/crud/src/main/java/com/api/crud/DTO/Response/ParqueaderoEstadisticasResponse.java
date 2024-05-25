package com.api.crud.DTO.Response;

import java.util.Map;

public class ParqueaderoEstadisticasResponse {
    private long parqueaderoId;
    private int totalCuposCarro;
    private int totalCuposMoto;
    private int totalCuposBici;
    private int cuposOcupadosCarro;
    private int cuposOcupadosMoto;
    private int cuposOcupadosBici;
    private Map<String, Integer> ingresosPorVehiculo;

    // Getters and setters
    public long getParqueaderoId() {
        return parqueaderoId;
    }

    public void setParqueaderoId(long parqueaderoId) {
        this.parqueaderoId = parqueaderoId;
    }

    public int getTotalCuposCarro() {
        return totalCuposCarro;
    }

    public void setTotalCuposCarro(int totalCuposCarro) {
        this.totalCuposCarro = totalCuposCarro;
    }

    public int getTotalCuposMoto() {
        return totalCuposMoto;
    }

    public void setTotalCuposMoto(int totalCuposMoto) {
        this.totalCuposMoto = totalCuposMoto;
    }

    public int getTotalCuposBici() {
        return totalCuposBici;
    }

    public void setTotalCuposBici(int totalCuposBici) {
        this.totalCuposBici = totalCuposBici;
    }

    public int getCuposOcupadosCarro() {
        return cuposOcupadosCarro;
    }

    public void setCuposOcupadosCarro(int cuposOcupadosCarro) {
        this.cuposOcupadosCarro = cuposOcupadosCarro;
    }

    public int getCuposOcupadosMoto() {
        return cuposOcupadosMoto;
    }

    public void setCuposOcupadosMoto(int cuposOcupadosMoto) {
        this.cuposOcupadosMoto = cuposOcupadosMoto;
    }

    public int getCuposOcupadosBici() {
        return cuposOcupadosBici;
    }

    public void setCuposOcupadosBici(int cuposOcupadosBici) {
        this.cuposOcupadosBici = cuposOcupadosBici;
    }

    public Map<String, Integer> getIngresosPorVehiculo() {
        return ingresosPorVehiculo;
    }

    public void setIngresosPorVehiculo(Map<String, Integer> ingresosPorVehiculo) {
        this.ingresosPorVehiculo = ingresosPorVehiculo;
    }
}