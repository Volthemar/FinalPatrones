
package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class DataBase {
    Connection conexion;
    String url = "jdbc:mysql://localhost:3306/parqueadero";
    String user = "root";
    String pass = "1234";
    PreparedStatement ps;
    ResultSet rs;
    
    
    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Problemas");
            System.out.println(e);
        }
    }    

    public void crearCliente(String nombre,String identificacion,String contrasena,String correo,String usuario){
        conectar();
        try{
            String sqlInsercion = "INSERT INTO usuario (Nombre, Identificacion, Contrasena, Correo, Usuario) VALUES (?, ?, ?, ?, ?)";
            try (
                PreparedStatement ps = conexion.prepareStatement(sqlInsercion)) {
                ps.setString(1, nombre);
                ps.setString(2, identificacion);
                ps.setString(3, contrasena);
                ps.setString(4, correo);
                ps.setString(5, usuario);
                ps.executeUpdate();
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
 
}

    

    

