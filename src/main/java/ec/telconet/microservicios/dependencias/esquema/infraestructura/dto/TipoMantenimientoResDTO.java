package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;

/**
 * @author jmcastillo
 *
 */

@Data
public class TipoMantenimientoResDTO {
	private Long idTipoMantenimiento;
	private String descripcionMantenimiento;
}
