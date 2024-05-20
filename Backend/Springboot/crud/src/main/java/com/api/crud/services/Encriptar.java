package com.api.crud.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encriptar {
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String ALL_CHARS = LOWERCASE_CHARS + UPPERCASE_CHARS + DIGITS;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generarContrasena(){
        StringBuilder password = new StringBuilder();

        password.append(LOWERCASE_CHARS.charAt(RANDOM.nextInt(LOWERCASE_CHARS.length())));
        password.append(UPPERCASE_CHARS.charAt(RANDOM.nextInt(UPPERCASE_CHARS.length())));
        password.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));

        for (int i = 0; i < 2; i++) {
            password.append(ALL_CHARS.charAt(RANDOM.nextInt(ALL_CHARS.length())));
        }

        int remainingLength = 8 - password.length();
        for (int i = 0; i < remainingLength; i++) {
            char randomChar = ALL_CHARS.charAt(RANDOM.nextInt(ALL_CHARS.length()));
            password.insert(RANDOM.nextInt(password.length() + 1), randomChar);
        }

        return password.toString();
    }

    public static String encriptarContrasena(String contrasena){
        String contrasenaEncriptada = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(contrasena.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            contrasenaEncriptada = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return contrasenaEncriptada;
    }

}
