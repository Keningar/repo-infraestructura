package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;

/**
 * DTO Request Elemento por grupo
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 23/06/2020
 */
@Data
public class ElementoPorGrupoReqDTO {
	private Long grupoId;
	private Long elementoId;
	private String estado;
}
