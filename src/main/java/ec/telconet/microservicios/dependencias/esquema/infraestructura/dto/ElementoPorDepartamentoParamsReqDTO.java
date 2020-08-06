package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO Request Elemento por departamento y params
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 17/07/2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ElementoPorDepartamentoParamsReqDTO extends ElementoReqDTO {
	private Long departamentoId;
	private String nombreDepartamento;
	private Long tipoId;
	private String nombreTipo;
}
