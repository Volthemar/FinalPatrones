package com.api.crud.DTO.Request;
import java.sql.Timestamp;

public class ReservarCupoRequest {
    private long usuario_fk;
    private long parqueadero_fk;
    private long vehiculo_fk;
    private Timestamp hora_llegada;
    private int horas_pedidas;
    

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
     * @return Date return the hora_llegada
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

}
