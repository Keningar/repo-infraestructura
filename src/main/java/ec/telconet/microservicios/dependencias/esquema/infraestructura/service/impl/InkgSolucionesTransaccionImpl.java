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
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.MaquinaVirtualReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.FactibPoolServidorReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.FactibilidadMaquinaVirtualReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.FactibilidadServidorReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InkgSolucionesTransaccionService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraConstants;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraProperties;

/**
 * Impl Service del paquete INKG_SOLUCIONES_TRANSACCION, se deberá crear un DTO por procedimiento o función que no estén
 * relacionado a una entidad en especifico
 * 
 * @author Karen Rodríguez Véliz <mailto:kyrdoriguez@telconet.ec>
 * @version 1.0
 * @since 19/05/2020
 */
@Service
public class InkgSolucionesTransaccionImpl implements InkgSolucionesTransaccionService {
	 @Autowired
     @Qualifier("jdbcInfraestructura")
     private JdbcTemplate jdbcTemplate;
	
	 @Autowired
	 private InfraestructuraProperties infraestructuraProperties;
	 
	 /**
     * Método encargado de crear máquina virtual de una solución DC.
     *
     * @author Karen Rodríguez Véliz <mailto:kyrdoriguez@telconet.ec>
	 * @version 1.0
	 * @since 19/05/2020
     *
     * @param  MaquinaVirtualReqDTO request
     * @return String
     * @throws GenericException
     */
    public String crearMaquinaVirtual(MaquinaVirtualReqDTO request) throws GenericException {
	        String mensaje;
	        try {
	            GsonBuilder gsonBuilder = new GsonBuilder();
	            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
	            Map<String, Object> parametrosIn = new HashMap<String, Object>();
	            parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
	            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
	                    .withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
	                    .withCatalogName(infraestructuraProperties.getNombrePaqueteSolucionesTrans())
	                    .withProcedureName(infraestructuraProperties.getProceCrearMaquinaVirtual());
	            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
	            Map<String, Object> parametrosOut = call.execute(parametrosSource);
	            String status = (String) parametrosOut.get("PV_STATUS");
	            mensaje = (String) parametrosOut.get("PV_MENSAJE");
	            if (status.equalsIgnoreCase("ERROR")) {
	                throw new GenericException(mensaje);
	            }
	        } catch (GenericException e) {
	            throw new GenericException(e.getMessageError(), e.getCodeError());
	        }
	        return mensaje;
	    }

	@Override
	public String crearFactibilidadMV(FactibilidadMaquinaVirtualReqDTO request) throws GenericException {
		String mensaje;
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getNombrePaqueteSolucionesTrans())
                    .withProcedureName(infraestructuraProperties.getProceCrearFactibilidadMV());
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            String status = (String) parametrosOut.get("PV_STATUS");
            mensaje = (String) parametrosOut.get("PV_MENSAJE");
            if (status.equalsIgnoreCase("ERROR")) {
                throw new GenericException(mensaje);
            }
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return mensaje;
	}

	@Override
	public String crearFactibilidadServidor(FactibilidadServidorReqDTO request) throws GenericException {
		String mensaje;
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getNombrePaqueteSolucionesTrans())
                    .withProcedureName(infraestructuraProperties.getProceCrearFactibilidadServidor());
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            String status = (String) parametrosOut.get("PV_STATUS");
            mensaje = (String) parametrosOut.get("PV_MENSAJE");
            if (status.equalsIgnoreCase("ERROR")) {
                throw new GenericException(mensaje);
            }
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return mensaje;
	}

	@Override
	public String crearFactibPoolServidor(FactibPoolServidorReqDTO request) throws GenericException {
		String mensaje;
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            Map<String, Object> parametrosIn = new HashMap<String, Object>();
            parametrosIn.put("Pcl_Request", gsonBuilder.create().toJson(request));
            SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                    .withSchemaName(InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
                    .withCatalogName(infraestructuraProperties.getNombrePaqueteSolucionesTrans())
                    .withProcedureName(infraestructuraProperties.getProceCrearFactibPoolServidor());
            SqlParameterSource parametrosSource = new MapSqlParameterSource().addValues(parametrosIn);
            Map<String, Object> parametrosOut = call.execute(parametrosSource);
            String status = (String) parametrosOut.get("PV_STATUS");
            mensaje = (String) parametrosOut.get("PV_MENSAJE");
            if (status.equalsIgnoreCase("ERROR")) {
                throw new GenericException(mensaje);
            }
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return mensaje;
	} 
}
