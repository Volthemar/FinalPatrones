package com.api.crud.services;

import com.api.crud.models.TarifaModel;

public class CalculoPrecioService {
    public static int CalcularPrecio(TarifaModel tarifaParqueadero, int horas){
        int precioMinuto = tarifaParqueadero.getValor_ordinario();
        int totalMinutos = horas * 60;
        int precioFinal = totalMinutos * precioMinuto;
        return precioFinal;
    }
}
