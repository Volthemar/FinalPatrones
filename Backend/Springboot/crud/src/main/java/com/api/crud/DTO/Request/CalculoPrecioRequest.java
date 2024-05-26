package com.api.crud.DTO.Request;

public class CalculoPrecioRequest {
    private Long parqueadero_id;
    private Long vehiculo_id;

    /**
     * @return Long return the parqueadero_id
     */
    public Long getParqueadero_id() {
        return parqueadero_id;
    }

    /**
     * @param parqueadero_id the parqueadero_id to set
     */
    public void setParqueadero_id(Long parqueadero_id) {
        this.parqueadero_id = parqueadero_id;
    }

    /**
     * @return Long return the vehiculo_id
     */
    public Long getVehiculo_id() {
        return vehiculo_id;
    }

    /**
     * @param vehiculo_id the vehiculo_id to set
     */
    public void setVehiculo_id(Long vehiculo_id) {
        this.vehiculo_id = vehiculo_id;
    }

}
