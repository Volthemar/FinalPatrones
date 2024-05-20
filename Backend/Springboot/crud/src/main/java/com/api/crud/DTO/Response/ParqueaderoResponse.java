package com.api.crud.DTO.Response;

public class ParqueaderoResponse {
    private long id;
    private String nombre;
    private int cupo_disponible_carro;
    private int cupo_disponible_moto;
    private int cupo_disponible_bici;
    private String tipo;
    private double longitud;
    private double latitud;
    private String color;

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
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
     * @return int return the cupo_disponible_carro
     */
    public int getCupo_disponible_carro() {
        return cupo_disponible_carro;
    }

    /**
     * @param cupo_disponible_carro the cupo_disponible_carro to set
     */
    public void setCupo_disponible_carro(int cupo_disponible_carro) {
        this.cupo_disponible_carro = cupo_disponible_carro;
    }

    /**
     * @return int return the cupo_disponible_moto
     */
    public int getCupo_disponible_moto() {
        return cupo_disponible_moto;
    }

    /**
     * @param cupo_disponible_moto the cupo_disponible_moto to set
     */
    public void setCupo_disponible_moto(int cupo_disponible_moto) {
        this.cupo_disponible_moto = cupo_disponible_moto;
    }

    /**
     * @return int return the cupo_disponible_bici
     */
    public int getCupo_disponible_bici() {
        return cupo_disponible_bici;
    }

    /**
     * @param cupo_disponible_bici the cupo_disponible_bici to set
     */
    public void setCupo_disponible_bici(int cupo_disponible_bici) {
        this.cupo_disponible_bici = cupo_disponible_bici;
    }

    /**
     * @return String return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    /**
     * @return String return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

}
