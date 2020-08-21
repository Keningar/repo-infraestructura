package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO Request Elemento por cuadrilla y params
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 21/08/2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ElementoPorCuadrillaParamsReqDTO extends ElementoReqDTO {
	private Long cuadrillaId;
	private String nombreCuadrilla;
}
