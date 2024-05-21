package com.api.crud.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parqueadero")
public class ParqueaderoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String nombre;

    @Column
    private int cupo_moto_total;

    @Column
    private int cupo_carro_total;

    @Column
    private int cupo_bici_total;

    @Column
    private long ciudad_fk;

    @Column
    private long tipo_fk;

    @Column
    private double longitud;

    @Column
    private double latitud;

    @Column
    private int cupo_uti_moto;

    @Column
    private int cupo_uti_carro;

    @Column
    private int cupo_uti_bici;

    @Column
    private Date fecha_creacion;

    @Column
    private long usuario_fk;

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

    /**
     * @return int return the cupo_uti_moto
     */
    public int getCupo_uti_moto() {
        return cupo_uti_moto;
    }

    /**
     * @param cupo_uti_moto the cupo_uti_moto to set
     */
    public void setCupo_uti_moto(int cupo_uti_moto) {
        this.cupo_uti_moto = cupo_uti_moto;
    }

    /**
     * @return int return the cupo_uti_carro
     */
    public int getCupo_uti_carro() {
        return cupo_uti_carro;
    }

    /**
     * @param cupo_uti_carro the cupo_uti_carro to set
     */
    public void setCupo_uti_carro(int cupo_uti_carro) {
        this.cupo_uti_carro = cupo_uti_carro;
    }

    /**
     * @return int return the cupo_uti_bici
     */
    public int getCupo_uti_bici() {
        return cupo_uti_bici;
    }

    /**
     * @param cupo_uti_bici the cupo_uti_bici to set
     */
    public void setCupo_uti_bici(int cupo_uti_bici) {
        this.cupo_uti_bici = cupo_uti_bici;
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
}
