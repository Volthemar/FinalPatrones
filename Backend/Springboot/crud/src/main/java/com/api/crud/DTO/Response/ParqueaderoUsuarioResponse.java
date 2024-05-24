package com.api.crud.DTO.Response;

public class ParqueaderoUsuarioResponse {
    private Long usuario;
    private Long parqueadero;

    /**
     * @return Long return the usuario
     */
    public Long getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    /**
     * @return Long return the parqueadero
     */
    public Long getParqueadero() {
        return parqueadero;
    }

    /**
     * @param parqueadero the parqueadero to set
     */
    public void setParqueadero(Long parqueadero) {
        this.parqueadero = parqueadero;
    }

}
