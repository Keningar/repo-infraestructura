package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoDetalleKardex;
/**
 * Service INFO_DETALLE_KARDEX
 * 
 * @author José Castillo <mailto:jmcastillo@telconet.ec>
 * @version 1.0
 * @since 23/05/2023
 */
@Service
public interface InfoDetalleKardexService {
	public InfoDetalleKardex guardarDetalleKardex(InfoDetalleKardex request) throws GenericException;
}
