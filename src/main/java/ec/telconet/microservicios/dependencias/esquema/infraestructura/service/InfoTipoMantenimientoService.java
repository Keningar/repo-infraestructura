package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoTipoMantenimiento;

@Service
public interface InfoTipoMantenimientoService {
	public List<InfoTipoMantenimiento> listaTipoMantenimientos(InfoTipoMantenimiento request) throws GenericException;

}
