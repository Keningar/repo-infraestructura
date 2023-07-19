package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoKardexTarea;

/**
 * Service INFO_KARDEX_TAREA
 * 
 * @author José Castillo <mailto:jmcastillo@telconet.ec>
 * @version 1.0
 * @since 08/05/2023
 */
@Service
public interface InfoKardexTareaService {
	public InfoKardexTarea guardarTarea(InfoKardexTarea request) throws GenericException;
}
