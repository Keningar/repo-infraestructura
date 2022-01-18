package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.DatosVehiculoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.DatosVehiculoResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorGrupoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorGrupoResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ModelosElemMonitorizadosReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ModelosElemMonitorizadosResDTO;

/**
 * Service del paquete INKG_ELEMENTO_CONSULTA, se deberá crear un DTO por procedimiento o función que no estén
 * relacionado a una entidad en especifico
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public interface InkgElementoConsultaService {
	public List<DatosVehiculoResDTO> datosVehiculo(DatosVehiculoReqDTO request) throws GenericException;
	public List<ElementoPorGrupoResDTO> elementoPorGrupo(ElementoPorGrupoReqDTO request) throws GenericException;
	public List<ModelosElemMonitorizadosResDTO> modelosElementoMonitorizado(ModelosElemMonitorizadosReqDTO request) throws GenericException;
}
