package ec.telconet.microservicios.dependencias.esquema.infraestructura.service.impl;

import java.sql.Clob;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicio.dependencia.util.general.DateDeserializer;
import ec.telconet.microservicio.dependencia.util.general.Formato;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementosOltDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ObtenerPmReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.OltDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.OltResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InkgProcesosMasivosService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraConstants;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraProperties;

/**
 * Impl Service del paquete INKG_ELEMENTO_CONSULTA, se deberá crear un DTO por procedimiento o función que no estén
 * relacionado a una entidad en especifico
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public class InkgProcesosMasivosServiceImpl implements InkgProcesosMasivosService {
    
    Logger log = LogManager.getLogger(this.getClass());
    
    @Autowired
    @Qualifier("jdbcInfraestructura")
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private InfraestructuraProperties infraestructuraProperties;
    
    /**
     * Método que retorna los datos de un vehículo
     * 
     * @author Jesús Bozada <mailto:jbozada@telconet.ec>
     * @version 1.0
     * @since 30/08/2022
     */
    public ElementosOltDTO obtenerPmCorteReactivacion(ObtenerPmReqDTO request) throws GenericException {
        ElementosOltDTO response = new ElementosOltDTO();
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
            
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getPaqueteProcesosMasivos())
                    .withProcedureName(infraestructuraProperties.getProceObtenerPm())
                    .declareParameters(
                            new SqlOutParameter("PCL_JSONRESPONSE", Types.CLOB));
            
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            
            String status = (String) parametrosOut.get("PV_STATUS");
            String mensaje = (String) parametrosOut.get("PV_MENSAJE");
            log.info("obtenerPm status: {}", status);
            log.info("obtenerPm mensaje: {}", mensaje);
            if (status.equalsIgnoreCase("ERROR")) { //TODO validar flujo de error
                throw new GenericException(mensaje);
            }
            
            Clob clobJsonResponse = (Clob) parametrosOut.get("PCL_JSONRESPONSE");
            String stringJsonResponse = Formato.getStringToCLOB(clobJsonResponse);
            response = gsonBuilder.create().fromJson(stringJsonResponse, ElementosOltDTO.class);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    
    public OltDTO procesarPeticionMw(OltDTO request) {
        log.info("MW INICIO OLT: {}", request.getNombreOlt());
        OltResDTO responseMw = new OltResDTO();
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request.getRequest()));
            
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getPaqueteProcesosMasivos())
                    .withProcedureName(infraestructuraProperties.getProceProcesarMw())
                    .declareParameters(
                            new SqlOutParameter("PCL_JSONRESPONSE", Types.CLOB));
            
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            
            String status = (String) parametrosOut.get("PV_STATUS");
            String mensaje = (String) parametrosOut.get("PV_MENSAJE");
            log.info("MW status: {}", status);
            log.info("MW mensaje: {}", mensaje);
            if (status.equalsIgnoreCase("ERROR")) {
                log.info("hilo mw proceso ERROR: {} , respuesta: {}", request.getNombreOlt(), mensaje);
            }
            Clob clobJsonResponse = (Clob) parametrosOut.get("PCL_JSONRESPONSE");
            String stringJsonResponse = Formato.getStringToCLOB(clobJsonResponse);
            log.info("MW response : {}", stringJsonResponse);
            responseMw = gsonBuilder.create().fromJson(stringJsonResponse, OltResDTO.class);
            request.setResponse(responseMw);
        } catch (Exception e) {
            log.error("hilo mw proceso EXCEPTION: {}", e.getMessage());
        }
        log.info("MW FIN OLT: {}", request.getNombreOlt());
        return request;
    }
    
    public OltDTO procesarTelcos(OltDTO request) {
        try {
            log.info("TELCOS INICIO OLT: {}", request.getNombreOlt());
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
            
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getPaqueteProcesosMasivos())
                    .withProcedureName(infraestructuraProperties.getProceProcesarTelcos());
            
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            
            String status = (String) parametrosOut.get("PV_STATUS");
            String mensaje = (String) parametrosOut.get("PV_MENSAJE");
            log.info("TELCOS status: {}", status);
            log.info("TELCOS mensaje: {}", mensaje);
            if (status.equalsIgnoreCase("ERROR")) {
                log.info("hilo telcos proceso ERROR: {} , respuesta: {}", request.getNombreOlt(), mensaje);
            }
        } catch (Exception e) {
            log.error("hilo telcos proceso EXCEPTION: {}", e.getMessage());
        }
        log.info("TELCOS FIN OLT: {}", request.getNombreOlt());
        return request;
    }
    
    public void procesarCabecerasPm(OltDTO request){
        try {
            log.info("PROCESAR CABECERAS PM INICIO");
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
            
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getPaqueteProcesosMasivos())
                    .withProcedureName(infraestructuraProperties.getProceProcesarCabecerasPm());
            
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            call.execute(parametrosSource);
        } catch (Exception e) {
            log.error("hilo procesaCabeceras EXCEPTION: {}", e.getMessage());
        }
        log.info("PROCESAR CABECERAS PM FIN");
    }
}
