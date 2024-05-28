package com.api.crud.dto.request;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservarCupoRequest {
    private Long tarjetaId;
    private Long usuarioId;
    private Long parqueaderoId;
    private Long vehiculoId;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date hora_llegada;

    private int horas;
    
    /**
     * @return Long return the tarjetaId
     */
    public Long getTarjetaId() {
        return tarjetaId;
    }

    /**
     * @param tarjetaId the tarjetaId to set
     */
    public void setTarjetaId(Long tarjetaId) {
        this.tarjetaId = tarjetaId;
    }

    /**
     * @return Long return the usuarioId
     */
    public Long getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * @return Long return the parqueaderoId
     */
    public Long getParqueaderoId() {
        return parqueaderoId;
    }

    /**
     * @param parqueaderoId the parqueaderoId to set
     */
    public void setParqueaderoId(Long parqueaderoId) {
        this.parqueaderoId = parqueaderoId;
    }

    /**
     * @return Long return the vehiculoId
     */
    public Long getVehiculoId() {
        return vehiculoId;
    }

    /**
     * @param vehiculoId the vehiculoId to set
     */
    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
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
     * @return int return the horas
     */
    public int getHoras() {
        return horas;
    }

    /**
     * @param horas the horas to set
     */
    public void setHoras(int horas) {
        this.horas = horas;
    }

}
