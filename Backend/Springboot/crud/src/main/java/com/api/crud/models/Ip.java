package com.api.crud.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "Ip")    
public class Ip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column()
    private String direccionIp;

    @Column()
    private Integer usuarioFk;

    @Column
    private Date fechaCreacion;

    
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
    public Integer getUsuarioFk() {
        return usuarioFk;
    }

    /**
     * @param usuarioFk the usuarioFk to set
     */
    public void setUsuarioFk(Integer usuarioFk) {
        this.usuarioFk = usuarioFk;
    }

    /**
     * @return Date return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
