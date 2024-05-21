package com.api.crud.DTO.Request;

public class OcuparCupoOfflineRequest{
    private Long parqueaderoId;
    private Long vehiculoId;
    private String nombreCliente;

    // Getters and Setters
    public Long getParqueaderoId() {
        return parqueaderoId;
    }

    public void setParqueaderoId(Long parqueaderoId) {
        this.parqueaderoId = parqueaderoId;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}