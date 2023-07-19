package ec.telconet.microservicios.dependencias.esquema.infraestructura.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoKardexTarea;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.InfoKardexTareaRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InfoKardexTareaService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraValidators;
/**
 * Impl Service INFO_KARDEX_TAREA
 * 
 * @author José Castillo <mailto:jmcastillo@telconet.ec>
 * @version 1.0
 * @since 08/05/2023
 */
@Service
public class InfoKardexTareaImpl implements InfoKardexTareaService{

	@Autowired
	private InfraestructuraValidators infraestructuraValidators;
	
	@Autowired
	private InfoKardexTareaRepository infoKardexTareaRepo;
	
	@Override
	public InfoKardexTarea guardarTarea(InfoKardexTarea request) throws GenericException {
		InfoKardexTarea response = new InfoKardexTarea();
		try {
			infraestructuraValidators.validarGuardarKardexTarea(request);
			request.setFeCreacion(new Date());
			response = infoKardexTareaRepo.save(request);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}

}
