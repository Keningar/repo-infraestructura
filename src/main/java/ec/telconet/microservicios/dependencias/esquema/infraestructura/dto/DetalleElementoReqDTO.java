package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;

/**
 * DTO Request Detalle Elemento
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Data
public class DetalleElementoReqDTO {
	private Long elementoId;
	private String nombreElemento;
	private String estado;
}
