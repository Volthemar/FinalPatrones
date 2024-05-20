package com.api.crud.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Cupo")
public class CupoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long usuario_fk;
    
    @Column
    private long parqueadero_fk;

    @Column
    private long vehiculo_fk;
    
    @Column
    private boolean pagado;

    @Column
    private Date hora_llegada;

    @Column
    private Date hora_salida;

    @Column
    private int horas_pedidas;

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
     * @return int return the horas_pedidas
     */
    public int getHoras_pedidas() {
        return horas_pedidas;
    }

    /**
     * @param horas_pedidas the horas_pedidas to set
     */
    public void setHoras_pedidas(int horas_pedidas) {
        this.horas_pedidas = horas_pedidas;
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
