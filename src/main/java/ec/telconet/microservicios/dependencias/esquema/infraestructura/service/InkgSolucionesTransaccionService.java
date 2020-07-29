package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.MaquinaVirtualReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.FactibPoolServidorReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.FactibilidadMaquinaVirtualReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.FactibilidadServidorReqDTO;

/**
 * Service del paquete INKG_SOLUCIONES_TRANSACTION, se deberá crear un DTO 
 * por procedimiento o función que no estén relacionado a una entidad en especifico
 * 
 * @author Karen Rodríguez Véliz <mailto:kyrdoriguez@telconet.ec>
 * @version 1.0
 * @since 19/05/2020
 */
@Service
public interface InkgSolucionesTransaccionService {
	public String crearMaquinaVirtual(MaquinaVirtualReqDTO request) throws GenericException;
	public String crearFactibilidadMV(FactibilidadMaquinaVirtualReqDTO request) throws GenericException;
	public String crearFactibilidadServidor(FactibilidadServidorReqDTO request) throws GenericException;
	public String crearFactibPoolServidor(FactibPoolServidorReqDTO request) throws GenericException;
}