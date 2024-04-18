

package main;

import modelo.DataBase;

public class main {

    public static void main(String[] args) {
        
        DataBase db = new DataBase();
        db.crearCliente("David","1000469816","1234","davariasc@udistrital.edu.co","Da04vid");
        System.out.println("agregado");
    }   
}
