package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoIp;

/**
 * Service INFO_IP
 * 
 * @author David De La Cruz <mailto:ddelacruz@telconet.ec>
 * @version 1.0
 * @since 11/01/2022
 */
@Service
public interface InfoIpService {
    public List<InfoIp> listaPor(InfoIp request) throws GenericException;
}
