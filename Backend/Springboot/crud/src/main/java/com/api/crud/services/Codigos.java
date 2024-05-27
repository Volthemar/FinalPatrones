package com.api.crud.services;

import java.security.SecureRandom;
import java.util.Random;

public class Codigos {
    public static String generarCodigoLogin() {
        Random random = new Random();
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            codigo.append(random.nextInt(10));
        }

        return codigo.toString();
    }

    public static String generarCodigoCupo() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder codigo = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            codigo.append(characters.charAt(index));
        }

        return codigo.toString();
    }

}
