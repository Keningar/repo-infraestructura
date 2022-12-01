package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.PreFactibilidadConnectivityReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.PreFactibilidadConnectivityResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.DatosFactibilidadConnectivityReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.DatosFactibilidadConnectivityResDTO;

/**
 * Service del paquete INKG_FACTIB_CONNECTIV_CONSULTA, se deberá crear un DTO 
 * por procedimiento o función que no estén relacionado a una entidad en específico
 * 
 * @author Lizbeth Cruz <mailto:mlcruz@telconet.ec>
 * @version 1.0
 * @since 06/07/2021
 * 
 * @author Antonio Ayala <mailto:afayala@telconet.ec>
 * @version 1.0
 * @since 21/07/2022 
 */
@Service
public interface InkgFactibConnectivConsultaService {
	public PreFactibilidadConnectivityResDTO obtieneInfoPreFactibilidad(PreFactibilidadConnectivityReqDTO request) throws GenericException;
	public DatosFactibilidadConnectivityResDTO obtenerDatosFactibilidad(DatosFactibilidadConnectivityReqDTO request) throws GenericException;
}