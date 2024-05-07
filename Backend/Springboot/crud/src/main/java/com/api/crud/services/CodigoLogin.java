package com.api.crud.services;

import java.util.Random;

public class CodigoLogin {
    public String generarCodigo() {
        Random random = new Random();
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            codigo.append(random.nextInt(10));
        }

        return codigo.toString();
    }
}
