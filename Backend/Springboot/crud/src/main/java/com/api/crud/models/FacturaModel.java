package com.api.crud.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Factura")
public class FacturaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_pagado", nullable = false)
    private BigDecimal valorPagado;

    @Column(name = "cupo_fk", nullable = false)
    private long cupoId;

    @Column(name = "vehiculo_fk", nullable = false)
    private long vehiculoId;

    @Column(name = "parqueadero_fk", nullable = false)
    private long parqueaderoId;

    @Column(name = "usuario_fk", nullable = false)
    private long usuarioId;

    @Column(name = "tarjeta_credito_fk", nullable = false)
    private long tarjetaCreditoId;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false, insertable = false)
    private Timestamp fechaCreacion;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public long getCupoId() {
        return cupoId;
    }

    public void setCupoId(long cupoId) {
        this.cupoId = cupoId;
    }

    public long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public long getParqueaderoId() {
        return parqueaderoId;
    }

    public void setParqueaderoId(long parqueaderoId) {
        this.parqueaderoId = parqueaderoId;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public long getTarjetaCreditoId() {
        return tarjetaCreditoId;
    }

    public void setTarjetaCreditoId(long tarjetaCreditoId) {
        this.tarjetaCreditoId = tarjetaCreditoId;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}