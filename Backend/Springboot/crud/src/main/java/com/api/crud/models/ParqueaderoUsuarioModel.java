package com.api.crud.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parqueadero_usuario")
public class ParqueaderoUsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long parqueadero_fk;

    @Column
    private Long usuario_fk;

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
     * @return Long return the parqueadero_fk
     */
    public Long getParqueadero_fk() {
        return parqueadero_fk;
    }

    /**
     * @param parqueadero_fk the parqueadero_fk to set
     */
    public void setParqueadero_fk(Long parqueadero_fk) {
        this.parqueadero_fk = parqueadero_fk;
    }

    /**
     * @return Long return the usuario_fk
     */
    public Long getUsuario_fk() {
        return usuario_fk;
    }

    /**
     * @param usuario_fk the usuario_fk to set
     */
    public void setUsuario_fk(Long usuario_fk) {
        this.usuario_fk = usuario_fk;
    }

}
