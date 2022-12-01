package ec.telconet.microservicios.dependencias.esquema.infraestructura.service.impl;

import java.sql.Clob;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import com.google.gson.GsonBuilder;

import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.DatosFactibilidadConnectivityReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.DatosFactibilidadConnectivityResDTO;
import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicio.dependencia.util.general.DateDeserializer;
import ec.telconet.microservicio.dependencia.util.general.Formato;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.PreFactibilidadConnectivityReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.PreFactibilidadConnectivityResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InkgFactibConnectivConsultaService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraConstants;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraProperties;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraValidators;

/**
 * Impl Service del paquete INKG_FACTIB_CONNECTIV_CONSULTA, se deberá crear un DTO por procedimiento o función que no estén
 * relacionado a una entidad en específico
 * 
 * @author Lizbeth Cruz <mailto:mlcruz@telconet.ec>
 * @version 1.0
 * @since 06/07/2021
 */
@Service
public class InkgFactibConnectivConsultaImpl implements InkgFactibConnectivConsultaService {
	@Autowired
	private InfraestructuraValidators infraestructuraValidators;
	
	@Autowired
	@Qualifier("jdbcInfraestructura")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private InfraestructuraProperties infraestructuraProperties;
	 
	 
	 /**
     * Método encargado de obtener la respuesta de prefactibilidad
     *
     * @author Lizbeth Cruz <mailto:mlcruz@telconet.ec>
	 * @version 1.0
	 * @since 06/07/2021
     *
     * @param  request {@linkplain PreFactibilidadConnectivityReqDTO}
     * @return {@linkplain PreFactibilidadConnectivityResDTO}
     * @throws GenericException
     */
    public PreFactibilidadConnectivityResDTO obtieneInfoPreFactibilidad(PreFactibilidadConnectivityReqDTO request) throws GenericException {
    	PreFactibilidadConnectivityResDTO response = new PreFactibilidadConnectivityResDTO();
	    try {
	        GsonBuilder gsonBuilder = new GsonBuilder();
	        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
	        
	        infraestructuraValidators.validarParamsObtieneInfoPreFactibilidad(request);
	        Map<String, Object> parametrosIn = new HashMap<String, Object>();
	        parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
	        
	        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
	                .withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
	                .withCatalogName(infraestructuraProperties.getPaqueteFactibilidadConnectivityConsulta())
	                .withProcedureName(infraestructuraProperties.getProceObtieneInfoPreFactibilidad())
	                .declareParameters(
							new SqlOutParameter("PCL_JSONRESPONSE", Types.CLOB));
	        SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
	        Map<String, Object> parametrosOut = call.execute(parametrosSource);
	        String status = (String) parametrosOut.get("PV_STATUS");
	        String mensaje = (String) parametrosOut.get("PV_MENSAJE");
	        if (status.equalsIgnoreCase("ERROR")) {
	            throw new GenericException(mensaje);
	        }
	        
	        Clob clobJsonResponse = (Clob) parametrosOut.get("PCL_JSONRESPONSE");
	        String stringJsonResponse = Formato.getStringToCLOB(clobJsonResponse);
	        response = gsonBuilder.create().fromJson(stringJsonResponse, PreFactibilidadConnectivityResDTO.class);
	        
	    } catch (GenericException e) {
	        throw new GenericException(e.getMessageError(), e.getCodeError());
	    }
	    return response;
    }
    
    /**
     * Método encargado de obtener la respuesta de factibilidad
     *
     * @author Antonio Ayala <mailto:afayala@telconet.ec>
	 * @version 1.0
	 * @since 21/07/2022
     *
     * @param  request {@linkplain DatosFactibilidadConnectivityReqDTO}
     * @return {@linkplain DatosFactibilidadConnectivityResDTO}
     * @throws GenericException
     */
    public DatosFactibilidadConnectivityResDTO obtenerDatosFactibilidad(DatosFactibilidadConnectivityReqDTO request) throws GenericException {
    	DatosFactibilidadConnectivityResDTO response = new DatosFactibilidadConnectivityResDTO();
	    try {
	        GsonBuilder gsonBuilder = new GsonBuilder();
	        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
	        
	        infraestructuraValidators.validarParamsObtenerDatosFactibilidad(request);
	        Map<String, Object> parametrosIn = new HashMap<String, Object>();
	        parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
	        
	        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
	                .withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
	                .withCatalogName(infraestructuraProperties.getPaqueteFactibilidadConnectivityConsulta())
	                .withProcedureName(infraestructuraProperties.getProceObtenerDatosFactibilidad())
	                .declareParameters(
							new SqlOutParameter("PCL_JSONRESPONSE", Types.CLOB));
	        SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
	        Map<String, Object> parametrosOut = call.execute(parametrosSource);
	        String status = (String) parametrosOut.get("PV_STATUS");
	        String mensaje = (String) parametrosOut.get("PV_MENSAJE");
	        if (status.equalsIgnoreCase("ERROR")) {
	            throw new GenericException(mensaje);
	        }
	        
	        Clob clobJsonResponse = (Clob) parametrosOut.get("PCL_JSONRESPONSE");
	        String stringJsonResponse = Formato.getStringToCLOB(clobJsonResponse);
	        response = gsonBuilder.create().fromJson(stringJsonResponse, DatosFactibilidadConnectivityResDTO.class);
	        
	    } catch (GenericException e) {
	        throw new GenericException(e.getMessageError(), e.getCodeError());
	    }
	    return response;
    }
}
