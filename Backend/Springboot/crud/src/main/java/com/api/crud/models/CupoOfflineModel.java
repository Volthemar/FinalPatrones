package com.api.crud.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "Cupo_Offline")
public class CupoOfflineModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private long parqueadero_fk;

    @Column
    private Long vehiculo_fk;

    @Column
    private boolean pagado;

    @Column
    private Timestamp hora_llegada;

    @Column
    private Timestamp hora_salida;

    @Column
    private Timestamp fecha_creacion;

    @Column
    private String nombre_cliente;

    @Column
    private Boolean activo;

    @Column
    private String codigo;

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
    public Long getParqueadero_fk() {
        return parqueadero_fk;
    }

    /**
     * @param parqueader_fk the parqueader_fk to set
     */
    public void setParqueadero_fk(Long parqueadero_fk) {
        this.parqueadero_fk = parqueadero_fk;
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
     * @return Timestamp return the hora_llegada
     */
    public Timestamp getHora_llegada() {
        return hora_llegada;
    }

    /**
     * @param hora_llegada the hora_llegada to set
     */
    public void setHora_llegada(Timestamp hora_llegada) {
        this.hora_llegada = hora_llegada;
    }

    /**
     * @return Timestamp return the hora_salida
     */
    public Timestamp getHora_salida() {
        return hora_salida;
    }

    /**
     * @param hora_salida the hora_salida to set
     */
    public void setHora_salida(Timestamp hora_salida) {
        this.hora_salida = hora_salida;
    }

    /**
     * @return Timestamp return the fecha_creacion
     */
    public Timestamp getFecha_creacion() {
        return fecha_creacion;
    }

    /**
     * @param fecha_creacion the fecha_creacion to set
     */
    public void setFecha_creacion(Timestamp fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getNombre_Cliente() {
        return nombre_cliente;
    }

    public void setNombre_Cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
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


    /**
     * @return String return the nombre_cliente
     */
    public String getNombre_cliente() {
        return nombre_cliente;
    }

    /**
     * @param nombre_cliente the nombre_cliente to set
     */
    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    /**
     * @return String return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
