package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import org.springframework.stereotype.Service;
import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementosOltDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ObtenerPmReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.OltDTO;

/**
 * Service del paquete INKG_PROCESOS_MASIVOS, se deberá crear un DTO por procedimiento o función que no estén
 * relacionado a una entidad en especifico
 * 
 * @author Jesús Bozada <mailto:jbozada@telconet.ec>
 * @version 1.0
 * @since 30/08/2022
 */
@Service
public interface InkgProcesosMasivosService {
	public ElementosOltDTO obtenerPmCorteReactivacion(ObtenerPmReqDTO request) throws GenericException;
	
	public OltDTO procesarPeticionMw(OltDTO request) throws GenericException;
	
	public OltDTO procesarTelcos(OltDTO request) throws GenericException;
	
	public void procesarCabecerasPm(OltDTO request) throws GenericException;
}
