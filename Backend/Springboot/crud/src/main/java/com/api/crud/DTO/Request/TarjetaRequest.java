package com.api.crud.dto.request;
import java.util.Date;

public class TarjetaRequest {
    private String numero;
    private String nombre_propietario;
    private String cvc;
    private Date fecha_vencimiento;
    private long usuario;

        /**
     * @return String return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return String return the nombre_propietario
     */
    public String getNombre_propietario() {
        return nombre_propietario;
    }

    /**
     * @param nombre_propietario the nombre_propietario to set
     */
    public void setNombre_propietario(String nombre_propietario) {
        this.nombre_propietario = nombre_propietario;
    }

    /**
     * @return String return the cvc
     */
    public String getCvc() {
        return cvc;
    }

    /**
     * @param cvc the cvc to set
     */
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    /**
     * @return String return the fecha_vencimiento
     */
    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    /**
     * @param fecha_vencimiento the fecha_vencimiento to set
     */
    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    /**
     * @return long return the usuario
     */
    public long getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }

}
