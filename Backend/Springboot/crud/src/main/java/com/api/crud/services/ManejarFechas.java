package com.api.crud.services;

import java.util.Calendar;
import java.util.Date;

public class ManejarFechas {
    public static Date obtenerFechaActual(){
        Date fecha = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.HOUR_OF_DAY,-5);
        Date fechaColombia = calendar.getTime();
        return fechaColombia;
    }
}
