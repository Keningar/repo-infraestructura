package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO Request Historial Elemento por Fechas
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HistorialElementoPorFechaReqDTO extends HistorialElementoReqDTO {
	private String fechaInicio;
	private String fechaFin;
}
