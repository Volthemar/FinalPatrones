package com.api.crud.services.models;

import java.util.Date;

public class EmailCupo {
    private String destinatario;
    private String asunto;
    private String codigo;
    private Date horaLlegada;
    private int horasSolicitadas;
    

    /**
     * @return String return the destinatario
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * @param destinatario the destinatario to set
     */
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * @return String return the asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * @param asunto the asunto to set
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
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
     * @return Date return the horaLlegada
     */
    public Date getHoraLlegada() {
        return horaLlegada;
    }

    /**
     * @param horaLlegada the horaLlegada to set
     */
    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    /**
     * @return int return the horasSolicitadas
     */
    public int getHorasSolicitadas() {
        return horasSolicitadas;
    }

    /**
     * @param horasSolicitadas the horasSolicitadas to set
     */
    public void setHorasSolicitadas(int horasSolicitadas) {
        this.horasSolicitadas = horasSolicitadas;
    }

}
