import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;

@Service
public class IpService {

    @Autowired
    private DataBase dataBase;

    public void saveIpAddress(String ipAddress, Integer userId) {
        Ip ip = new Ip();
        ip.setDireccionIp(ipAddress);
        ip.setUsuarioFk(userId);
        ip.setFechaCreacion(new Date());

        try {
            dataBase.saveIpAddress(ip);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

