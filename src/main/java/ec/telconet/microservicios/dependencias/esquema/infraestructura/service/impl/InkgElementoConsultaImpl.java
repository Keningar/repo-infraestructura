package ec.telconet.microservicios.dependencias.esquema.infraestructura.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicio.dependencia.util.general.DateDeserializer;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.DatosVehiculoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.DatosVehiculoResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoKardexReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoKardexResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorGrupoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorGrupoResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorPlacaReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorTipoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ListaKardexReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ListaKardexResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ListaPlacaDiscoResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ModelosElemMonitorizadosReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ModelosElemMonitorizadosResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.TareasKardexReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.TareasKardexResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.TotalRegistrosKardexResDTo;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InkgElementoConsultaService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraConstants;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraProperties;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraValidators;

/**
 * Impl Service del paquete INKG_ELEMENTO_CONSULTA, se deberá crear un DTO por procedimiento o función que no estén
 * relacionado a una entidad en especifico
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public class InkgElementoConsultaImpl implements InkgElementoConsultaService {
	@Autowired
	private InfraestructuraValidators infraestructuraValidators;
	
	@Autowired
	@Qualifier("jdbcInfraestructura")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private InfraestructuraProperties infraestructuraProperties;
	
	/**
	 * Método que retorna los datos de un vehículo
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 05/05/2020
	 */
	@SuppressWarnings("unchecked")
	public List<DatosVehiculoResDTO> datosVehiculo(DatosVehiculoReqDTO request) throws GenericException {
		List<DatosVehiculoResDTO> response = new ArrayList<DatosVehiculoResDTO>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			infraestructuraValidators.validarDatosVehiculo(request);
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceDatosVehiculo())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(DatosVehiculoResDTO.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<DatosVehiculoResDTO>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna los elementos de un grupo de monitorización
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 23/06/2020
	 */
	@SuppressWarnings("unchecked")
	public List<ElementoPorGrupoResDTO> elementoPorGrupo(ElementoPorGrupoReqDTO request) throws GenericException {
		List<ElementoPorGrupoResDTO> response = new ArrayList<ElementoPorGrupoResDTO>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			infraestructuraValidators.validarElementoPorGrupo(request);
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceElementoPorGrupo())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(ElementoPorGrupoResDTO.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<ElementoPorGrupoResDTO>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	public List<ModelosElemMonitorizadosResDTO> modelosElementoMonitorizado(ModelosElemMonitorizadosReqDTO request) throws GenericException {
		List<ModelosElemMonitorizadosResDTO> response = new ArrayList<ModelosElemMonitorizadosResDTO>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceModelosElemMonitorizados())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(ModelosElemMonitorizadosResDTO.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<ModelosElemMonitorizadosResDTO>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de registros del kardex
	 * 
	 * @author José Castillo <mailto:jmcastillo@telconet.ec>
	 * @version 1.0
	 * @since 16/05/2023
	 */
	@SuppressWarnings("unchecked")
	public List<ListaKardexResDTO> listaKardex(ListaKardexReqDTO request) throws GenericException {
		List<ListaKardexResDTO> response = new ArrayList<ListaKardexResDTO>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceKardexDetalle())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(ListaKardexResDTO.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<ListaKardexResDTO>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna el total de registros del kardex
	 * 
	 * @author José Castillo <mailto:jmcastillo@telconet.ec>
	 * @version 1.0
	 * @since 16/05/2023
	 */
	@SuppressWarnings("unchecked")
	public List<TotalRegistrosKardexResDTo> totalRegistrosKardex(ListaKardexReqDTO request) throws GenericException {
		List<TotalRegistrosKardexResDTo> response = new ArrayList<TotalRegistrosKardexResDTo>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceKardexTotal())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(TotalRegistrosKardexResDTo.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<TotalRegistrosKardexResDTo>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de registros del kardex
	 * 
	 * @author José Castillo <mailto:jmcastillo@telconet.ec>
	 * @version 1.0
	 * @since 16/05/2023
	 */
	@SuppressWarnings("unchecked")
	public List<ElementoKardexResDTO> elementoKardex(ElementoKardexReqDTO request) throws GenericException {
		List<ElementoKardexResDTO> response = new ArrayList<ElementoKardexResDTO>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceDetalleElementoKardex())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(ElementoKardexResDTO.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<ElementoKardexResDTO>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	

	/**
	 * Método que retorna la lista de registros del kardex
	 * 
	 * @author José Castillo <mailto:jmcastillo@telconet.ec>
	 * @version 1.0
	 * @since 16/05/2023
	 */
	@SuppressWarnings("unchecked")
	public List<TareasKardexResDTO> kardexTarea(TareasKardexReqDTO request) throws GenericException {
		List<TareasKardexResDTO> response = new ArrayList<TareasKardexResDTO>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceKardexTarea())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(TareasKardexResDTO.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<TareasKardexResDTO>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de placas correspondientes al elemento vehiculo
	 * 
	 * @author José Castillo <mailto:jmcastillo@telconet.ec>
	 * @version 1.0
	 * @since 16/05/2023
	 */
	@SuppressWarnings("unchecked")
	public List<ListaPlacaDiscoResDTO> placasVehiculo(ElementoPorPlacaReqDTO request) throws GenericException {
		List<ListaPlacaDiscoResDTO> response = new ArrayList<ListaPlacaDiscoResDTO>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());	
			
			infraestructuraValidators.validarElementoPorTipoPlaca(request);
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceElementoPorTipoPlaca())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(ListaPlacaDiscoResDTO.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<ListaPlacaDiscoResDTO>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
}
