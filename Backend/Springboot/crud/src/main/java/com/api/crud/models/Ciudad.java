package com.api.crud.models;

import org.springframework.data.geo.Point;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ciudad")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String nombre;

    @Column(name = "coordenada", columnDefinition = "POINT")
    private Point coordenada;
    
    @Column
    private Date fecha_creacion;

    

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
     * @return Point return the coordenada
     */
    public Point getCoordenada() {
        return coordenada;
    }

    /**
     * @param coordenada the coordenada to set
     */
    public void setCoordenada(Point coordenada) {
        this.coordenada = coordenada;
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

}
