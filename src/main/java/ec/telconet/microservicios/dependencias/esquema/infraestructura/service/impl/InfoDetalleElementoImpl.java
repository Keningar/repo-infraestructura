package ec.telconet.microservicios.dependencias.esquema.infraestructura.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;

import ec.telconet.microservicio.dependencia.util.dto.PageDTO;
import ec.telconet.microservicio.dependencia.util.enumerado.StatusHandler;
import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicio.dependencia.util.general.DateDeserializer;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.DetalleElementoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoDetalleElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoHistorialElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.InfoDetalleElementoRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InfoDetalleElementoService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InfoHistorialElementoService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraConstants;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraProperties;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraValidators;

/**
 * Impl Service INFO_DETALLE_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public class InfoDetalleElementoImpl implements InfoDetalleElementoService {
	@Autowired
	private InfraestructuraValidators infraestructuraValidators;
	
	@Autowired
	private InfoDetalleElementoRepository infoDetalleElementoRepo;
	
	@Autowired
	private InfoHistorialElementoService infoHistorialElementoService;
	
	@Autowired
	@Qualifier("jdbcInfraestructura")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private InfraestructuraProperties infraestructuraProperties;
	
	/**
	 * Método que guarda una característica del elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public InfoDetalleElemento guardar(InfoDetalleElemento request) throws GenericException {
		InfoDetalleElemento response = new InfoDetalleElemento();
		try {
			infraestructuraValidators.validarGuardarDetalleElemento(request);
			request.setEstado(StatusHandler.Activo.toString());
			request.setFeCreacion(new Date());
			response = infoDetalleElementoRepo.saveAndFlush(request);
			// Guardo el historial del elemento
			InfoHistorialElemento historial = new InfoHistorialElemento();
			historial.setElementoId(request.getElementoId());
			historial.setEstadoElemento(StatusHandler.Activo.toString());
			historial.setObservacion("Se creó la característica " + request.getDetalleNombre());
			historial.setUsrCreacion(response.getUsrCreacion());
			historial.setIpCreacion(response.getIpCreacion());
			infoHistorialElementoService.guardar(historial);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que actualiza una característica del elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public InfoDetalleElemento actualizar(InfoDetalleElemento request) throws GenericException {
		InfoDetalleElemento response = new InfoDetalleElemento();
		try {
			request = infraestructuraValidators.validarActualizarDetalleElemento(request);
			response = infoDetalleElementoRepo.saveAndFlush(request);
			// Guardo el historial del elemento
			InfoHistorialElemento historial = new InfoHistorialElemento();
			historial.setElementoId(response.getElementoId());
			historial.setEstadoElemento(StatusHandler.Modificado.toString());
			historial.setObservacion("Se modificó la característica " + response.getDetalleNombre());
			historial.setUsrCreacion(response.getUsrCreacion());
			historial.setIpCreacion(response.getIpCreacion());
			infoHistorialElementoService.guardar(historial);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que elimina una característica del elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public Boolean eliminar(InfoDetalleElemento request) throws GenericException {
		Boolean response = false;
		try {
			request.setEstado(StatusHandler.Eliminado.toString());
			request = infraestructuraValidators.validarActualizarDetalleElemento(request);
			infoDetalleElementoRepo.saveAndFlush(request);
			// Guardo el historial del elemento
			InfoHistorialElemento historial = new InfoHistorialElemento();
			historial.setElementoId(request.getElementoId());
			historial.setEstadoElemento(StatusHandler.Eliminado.toString());
			historial.setObservacion("Se eliminó la característica " + request.getDetalleNombre());
			historial.setUsrCreacion(request.getUsrCreacion());
			historial.setIpCreacion(request.getIpCreacion());
			infoHistorialElementoService.guardar(historial);
			response = true;
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de característica del elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public List<InfoDetalleElemento> lista() throws GenericException {
		List<InfoDetalleElemento> response = new ArrayList<InfoDetalleElemento>();
		try {
			infraestructuraValidators.validarMaximoDataList(infoDetalleElementoRepo.count());
			response = infoDetalleElementoRepo.findAll();
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de característica del elemento con filtros
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public List<InfoDetalleElemento> listaPor(InfoDetalleElemento request) throws GenericException {
		List<InfoDetalleElemento> response = new ArrayList<InfoDetalleElemento>();
		try {
			infraestructuraValidators.validarDetalleElementoVacio(request);
			Example<InfoDetalleElemento> listFiltros = Example.of(request);
			response = infoDetalleElementoRepo.findAll(listFiltros, Sort.by(Direction.DESC, InfoDetalleElemento.idDetalleElementoValue));
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la paginación de una lista de característica del elemento
	 * con filtros
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public Page<InfoDetalleElemento> paginaListaPor(PageDTO<InfoDetalleElemento> request) throws GenericException {
		Page<InfoDetalleElemento> response = null;
		Pageable pageable;
		try {
			infraestructuraValidators.validarPagina(request);
			Example<InfoDetalleElemento> listFiltros = Example.of(request.getTabla());
			if (request.getOrder() != null && request.getOrderValue() != null) {
				pageable = PageRequest.of(request.getPage(), request.getSize(),
						Sort.by(Direction.valueOf(request.getOrder()), request.getOrderValue()));
			} else {
				pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.Direction.DESC, InfoDetalleElemento.idDetalleElementoValue);
			}
			response = infoDetalleElementoRepo.findAll(listFiltros, pageable);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de característica de un elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	@SuppressWarnings("unchecked")
	public List<InfoDetalleElemento> detalleElementoPorElemento(DetalleElementoReqDTO request) throws GenericException {
		List<InfoDetalleElemento> response = new ArrayList<InfoDetalleElemento>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			infraestructuraValidators.validarDetalleElementoPorElemento(request);
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceDetalleElementoPorElemento())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(InfoDetalleElemento.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<InfoDetalleElemento>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
}
