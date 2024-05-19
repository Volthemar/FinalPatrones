package com.api.crud.DTO;

public class ParqueaderoRequest {
    private long ciudad_fk;
    

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

}
