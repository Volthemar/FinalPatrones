public class Ip {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "direccion_ip", nullable = false)
    private String direccionIp;

    @Column(name = "usuario_fk")
    private Integer usuarioFk;

    @Column
    private Date fechaCreacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public Integer getUsuarioFk() {
        return usuarioFk;
    }

    public void setUsuarioFk(Integer usuarioFk) {
        this.usuarioFk = usuarioFk;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
