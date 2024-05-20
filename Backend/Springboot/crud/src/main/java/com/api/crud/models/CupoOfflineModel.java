package com.api.crud.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "Cupo_Offline")
public class CupoOfflineModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long parqueader_fk;

    @Column
    private Long vehiculo_fk;

    @Column
    private boolean pagado;

    @Column
    private Date hora_llegada;

    @Column
    private Date hora_salida;

    @Column
    private Date fecha_creacion;

    
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
     * @return Long return the parqueader_fk
     */
    public Long getParqueader_fk() {
        return parqueader_fk;
    }

    /**
     * @param parqueader_fk the parqueader_fk to set
     */
    public void setParqueader_fk(Long parqueader_fk) {
        this.parqueader_fk = parqueader_fk;
    }

    /**
     * @return Long return the vehiculo_fk
     */
    public Long getVehiculo_fk() {
        return vehiculo_fk;
    }

    /**
     * @param vehiculo_fk the vehiculo_fk to set
     */
    public void setVehiculo_fk(Long vehiculo_fk) {
        this.vehiculo_fk = vehiculo_fk;
    }

    /**
     * @return boolean return the pagado
     */
    public boolean isPagado() {
        return pagado;
    }

    /**
     * @param pagado the pagado to set
     */
    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    /**
     * @return Date return the hora_llegada
     */
    public Date getHora_llegada() {
        return hora_llegada;
    }

    /**
     * @param hora_llegada the hora_llegada to set
     */
    public void setHora_llegada(Date hora_llegada) {
        this.hora_llegada = hora_llegada;
    }

    /**
     * @return Date return the hora_salida
     */
    public Date getHora_salida() {
        return hora_salida;
    }

    /**
     * @param hora_salida the hora_salida to set
     */
    public void setHora_salida(Date hora_salida) {
        this.hora_salida = hora_salida;
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
