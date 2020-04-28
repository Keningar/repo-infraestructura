package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO Request Elemento por Filial y params
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ElementoPorFilialParamsReqDTO extends ElementoReqDTO {
	private Long filialId;
	private String nombreFilial;
	private Integer tipoId;
	private String nombreTipo;
	private String detalle;
	private String detalleValor;
}
