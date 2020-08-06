package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.UbicacionElementoReqDTO;

/**
 * Service del paquete INKG_ELEMENTO_TRANSACCION, se deberá crear un DTO por procedimiento o función que no estén
 * relacionado a una entidad en especifico
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public interface InkgElementoTransaccionService {
	public String asignarUbicacionElemento(UbicacionElementoReqDTO request) throws GenericException;
	public String modificarUbicacionElemento(UbicacionElementoReqDTO request) throws GenericException;
}
