package com.api.crud.DTO.Request;

import java.util.Date;

public class VerificarDisponibilidadRequest {
    private Long parqueaderoId;
    private Date hora_llegada;
    private Long vehiculoId;

    /**
     * @return Long return the parqueaderoId
     */
    public Long getParqueaderoId() {
        return parqueaderoId;
    }

    /**
     * @param parqueaderoId the parqueaderoId to set
     */
    public void setParqueaderoId(Long parqueaderoId) {
        this.parqueaderoId = parqueaderoId;
    }

    /**
     * @return Date return the hora_llegada
     */
    public Date getHora_llegada() {
        return hora_llegada;
    }

    /**
     * @param hora_llegada the hora_llegada to set
     */
    public void setHora_llegada(Date hora_llegada) {
        this.hora_llegada = hora_llegada;
    }


    /**
     * @return Long return the vehiculoId
     */
    public Long getVehiculoId() {
        return vehiculoId;
    }

    /**
     * @param vehiculoId the vehiculoId to set
     */
    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

}
