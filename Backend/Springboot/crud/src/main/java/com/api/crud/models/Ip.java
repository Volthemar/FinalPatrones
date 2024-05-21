package com.api.crud.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Ip")
public class Ip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column()
    private String direccionIp;

    @Column()
    private Long usuarioFk;

    @Column
    private Timestamp fechaCreacion;

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the direccionIp
     */
    public String getDireccionIp() {
        return direccionIp;
    }

    /**
     * @param direccionIp the direccionIp to set
     */
    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    /**
     * @return Integer return the usuarioFk
     */
    public Long getUsuarioFk() {
        return usuarioFk;
    }

    /**
     * @param usuarioFk the usuarioFk to set
     */
    public void setUsuarioFk(Long usuarioFk) {
        this.usuarioFk = usuarioFk;
    }

    /**
     * @return Date return the fechaCreacion
     */
    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
