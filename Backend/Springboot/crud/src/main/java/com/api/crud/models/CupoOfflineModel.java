package com.api.crud.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Column (name = "Parqueadero_fk")
    private long parqueaderoFk;

    @Column (name = "Vehiculo_fk")
    private Long vehiculoFk;

    @Column 
    private boolean pagado;

    @Column (name = "Hora_llegada")
    private Date horaLlegada;

    @Column (name = "Hora_salida")
    private Date horaSalida;

    @Column (name = "Fecha_creacion")
    private Date fechaCreacion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column 
    private Boolean activo;

    @Column
    private String codigo;

    public Estado getEstado() {
        return estado;
    }

    public enum Estado {
        OCUPADO,
        FINALIZADO
    }

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
    public Long getParqueaderoFk() {
        return parqueaderoFk;
    }

    /**
     * @param parqueader_fk the parqueader_fk to set
     */
    public void setParqueaderoFk(Long parqueaderoFk) {
        this.parqueaderoFk = parqueaderoFk;
    }

    /**
     * @return Long return the vehiculo_fk
     */
    public Long getVehiculoFk() {
        return vehiculoFk;
    }

    /**
     * @param vehiculo_fk the vehiculo_fk to set
     */
    public void setVehiculoFk(Long vehiculo_fk) {
        this.vehiculoFk = vehiculo_fk;
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
     * @return boolean return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @return Boolean return the activo
     */
    public Boolean getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
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


    /**
     * @param estado the estado to set
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }


    /**
     * @return Date return the hora_llegada
     */
    public Date getHoraLlegada() {
        return horaLlegada;
    }

    /**
     * @param hora_llegada the hora_llegada to set
     */
    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    /**
     * @return Date return the hora_salida
     */
    public Date getHoraSalida() {
        return horaSalida;
    }

    /**
     * @param hora_salida the hora_salida to set
     */
    public void setHora_salida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * @return Date return the fecha_creacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fecha_creacion the fecha_creacion to set
     */
    public void setFecha_creacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
