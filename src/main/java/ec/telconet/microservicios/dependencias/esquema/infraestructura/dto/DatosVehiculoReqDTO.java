package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;

/**
 * DTO Request DatosVehiculoReqDTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 05/05/2020
 */
@Data
public class DatosVehiculoReqDTO {
	private Long idElemento;
	private String nombreDetalle;
	private String valorDetalle;
	private String estado;
}
