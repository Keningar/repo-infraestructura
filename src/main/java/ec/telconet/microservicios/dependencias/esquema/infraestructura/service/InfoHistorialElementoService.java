package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.HistorialElementoPorFechaReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.HistorialElementoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoHistorialElemento;

/**
 * Service INFO_HISTORIAL_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public interface InfoHistorialElementoService {
	public InfoHistorialElemento guardar(InfoHistorialElemento request) throws GenericException;
	public List<InfoHistorialElemento> historialElementoPorElemento(HistorialElementoReqDTO request) throws GenericException;
	public List<InfoHistorialElemento> historialElementoPorFecha(HistorialElementoPorFechaReqDTO request) throws GenericException;
}
