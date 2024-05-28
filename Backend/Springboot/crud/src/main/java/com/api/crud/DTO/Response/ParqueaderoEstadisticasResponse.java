package com.api.crud.DTO.Response;

import java.util.List;

public class ParqueaderoEstadisticasResponse {
    private List<String> labels;
    private List<Integer> cuposTotales;
    private List<Integer> cuposOcupados;
    private List<Integer> cuposDisponibles;
    private List<Integer> ingresos;

    // Getters and Setters
    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Integer> getCuposTotales() {
        return cuposTotales;
    }

    public void setCuposTotales(List<Integer> cuposTotales) {
        this.cuposTotales = cuposTotales;
    }

    public List<Integer> getCuposOcupados() {
        return cuposOcupados;
    }

    public void setCuposOcupados(List<Integer> cuposOcupados) {
        this.cuposOcupados = cuposOcupados;
    }

    public List<Integer> getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(List<Integer> cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    public List<Integer> getingresos() {
        return ingresos;
    }

    public void setIngresos(List<Integer> ingresos) {
        this.ingresos = ingresos;
    }
}
