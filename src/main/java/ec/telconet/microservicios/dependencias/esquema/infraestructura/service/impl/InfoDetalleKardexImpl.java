package ec.telconet.microservicios.dependencias.esquema.infraestructura.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoDetalleKardex;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoKardex;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.InfoDetalleKardexRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InfoDetalleKardexService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraValidators;
@Service
public class InfoDetalleKardexImpl implements InfoDetalleKardexService{
	@Autowired
	private InfraestructuraValidators infraestructuraValidators;

	@Autowired
	private InfoDetalleKardexRepository infoDetalleKardexRepo;


	public InfoDetalleKardex guardarDetalleKardex(InfoDetalleKardex request) throws GenericException {
		InfoDetalleKardex response = new InfoDetalleKardex();
		try {
			infraestructuraValidators.validarGuardarDetalleKardex(request);
			response = infoDetalleKardexRepo.save(request);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
}
