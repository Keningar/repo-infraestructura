package ec.telconet.microservicios.dependencias.esquema.infraestructura.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicio.dependencia.util.general.DateDeserializer;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.UbicacionElementoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InkgElementoTransaccionService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraConstants;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraProperties;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraValidators;

/**
 * Impl Service del paquete INKG_ELEMENTO_TRANSACCION, se deberá crear un DTO por procedimiento o función que no estén
 * relacionado a una entidad en especifico
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public class InkgElementoTransaccionImpl implements InkgElementoTransaccionService {
	@Autowired
	private InfraestructuraValidators infraestructuraValidators;
	
	@Autowired
	@Qualifier("jdbcInfraestructura")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private InfraestructuraProperties infraestructuraProperties;
	
	/**
	 * Método que asigna una ubicación a un elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 04/08/2020
	 */
	public String asignarUbicacionElemento(UbicacionElementoReqDTO request) throws GenericException {
		String response;
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			infraestructuraValidators.validarAsignarUbicacionElemento(request);
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoTransaccion())
					.withProcedureName(infraestructuraProperties.getProceAsignarUbicacionElemento());
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = mensaje;
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que modifique una ubicación a un elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 04/08/2020
	 */
	public String modificarUbicacionElemento(UbicacionElementoReqDTO request) throws GenericException {
		String response;
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			
			infraestructuraValidators.validarModificarUbicacionElemento(request);
			Map<String, Object> parametrosIn = new HashMap<String, Object>();
			parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
			
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
					.withCatalogName(infraestructuraProperties.getPaqueteElementoTransaccion())
					.withProcedureName(infraestructuraProperties.getProceModificarUbicacionElemento());
			
			SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
			Map<String, Object> parametrosOut = call.execute(parametrosSource);
			
			String status = (String) parametrosOut.get("PV_STATUS");
			String mensaje = (String) parametrosOut.get("PV_MENSAJE");
			if (status.equalsIgnoreCase("ERROR")) {
				throw new GenericException(mensaje);
			}
			
			response = mensaje;
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
}
