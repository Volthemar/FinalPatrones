package com.api.crud.DTO.Request;
import java.util.Date;

public class ReservarCupoRequest {
    private Long tarjeta_fk;
    private Long parqueadero_fk;
    private Long vehiculo_fk;
    private Date hora_llegada;
    private int horas;


    /**
     * @return Long return the tarjeta_fk
     */
    public Long getTarjeta_fk() {
        return tarjeta_fk;
    }

    /**
     * @param tarjeta_fk the tarjeta_fk to set
     */
    public void setTarjeta_fk(Long tarjeta_fk) {
        this.tarjeta_fk = tarjeta_fk;
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
