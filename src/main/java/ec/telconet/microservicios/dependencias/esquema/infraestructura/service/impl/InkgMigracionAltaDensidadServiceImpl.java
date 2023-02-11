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
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ListaMigracionCabDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.MigracionCabDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InkgMigracionAltaDensidadService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraConstants;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraProperties;

/**
 * Impl Service del paquete INKG_ELEMENTO_CONSULTA, se deberá crear un DTO por procedimiento o función que no estén
 * relacionado a una entidad en especifico
 * 
 * @author Jesús Bozada <jbozada@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public class InkgMigracionAltaDensidadServiceImpl implements InkgMigracionAltaDensidadService {
    
    Logger log = LogManager.getLogger(this.getClass());
    
    @Autowired
    @Qualifier("jdbcInfraestructura")
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private InfraestructuraProperties infraestructuraProperties;
    
    /**
     * Método que retorna las cabeceras de Migración a procesar
     * Aquí se recuperan cabeceras nuevas Pendiente, OK_CLIENTES_CONTINUA y a ERROR_CLIENTES_REVERSA
     * 
     * @author Jesús Bozada <mailto:jbozada@telconet.ec>
     * @version 1.0
     * @since 26/01/2023
     */
    public ListaMigracionCabDTO obtenerCabMigracion() throws GenericException {
        ListaMigracionCabDTO response = new ListaMigracionCabDTO();
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getPaqueteMigracionAltaDensidad())
                    .withProcedureName(infraestructuraProperties.getProceObtenerCabMigracion())
                    .declareParameters(
                            new SqlOutParameter("PCL_JSONRESPONSE", Types.CLOB));
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            String status = (String) parametrosOut.get("PV_STATUS");
            String mensaje = (String) parametrosOut.get("PV_MENSAJE");
            log.info("obtenerCabMigracion status: {}", status);
            log.info("obtenerCabMigracion mensaje: {}", mensaje);
            if (status.equalsIgnoreCase("ERROR")) {
                throw new GenericException(mensaje);
            }
            Clob clobJsonResponse = (Clob) parametrosOut.get("PCL_JSONRESPONSE");
            String stringJsonResponse = Formato.getStringToCLOB(clobJsonResponse);
            response = gsonBuilder.create().fromJson(stringJsonResponse, ListaMigracionCabDTO.class);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    
    /**
     * Método que retorna las cabeceras de Migración a procesar
     * 
     * @author Jesús Bozada <mailto:jbozada@telconet.ec>
     * @version 1.0
     * @since 26/01/2023
     */
    public MigracionCabDTO validarCabMigracion(MigracionCabDTO request) throws GenericException {
        MigracionCabDTO response = new MigracionCabDTO();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        Map<String, Object> parametrosIn = new HashMap<String, Object>();
        parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                .withCatalogName(infraestructuraProperties.getPaqueteMigracionAltaDensidad())
                .withProcedureName(infraestructuraProperties.getProceValidarCabMigracion())
                .declareParameters(
                        new SqlOutParameter("PCL_JSONRESPONSE", Types.CLOB));
        SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
        Map<String, Object> parametrosOut = call.execute(parametrosSource);
        String status = (String) parametrosOut.get("PV_STATUS");
        String mensaje = (String) parametrosOut.get("PV_MENSAJE");
        log.info("validarCabMigracion status: {}", status);
        log.info("validarCabMigracion mensaje: {}", mensaje);
        Clob clobJsonResponse = (Clob) parametrosOut.get("PCL_JSONRESPONSE");
        String stringJsonResponse = Formato.getStringToCLOB(clobJsonResponse);
        response = gsonBuilder.create().fromJson(stringJsonResponse, MigracionCabDTO.class);
        return response;
    }
    
    /**
     * Método que retorna las cabeceras de Migración a procesar
     * 
     * @author Jesús Bozada <mailto:jbozada@telconet.ec>
     * @version 1.0
     * @since 26/01/2023
     */
    public MigracionCabDTO obtenerSplittersAgrupados(MigracionCabDTO request) throws GenericException {
        MigracionCabDTO response = new MigracionCabDTO();
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            request.setTipoRegistro("SPLITTER");
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getPaqueteMigracionAltaDensidad())
                    .withProcedureName(infraestructuraProperties.getProceAgruparDetalles())
                    .declareParameters(
                            new SqlOutParameter("PCL_JSONRESPONSE", Types.CLOB));
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            String status = (String) parametrosOut.get("PV_STATUS");
            String mensaje = (String) parametrosOut.get("PV_MENSAJE");
            log.info("obtenerSplittersAgrupados status: {}", status);
            log.info("obtenerSplittersAgrupados mensaje: {}", mensaje);
            if (status.equalsIgnoreCase("ERROR")) {
                throw new GenericException(mensaje);
            }
            Clob clobJsonResponse = (Clob) parametrosOut.get("PCL_JSONRESPONSE");
            String stringJsonResponse = Formato.getStringToCLOB(clobJsonResponse);
            response = gsonBuilder.create().fromJson(stringJsonResponse, MigracionCabDTO.class);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    
    /**
     * Método que retorna las cabeceras de Migración a procesar
     * 
     * @author Jesús Bozada <mailto:jbozada@telconet.ec>
     * @version 1.0
     * @since 26/01/2023
     */
    public MigracionCabDTO obtenerEnlacesAgrupados(MigracionCabDTO request) throws GenericException {
        MigracionCabDTO response = new MigracionCabDTO();
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            request.setTipoRegistro("ENLACE");
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getPaqueteMigracionAltaDensidad())
                    .withProcedureName(infraestructuraProperties.getProceAgruparDetalles())
                    .declareParameters(
                            new SqlOutParameter("PCL_JSONRESPONSE", Types.CLOB));
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            String status = (String) parametrosOut.get("PV_STATUS");
            String mensaje = (String) parametrosOut.get("PV_MENSAJE");
            log.info("obtenerEnlacesAgrupados status: {}", status);
            log.info("obtenerEnlacesAgrupados mensaje: {}", mensaje);
            if (status.equalsIgnoreCase("ERROR")) {
                throw new GenericException(mensaje);
            }
            Clob clobJsonResponse = (Clob) parametrosOut.get("PCL_JSONRESPONSE");
            String stringJsonResponse = Formato.getStringToCLOB(clobJsonResponse);
            response = gsonBuilder.create().fromJson(stringJsonResponse, MigracionCabDTO.class);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    
    /**
     * Método que retorna las cabeceras de Migración a procesar
     * 
     * @author Jesús Bozada <mailto:jbozada@telconet.ec>
     * @version 1.0
     * @since 26/01/2023
     */
    public MigracionCabDTO obtenerClientesAgrupados(MigracionCabDTO request) throws GenericException {
        MigracionCabDTO response = new MigracionCabDTO();
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            request.setTipoRegistro("OLT");
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getPaqueteMigracionAltaDensidad())
                    .withProcedureName(infraestructuraProperties.getProceAgruparDetalles())
                    .declareParameters(
                            new SqlOutParameter("PCL_JSONRESPONSE", Types.CLOB));
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            String status = (String) parametrosOut.get("PV_STATUS");
            String mensaje = (String) parametrosOut.get("PV_MENSAJE");
            log.info("obtenerClientesAgrupados status: {}", status);
            log.info("obtenerClientesAgrupados mensaje: {}", mensaje);
            if (status.equalsIgnoreCase("ERROR")) {
                throw new GenericException(mensaje);
            }
            Clob clobJsonResponse = (Clob) parametrosOut.get("PCL_JSONRESPONSE");
            String stringJsonResponse = Formato.getStringToCLOB(clobJsonResponse);
            response = gsonBuilder.create().fromJson(stringJsonResponse, MigracionCabDTO.class);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    
    /**
     * Método que retorna las cabeceras de Migración a procesar
     * 
     * @author Jesús Bozada <mailto:jbozada@telconet.ec>
     * @version 1.0
     * @since 26/01/2023
     */
    public MigracionCabDTO obtenerScopesAgrupados(MigracionCabDTO request) throws GenericException {
        MigracionCabDTO response = new MigracionCabDTO();
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            request.setTipoRegistro("SCOPE");
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getPaqueteMigracionAltaDensidad())
                    .withProcedureName(infraestructuraProperties.getProceAgruparDetalles())
                    .declareParameters(
                            new SqlOutParameter("PCL_JSONRESPONSE", Types.CLOB));
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            String status = (String) parametrosOut.get("PV_STATUS");
            String mensaje = (String) parametrosOut.get("PV_MENSAJE");
            log.info("obtenerScopesAgrupados status: {}", status);
            log.info("obtenerScopesAgrupados mensaje: {}", mensaje);
            if (status.equalsIgnoreCase("ERROR")) {
                throw new GenericException(mensaje);
            }
            Clob clobJsonResponse = (Clob) parametrosOut.get("PCL_JSONRESPONSE");
            String stringJsonResponse = Formato.getStringToCLOB(clobJsonResponse);
            response = gsonBuilder.create().fromJson(stringJsonResponse, MigracionCabDTO.class);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }

    /**
     * Método que retorna las cabeceras de Migración a procesar
     * 
     * @author Jesús Bozada <mailto:jbozada@telconet.ec>
     * @version 1.0
     * @since 26/01/2023
     */
    public MigracionCabDTO reversarCabMigracion(MigracionCabDTO request) throws GenericException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        Map<String, Object> parametrosIn = new HashMap<String, Object>();
        parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                .withCatalogName(infraestructuraProperties.getPaqueteMigracionAltaDensidad())
                .withProcedureName(infraestructuraProperties.getProceReversarCabMigracion())
                .declareParameters(
                        new SqlOutParameter("PCL_JSONRESPONSE", Types.CLOB));
        SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
        Map<String, Object> parametrosOut = call.execute(parametrosSource);
        String status = (String) parametrosOut.get("PV_STATUS");
        String mensaje = (String) parametrosOut.get("PV_MENSAJE");
        log.info("reversarCabMigracion status: {}", status);
        log.info("reversarCabMigracion mensaje: {}", mensaje);
        request.setEstado(status);
        return request;
    }
    
    /**
     * Método que retorna las cabeceras de Migración a procesar
     * 
     * @author Jesús Bozada <mailto:jbozada@telconet.ec>
     * @version 1.0
     * @since 26/01/2023
     */
    public void procesarOlts(MigracionCabDTO request){
        try {
            log.info("PROCESAR OLTS INICIO");
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getPaqueteMigracionAltaDensidad())
                    .withProcedureName(infraestructuraProperties.getProceProcesarOlts());
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            String status = (String) parametrosOut.get("PV_STATUS");
            String mensaje = (String) parametrosOut.get("PV_MENSAJE");
            log.info("procesarOlts status: {}", status);
            log.info("procesarOlts mensaje: {}", mensaje);
        } catch (Exception e) {
            log.error("hilo procesarOlts EXCEPTION: {}", e.getMessage());
        }
        log.info("PROCESAR OLTS FIN");
    }

    /**
     * Método que retorna las cabeceras de Migración a procesar
     * 
     * @author Jesús Bozada <mailto:jbozada@telconet.ec>
     * @version 1.0
     * @since 26/01/2023
     */
    public void procesarSplitters(MigracionCabDTO request){
        try {
            log.info("PROCESAR SPLITTERS INICIO");
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getPaqueteMigracionAltaDensidad())
                    .withProcedureName(infraestructuraProperties.getProceProcesarSplitters());
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            String status = (String) parametrosOut.get("PV_STATUS");
            String mensaje = (String) parametrosOut.get("PV_MENSAJE");
            log.info("procesarSplitters status: {}", status);
            log.info("procesarSplitters mensaje: {}", mensaje);
        } catch (Exception e) {
            log.error("hilo procesarSplitters EXCEPTION: {}", e.getMessage());
        }
        log.info("PROCESAR SPLITTERS FIN");
    }

    /**
     * Método que retorna las cabeceras de Migración a procesar
     * 
     * @author Jesús Bozada <mailto:jbozada@telconet.ec>
     * @version 1.0
     * @since 26/01/2023
     */
    public void procesarEnlaces(MigracionCabDTO request){
        try {
            log.info("PROCESAR ENLACES INICIO");
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
            
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getPaqueteMigracionAltaDensidad())
                    .withProcedureName(infraestructuraProperties.getProceProcesarEnlaces());
            
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            String status = (String) parametrosOut.get("PV_STATUS");
            String mensaje = (String) parametrosOut.get("PV_MENSAJE");
            log.info("procesarEnlaces status: {}", status);
            log.info("procesarEnlaces mensaje: {}", mensaje);
        } catch (Exception e) {
            log.error("hilo procesarEnlaces EXCEPTION: {}", e.getMessage());
        }
        log.info("PROCESAR ENLACES FIN");
    }
    
    /**
     * Método que retorna las cabeceras de Migración a procesar
     * 
     * @author Jesús Bozada <mailto:jbozada@telconet.ec>
     * @version 1.0
     * @since 26/01/2023
     */
    public void procesarClientes(MigracionCabDTO request){
        try {
            log.info("PROCESAR CLIENTES INICIO");
            //request.setTipoProceso("CLIENTE");
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getPaqueteMigracionAltaDensidad())
                    .withProcedureName(infraestructuraProperties.getProceProcesarClientes());
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            String status = (String) parametrosOut.get("PV_STATUS");
            String mensaje = (String) parametrosOut.get("PV_MENSAJE");
            log.info("procesarClientes status: {}", status);
            log.info("procesarClientes mensaje: {}", mensaje);
        } catch (Exception e) {
            log.error("hilo procesarClientes EXCEPTION: {}", e.getMessage());
        }
        log.info("PROCESAR CLIENTES FIN");
    }
    
    /**
     * Método que retorna las cabeceras de Migración a procesar
     * 
     * @author Jesús Bozada <mailto:jbozada@telconet.ec>
     * @version 1.0
     * @since 26/01/2023
     */
    public void procesarScopes(MigracionCabDTO request){
        try {
            log.info("PROCESAR SCOPES INICIO");
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_JsonRequest", gsonBuilder.create().toJson(request));
            log.info("PROCESAR SCOPES Pcl_JsonRequest: "+gsonBuilder.create().toJson(request));
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getPaqueteMigracionAltaDensidad())
                    .withProcedureName(infraestructuraProperties.getProceProcesarScopes());
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            String status = (String) parametrosOut.get("PV_STATUS");
            String mensaje = (String) parametrosOut.get("PV_MENSAJE");
            log.info("procesarScopes status: {}", status);
            log.info("procesarScopes mensaje: {}", mensaje);
        } catch (Exception e) {
            log.error("hilo procesarScopes EXCEPTION: {}", e.getMessage());
        }
        log.info("PROCESAR SCOPES FIN");
    }
}
