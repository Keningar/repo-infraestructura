package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO Request Elemento por Canton y params
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ElementoPorCantonParamsReqDTO extends ElementoReqDTO {
	private Long cantonId;
	private String nombreCanton;
	private Long tipoId;
	private String nombreTipo;
	private String detalle;
	private String detalleValor;
}
