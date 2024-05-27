package com.api.crud.DTO.Request;

public class ReservarCupoOfflineRequest {
    private Long parqueaderoId;
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
