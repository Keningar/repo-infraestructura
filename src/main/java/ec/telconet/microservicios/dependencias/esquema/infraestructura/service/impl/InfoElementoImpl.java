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
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorCantonParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorFilialParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorMonitorizadoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorParroquiaParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorProvinciaParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorRegionParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorTipoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoHistorialElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.InfoElementoRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InfoElementoService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InfoHistorialElementoService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraConstants;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraProperties;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraValidators;

/**
 * Impl Service INFO_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public class InfoElementoImpl implements InfoElementoService {
	@Autowired
	private InfraestructuraValidators infraestructuraValidators;
	
	@Autowired
	@Qualifier("jdbcInfraestructura")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private InfraestructuraProperties infraestructuraProperties;
	
	@Autowired
	private InfoElementoRepository infoElementoRepo;
	
	@Autowired
	private InfoHistorialElementoService infoHistorialElementoService;
	
	/**
	 * Método que guarda un elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public InfoElemento guardar(InfoElemento request) throws GenericException {
		InfoElemento response = new InfoElemento();
		try {
			infraestructuraValidators.validarGuardarElemento(request);
			request.setEstado(StatusHandler.Activo.toString());
			request.setFeCreacion(new Date());
			response = infoElementoRepo.saveAndFlush(request);
			// Guardo el historial del elemento
			InfoHistorialElemento historial = new InfoHistorialElemento();
			historial.setElementoId(response.getIdElemento());
			historial.setEstadoElemento(StatusHandler.Activo.toString());
			historial.setObservacion("Se creó el elemento");
			historial.setUsrCreacion(response.getUsrCreacion());
			historial.setIpCreacion(response.getIpCreacion());
			infoHistorialElementoService.guardar(historial);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que actualiza un elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public InfoElemento actualizar(InfoElemento request) throws GenericException {
		InfoElemento response = new InfoElemento();
		try {
			request = infraestructuraValidators.validarActualizarElemento(request);
			response = infoElementoRepo.saveAndFlush(request);
			// Guardo el historial del elemento
			InfoHistorialElemento historial = new InfoHistorialElemento();
			historial.setElementoId(response.getIdElemento());
			historial.setEstadoElemento(StatusHandler.Modificado.toString());
			historial.setObservacion("Se modificó el elemento");
			historial.setUsrCreacion(response.getUsrCreacion());
			historial.setIpCreacion(response.getIpCreacion());
			infoHistorialElementoService.guardar(historial);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que elimina un elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public Boolean eliminar(InfoElemento request) throws GenericException {
		Boolean response = false;
		try {
			request.setEstado(StatusHandler.Eliminado.toString());
			request = infraestructuraValidators.validarActualizarElemento(request);
			infoElementoRepo.saveAndFlush(request);
			// Guardo el historial del elemento
			InfoHistorialElemento historial = new InfoHistorialElemento();
			historial.setElementoId(request.getIdElemento());
			historial.setEstadoElemento(StatusHandler.Eliminado.toString());
			historial.setObservacion("Se eliminó el elemento");
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
	 * Método que retorna la lista de elementos
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public List<InfoElemento> lista() throws GenericException {
		List<InfoElemento> response = new ArrayList<InfoElemento>();
		try {
			infraestructuraValidators.validarMaximoDataList(infoElementoRepo.count());
			response = infoElementoRepo.findAll();
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de elementos con filtros
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public List<InfoElemento> listaPor(InfoElemento request) throws GenericException {
		List<InfoElemento> response = new ArrayList<InfoElemento>();
		try {
			infraestructuraValidators.validarElementoVacio(request);
			Example<InfoElemento> listFiltros = Example.of(request);
			response = infoElementoRepo.findAll(listFiltros, Sort.by(Direction.DESC, InfoElemento.idElementoValue));
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la paginación de una lista de elementos con filtros
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public Page<InfoElemento> paginaListaPor(PageDTO<InfoElemento> request) throws GenericException {
		Page<InfoElemento> response = null;
		Pageable pageable;
		try {
			infraestructuraValidators.validarPagina(request);
			Example<InfoElemento> listFiltros = Example.of(request.getTabla());
			if (request.getOrder() != null && request.getOrderValue() != null) {
				pageable = PageRequest.of(request.getPage(), request.getSize(),
						Sort.by(Direction.valueOf(request.getOrder()), request.getOrderValue()));
			} else {
				pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.Direction.DESC, InfoElemento.idElementoValue);
			}
			response = infoElementoRepo.findAll(listFiltros, pageable);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de elementos de un tipo de elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	@SuppressWarnings("unchecked")
	public List<InfoElemento> elementoPorTipo(ElementoPorTipoReqDTO request) throws GenericException {
		List<InfoElemento> response = new ArrayList<InfoElemento>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			infraestructuraValidators.validarElementoPorTipo(request);
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceElementoPorTipo())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(InfoElemento.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<InfoElemento>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de elementos que tiene como característica
	 * ES_MONITORIZADO
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	@SuppressWarnings("unchecked")
	public List<InfoElemento> elementoPorEsMonitorizado(ElementoPorMonitorizadoReqDTO request) throws GenericException {
		List<InfoElemento> response = new ArrayList<InfoElemento>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceElementoPorEsMonitorizado())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(InfoElemento.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<InfoElemento>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de elementos por region y params
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	@SuppressWarnings("unchecked")
	public List<InfoElemento> elementoPorRegionParams(ElementoPorRegionParamsReqDTO request) throws GenericException {
		List<InfoElemento> response = new ArrayList<InfoElemento>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			infraestructuraValidators.validarElementoPorRegionParams(request);
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceElementoPorRegionParams())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(InfoElemento.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<InfoElemento>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de elementos por provincia y params
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	@SuppressWarnings("unchecked")
	public List<InfoElemento> elementoPorProvinciaParams(ElementoPorProvinciaParamsReqDTO request) throws GenericException {
		List<InfoElemento> response = new ArrayList<InfoElemento>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			infraestructuraValidators.validarElementoPorProvinciaParams(request);
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceElementoPorProvinciaParams())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(InfoElemento.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<InfoElemento>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de elementos por parroquia y params
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	@SuppressWarnings("unchecked")
	public List<InfoElemento> elementoPorParroquiaParams(ElementoPorParroquiaParamsReqDTO request) throws GenericException {
		List<InfoElemento> response = new ArrayList<InfoElemento>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			infraestructuraValidators.validarElementoPorParroquiaParams(request);
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceElementoPorParroquiaParams())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(InfoElemento.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<InfoElemento>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de elementos por canton y params
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	@SuppressWarnings("unchecked")
	public List<InfoElemento> elementoPorCantonParams(ElementoPorCantonParamsReqDTO request) throws GenericException {
		List<InfoElemento> response = new ArrayList<InfoElemento>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			infraestructuraValidators.validarElementoPorCantonParams(request);
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceElementoPorCantonParams())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(InfoElemento.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<InfoElemento>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de elementos por filial y params
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	@SuppressWarnings("unchecked")
	public List<InfoElemento> elementoPorFilialParams(ElementoPorFilialParamsReqDTO request) throws GenericException {
		List<InfoElemento> response = new ArrayList<InfoElemento>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			infraestructuraValidators.validarElementoPorFilialParams(request);
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceElementoPorFilialParams())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(InfoElemento.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<InfoElemento>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
}
