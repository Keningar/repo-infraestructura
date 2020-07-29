package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO Request para guardar la factibilidad de CLOUD IAAS
 * POOL DE RECURSOS LOC para Alquiler de Servidor
 *
 * @author Karen Rodríguez Véliz <mailto:kyrdoriguez@telconet.ec>
 * @version 1.0
 * @since 03/06/2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FactibPoolServidorReqDTO extends DatosPrincipalesSolucionReqDTO {
	private DataSolicitud               dataSolicitud;
    private List<ServicioRecursoDetDTO> factibilidad;
}
@Data
class DataSolicitud {
	private String 			    estado;
	private ServicioHistorial   servicioHistorial;
	private Solicitud           solicitud;
	private HistorialSolicitud  historialSolicitud;
}
@Data
class ServicioHistorial {
    private String observacion;
}
@Data
class Solicitud {
	private Long   idDetalleSolicitud;
    private String observacion;
}
@Data
class HistorialSolicitud {
    private String observacion;
}
