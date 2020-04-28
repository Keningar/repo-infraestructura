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
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.HistorialElementoPorFechaReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.HistorialElementoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoHistorialElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.InfoHistorialElementoRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InfoHistorialElementoService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraConstants;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraProperties;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraValidators;

/**
 * Impl Service INFO_HISTORIAL_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public class InfoHistorialElementoImpl implements InfoHistorialElementoService {
	@Autowired
	private InfraestructuraValidators infraestructuraValidators;
	
	@Autowired
	private InfoHistorialElementoRepository infoHistorialElementoRepo;
	
	@Autowired
	@Qualifier("jdbcInfraestructura")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private InfraestructuraProperties infraestructuraProperties;
	
	/**
	 * Método que guarda un historial de elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public InfoHistorialElemento guardar(InfoHistorialElemento request) throws GenericException {
		InfoHistorialElemento response = new InfoHistorialElemento();
		try {
			infraestructuraValidators.validarGuardarHistorialElemento(request);
			request.setFeCreacion(new Date());
			response = infoHistorialElementoRepo.save(request);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de historial de un elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	@SuppressWarnings("unchecked")
	public List<InfoHistorialElemento> historialElementoPorElemento(HistorialElementoReqDTO request) throws GenericException {
		List<InfoHistorialElemento> response = new ArrayList<InfoHistorialElemento>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			infraestructuraValidators.validarHistorialElementoPorElemento(request);
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceHistorialElementoPorElemento())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(InfoHistorialElemento.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<InfoHistorialElemento>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de historial de un elemento con un rango de fecha
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	@SuppressWarnings("unchecked")
	public List<InfoHistorialElemento> historialElementoPorFecha(HistorialElementoPorFechaReqDTO request) throws GenericException {
		List<InfoHistorialElemento> response = new ArrayList<InfoHistorialElemento>();
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			infraestructuraValidators.validarHistorialElementoPorFecha(request);
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoConsulta())
					.withProcedureName(infraestructuraProperties.getProceHistorialElementoPorFecha())
					.returningResultSet("PCL_RESPONSE", BeanPropertyRowMapper.newInstance(InfoHistorialElemento.class));
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = (List<InfoHistorialElemento>) parametrosOut.get("PCL_RESPONSE");
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
}
