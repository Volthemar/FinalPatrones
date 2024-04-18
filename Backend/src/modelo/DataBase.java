
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

    public boolean crearUsuario(String nombre,String identificacion,String contrasena,String correo,String usuario){
        conectar();
        try{
            String sqlInsercion = "INSERT INTO usuario (Nombre, Identificacion, Contrasena, Correo, Usuario) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(sqlInsercion);
            ps.setString(1, nombre);
            ps.setString(2, identificacion);
            ps.setString(3, contrasena);
            ps.setString(4, correo);
            ps.setString(5, usuario);
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public ResultSet buscarIdUsuario(String usuario){
        conectar();
        try{
            String sqlQuery = "SELECT Id FROM USUARIO WHERE usuario = ?";
            PreparedStatement ps = conexion.prepareStatement(sqlQuery);
            ps.setString(1, usuario);
            System.out.println(ps.toString());
            rs = ps.executeQuery();
            if(tieneResultados(rs)){
                return rs;
            }else{
                return null;
            }
        }catch(SQLException ex){
            return null;
        }
    }

    public ResultSet buscarUsuario(String contrasena, String usuario){
        conectar();
        try{
            String sqlQuery = "SELECT ESTADO, CORREO FROM USUARIO WHERE contrasena = ? and usuario = ?";
            PreparedStatement ps = conexion.prepareStatement(sqlQuery);
            ps.setString(1, contrasena);
            ps.setString(2, usuario);
            rs = ps.executeQuery();
            if(tieneResultados(rs)){
                return rs;
            }else{
                return null;
            }
        }catch(SQLException ex){
            return null;
        }
    }
    
    public boolean crearIpConUsuario(String direccionIp,int usuarioFk){
        conectar();
        try{
            String sqlInsercion = "INSERT INTO ip (Direccion_ip, Usuario_fk) VALUES (?, ?)";
            PreparedStatement ps = conexion.prepareStatement(sqlInsercion);
            ps.setString(1, direccionIp);
            ps.setInt(2, usuarioFk);
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("Error: "+e);
            return false;
        }
    }

    public boolean crearIpSinUsuario(String direccionIp){
        conectar();
        try{
            String sqlInsercion = "INSERT INTO ip (Direccion_ip) VALUES (?)";
            PreparedStatement ps = conexion.prepareStatement(sqlInsercion);
            ps.setString(1, direccionIp);
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean tieneResultados(ResultSet rs) {
        try {
            return rs.isBeforeFirst();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean crearTipoUsuario(String tipo,int usuarioFk){
        conectar();
        try{
            String sqlInsercion = "INSERT INTO tipo_usuario (Tipo, Usuario_fk) VALUES (?, ?)";
            PreparedStatement ps = conexion.prepareStatement(sqlInsercion);
            ps.setString(1, tipo);
            ps.setInt(2, usuarioFk);
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("Error: "+e);
            return false;
        }
    }

    public ResultSet buscarTipoUsuario(int usuarioFk){
        conectar();
        try{
            String sqlInsercion = "SELECT * FROM tipo_usuario WHERE Usuario_fk = ?";
            PreparedStatement ps = conexion.prepareStatement(sqlInsercion);
            ps.setInt(1, usuarioFk);
            rs = ps.executeQuery();
            if(tieneResultados(rs)){
                return rs;
            }else{
                return null;
            }
        }catch(SQLException ex){
            return null;
        }
    }
}

    

    

