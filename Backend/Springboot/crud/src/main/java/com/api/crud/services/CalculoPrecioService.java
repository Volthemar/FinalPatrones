package com.api.crud.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.api.crud.models.TarifaModel;

public class CalculoPrecioService {
    public static int CalcularPrecio(TarifaModel tarifaParqueadero, int horas){
        int precioMinuto = tarifaParqueadero.getValor_ordinario();
        int totalMinutos = horas * 60;
        int precioFinal = totalMinutos * precioMinuto;
        return precioFinal;
    }

    public static BigDecimal  CalcularPrecioExtraOrdinario(TarifaModel tarifaParqueadero, Date horaSalidaSolicitada, Date horaSalidaReal){
        long diferenciaEnMilisegundos = horaSalidaReal.getTime() - horaSalidaSolicitada.getTime();
        BigDecimal diferenciaEnMinutos = BigDecimal.valueOf(TimeUnit.MILLISECONDS.toMinutes(diferenciaEnMilisegundos));
        BigDecimal precioMinuto = BigDecimal.valueOf(tarifaParqueadero.getValor_mora());
        BigDecimal precioFinal = diferenciaEnMinutos.multiply(precioMinuto);
        return precioFinal;
    }

    public static BigDecimal  CalcularPrecioOffline(TarifaModel tarifaParqueadero, Date horaLlegada, Date horaSalida){
        long diferenciaEnMilisegundos = horaSalida.getTime() - horaLlegada.getTime();
        BigDecimal diferenciaEnMinutos = BigDecimal.valueOf(TimeUnit.MILLISECONDS.toMinutes(diferenciaEnMilisegundos));
        BigDecimal precioMinuto = BigDecimal.valueOf(tarifaParqueadero.getValor_ordinario());
        BigDecimal precioFinal = diferenciaEnMinutos.multiply(precioMinuto);
        return precioFinal;
    }

}
