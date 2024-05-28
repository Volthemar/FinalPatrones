package com.api.crud.models;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tarjeta_credito")
public class TarjetaCreditoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String numero;

    @Column
    private String nombre_propietario;

    @Column
    private String cvc;

    @Column
    private Date fecha_vencimiento;

    @Column
    private long usuario_fk;

    @Column
    private Date fecha_creacion;

    @Column
    private Boolean activo;

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
     * @return Date return the fecha_vencimiento
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
     * @return long return the usuario_fk
     */
    public long getUsuario_fk() {
        return usuario_fk;
    }

    /**
     * @param usuario_fk the usuario_fk to set
     */
    public void setUsuario_fk(long usuario_fk) {
        this.usuario_fk = usuario_fk;
    }

    /**
     * @return Date return the fecha_creacion
     */
    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    /**
     * @param fecha_creacion the fecha_creacion to set
     */
    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    /**
     * @return boolean return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @return Boolean return the activo
     */
    public Boolean getactivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

}
