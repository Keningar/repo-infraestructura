package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoKardex;

/**
 * Service INFO_KARDEX
 * 
 * @author José Castillo <mailto:jmcastillo@telconet.ec>
 * @version 1.0
 * @since 03/05/2023
 */
@Service
public interface InfoKardexService {
	public InfoKardex guardar(InfoKardex request) throws GenericException;
}
