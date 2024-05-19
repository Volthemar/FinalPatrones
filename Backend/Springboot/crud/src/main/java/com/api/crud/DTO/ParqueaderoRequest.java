package com.api.crud.DTO;


public class ParqueaderoRequest {
    private long parqueadero_id;
    private long ciudad_fk;
    private String nombre;
    private int cupo_moto_total;
    private int cupo_carro_total;
    private int cupo_bici_total;
    private long tipo_fk;
    private double longitud;
    private double latitud;

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


    /**
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return int return the cupo_moto_total
     */
    public int getCupo_moto_total() {
        return cupo_moto_total;
    }

    /**
     * @param cupo_moto_total the cupo_moto_total to set
     */
    public void setCupo_moto_total(int cupo_moto_total) {
        this.cupo_moto_total = cupo_moto_total;
    }

    /**
     * @return int return the cupo_carro_total
     */
    public int getCupo_carro_total() {
        return cupo_carro_total;
    }

    /**
     * @param cupo_carro_total the cupo_carro_total to set
     */
    public void setCupo_carro_total(int cupo_carro_total) {
        this.cupo_carro_total = cupo_carro_total;
    }

    /**
     * @return int return the cupo_bici_total
     */
    public int getCupo_bici_total() {
        return cupo_bici_total;
    }

    /**
     * @param cupo_bici_total the cupo_bici_total to set
     */
    public void setCupo_bici_total(int cupo_bici_total) {
        this.cupo_bici_total = cupo_bici_total;
    }

    /**
     * @return long return the tipo_fk
     */
    public long getTipo_fk() {
        return tipo_fk;
    }

    /**
     * @param tipo_fk the tipo_fk to set
     */
    public void setTipo_fk(long tipo_fk) {
        this.tipo_fk = tipo_fk;
    }

    /**
     * @return double return the longitud
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * @return double return the latitud
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}
