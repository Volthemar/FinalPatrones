

package main;

import java.sql.ResultSet;

import modelo.DataBase;
import java.sql.SQLException;

public class main {

    public static void main(String[] args) {
        
        DataBase db = new DataBase();
        //db.crearUsuario("David","1000469816","1234","davariasc@udistrital.edu.co","Da04vid");
        System.out.println("inicio");
        ResultSet respuesta = db.buscarUsuario("1234", "Da04vid");
        // EJEMPLO DE MUESTRA DE DATOS
        try{
            while(respuesta.next()){
                String estado = respuesta.getString("ESTADO");
                String correo = respuesta.getString("CORREO");            
                System.out.println("Estado: " + estado + ", Correo: " + correo);
            }
        }catch (SQLException e) {
            System.out.println("Error al mostrar resultados: " + e);
        }
        ResultSet idUsuario = db.buscarIdUsuario("Da04vid");
        int id=0;
        try{
            while(idUsuario.next()){
                id = idUsuario.getInt("ID");
                System.out.println("id: " + id );
            }
        }catch (SQLException e) {
            System.out.println("Error al mostrar resultados: " + e);
        }
        // db.crearIpConUsuario("168.168.168.168", id);
        // db.crearIpSinUsuario("168.168.168.168")
        //db.crearTipoUsuario("administrador",id);
        ResultSet tipoUsuario = db.buscarTipoUsuario(id);
        try{
            while(tipoUsuario.next()){
                String tipo = tipoUsuario.getString("Tipo");
                System.out.println("tipo: " + tipo);
            }
        }catch (SQLException e) {
            System.out.println("Error al mostrar resultados: " + e);
        }
    }   
}
