package com.api.crud.DTO;

public class ParqueaderoRequest {
    private long ciudad_fk;
    private long parqueadero_id;

    /**
     * @return long return the ciudad_fk
     */
    public long getCiudad_fk() {
        return ciudad_fk;
    }

    /**
     * @param ciudad_fk the ciudad_fk to set
     */
    public void setCiudad_fk(long ciudad_fk) {
        this.ciudad_fk = ciudad_fk;
    }


    /**
     * @return long return the parqueadero_id
     */
    public long getParqueadero_id() {
        return parqueadero_id;
    }

    /**
     * @param parqueadero_id the parqueadero_id to set
     */
    public void setParqueadero_id(long parqueadero_id) {
        this.parqueadero_id = parqueadero_id;
    }

}
