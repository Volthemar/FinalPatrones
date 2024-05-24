package com.api.crud.models;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tarifa")
public class TarifaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int valor_ordinario;

    @Column
    private long parqueadero_fk;

    @Column
    private long vehiculo_fk;

    @Column
    private int valor_mora;

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
     * @return int return the valor_ordinario
     */
    public int getValor_ordinario() {
        return valor_ordinario;
    }

    /**
     * @param valor_ordinario the valor_ordinario to set
     */
    public void setValor_ordinario(int valor_ordinario) {
        this.valor_ordinario = valor_ordinario;
    }

    /**
     * @return long return the parqueadero_fk
     */
    public long getParqueadero_fk() {
        return parqueadero_fk;
    }

    /**
     * @param parqueadero_fk the parqueadero_fk to set
     */
    public void setParqueadero_fk(long parqueadero_fk) {
        this.parqueadero_fk = parqueadero_fk;
    }

    /**
     * @return long return the vehiculo_fk
     */
    public long getVehiculo_fk() {
        return vehiculo_fk;
    }

    /**
     * @param vehiculo_fk the vehiculo_fk to set
     */
    public void setVehiculo_fk(long vehiculo_fk) {
        this.vehiculo_fk = vehiculo_fk;
    }

    /**
     * @return int return the valor_mora
     */
    public int getValor_mora() {
        return valor_mora;
    }

    /**
     * @param valor_mora the valor_mora to set
     */
    public void setValor_mora(int valor_mora) {
        this.valor_mora = valor_mora;
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
