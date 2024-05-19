import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;

import com.api.crud.dto.IpCaptureRequest;
import com.api.crud.models.Ip;
import com.api.crud.repositories.IpRepository;

@Service
public class IpService {

    @Autowired
    private IpRepository ipRepository;

    public void captureIp(IpCaptureRequest request) {
        Ip ip = new Ip();
        ip.setIpAddress(request.getIpAddress());
        ip.setUserId(request.getUserId());
        ip.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        ipRepository.save(ip);
    }
}

