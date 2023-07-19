package ec.telconet.microservicios.dependencias.esquema.infraestructura.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoTipoMantenimiento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.InfoTipoMantenimientoRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InfoTipoMantenimientoService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraValidators;
@Service
public class InfoTipoMantenimientoImpl implements InfoTipoMantenimientoService {
	@Autowired
	private InfraestructuraValidators infraestructuraValidators;
	
	@Autowired
	private InfoTipoMantenimientoRepository infoTipoMantenimientoRepo;

	
	public List<InfoTipoMantenimiento> listaTipoMantenimientos(InfoTipoMantenimiento request) throws GenericException {
		List<InfoTipoMantenimiento> response = new ArrayList<InfoTipoMantenimiento>();
		try {
			infraestructuraValidators.validarTipoMantenimiento(request);
			Example<InfoTipoMantenimiento> listFiltros = Example.of(request);
			response = infoTipoMantenimientoRepo.findAll(listFiltros, Sort.by(Direction.ASC, InfoTipoMantenimiento.idTipoMantenimientoValue));
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
}
