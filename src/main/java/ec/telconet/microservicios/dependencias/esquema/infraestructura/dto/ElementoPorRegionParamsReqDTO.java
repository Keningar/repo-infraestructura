package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO Request Elemento por Region y params
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ElementoPorRegionParamsReqDTO extends ElementoReqDTO {
	private String region;
	private Long tipoId;
	private String nombreTipo;
	private String detalle;
	private String detalleValor;
}
