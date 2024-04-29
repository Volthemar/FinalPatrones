package com.api.crud.models;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String nombre;

    @Column
    private String identificacion;

    @Column
    private String correo;

    @Column
    private String usuario;

    @Column
    private String contrasena;

    @Column
    private boolean estado;

    @Column
    private Date fecha_creacion;

    @Column
    private Int num_intentos;

    @Column
    private String cod_verificacion;

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return String return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * @return String return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return String return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return String return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return boolean return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return Date return the fecha_creacion
     */
    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    /**
     * @param fecha_creacion the fecha_creacion to set
     */
    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    /**
    * @return Int return num_intentos
    */
    public Int getNum_intentos(){
        return num_intentos
    }

    /**
    * @param num_intentos the num_intentos to set
    */
    public void setNum_intentos(Int num_intentos){
        this.num_intentos = num_intentos
    }

    /**
    * @return String return cod_verificacion
    */
    public String getCod_verificacion(){
        return cod_verificacion
    }
    
    /**
    * @param cod_verificacion the cod_verificacion to set
    */
    public void setCod_verificacion(String cod_verificacion){
        this.cod_verificacion = cod_verificaicon
    }
}
