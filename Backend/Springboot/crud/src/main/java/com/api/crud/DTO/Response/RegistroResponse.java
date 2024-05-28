package com.api.crud.dto.response;
import com.api.crud.models.TipoUsuarioModel;

public class RegistroResponse {
    private Long id;
    private String nombre;
    private String correo;
    private boolean estado;
    private boolean activo;
    private TipoUsuarioModel tipo;

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
     * @return String return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return boolean return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return TipoUsuarioModel return the tipo
     */
    public TipoUsuarioModel getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoUsuarioModel tipo) {
        this.tipo = tipo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    /**
     * @return boolean return the activo
     */
    public boolean isActivo() {
        return activo;
    }

}
