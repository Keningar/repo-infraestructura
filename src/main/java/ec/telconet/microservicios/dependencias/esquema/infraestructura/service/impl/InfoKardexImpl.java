package ec.telconet.microservicios.dependencias.esquema.infraestructura.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.enumerado.StatusHandler;
import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoKardex;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.InfoKardexRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InfoKardexService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraValidators;

/**
 * Impl Service INFO_KARDEX
 * 
 * @author José Castillo <mailto:jmcastillo@telconet.ec>
 * @version 1.0
 * @since 03/05/2023
 */
@Service
public class InfoKardexImpl implements InfoKardexService{
	@Autowired
	private InfraestructuraValidators infraestructuraValidators;
	
	@Autowired
	private InfoKardexRepository infoKardexRepo;


	public InfoKardex guardar(InfoKardex request) throws GenericException {
		InfoKardex response = new InfoKardex();
		try {
			infraestructuraValidators.validarGuardarKardex(request);
			response = infoKardexRepo.save(request);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}

}
