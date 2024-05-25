package com.api.crud.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Factura_offline")
public class FacturaOfflineModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_pagado", nullable = false)
    private BigDecimal valorPagado;

    @Column(name = "cupo_offline_fk", nullable = false)
    private long cupoOfflineId;

    @Column(name = "vehiculo_fk", nullable = false)
    private long vehiculoId;

    @Column(name = "parqueadero_fk", nullable = false)
    private long parqueaderoId;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false, insertable = false)
    private Timestamp fechaCreacion;

    // Getters and Setters

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

    public long getCupoOfflineId() {
        return cupoOfflineId;
    }

    public void setCupoOfflineId(long cupoOfflineId) {
        this.cupoOfflineId = cupoOfflineId;
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

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}