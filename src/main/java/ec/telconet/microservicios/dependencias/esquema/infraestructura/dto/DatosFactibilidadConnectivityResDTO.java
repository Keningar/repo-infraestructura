package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import java.util.List;

import lombok.Data;

/**
 * DTO Response para obtener la factibilidad
 * 
 * @author Antonio Ayala <mailto:afayala@telconet.ec>
 * @version 1.0
 * @since 21/07/2022
 *
 * @author Steven Ruano <mailto:sruano@telconet.ec>
 * @version 1.1
 * @since 12/04/2023 Agrega parametro para validar que pasa por el proceso nuevoAlgoritmo
 *
 */
@Data
public class DatosFactibilidadConnectivityResDTO {
	private Long idCaja;
    private String nombreCaja;
    private String estadoCaja;
    private Long idElementoConector;
    private Long idServicio;
    private String nombreElementoConector;
    private String estadoElementoConector;
    private Long idInterfaceElementoConector;
    private String nombreInterfaceElementoConector;
    private Float distancia;
    private String pasaNuevoAlgoritmo;	
}
