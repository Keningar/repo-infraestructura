package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;

/**
 * DTO Request ServicioRecursoDet
 * 
 * @author Karen Rodríguez Véliz <mailto:kyrdoriguez@telconet.ec>
 * @version 1.0
 * @since 19/05/2020
 */
@Data
public class ServicioRecursoDetDTO {
	private Long servicioRecursoCabId;
	private Long elementoId;
	private Long cantidad;
	private String descripcion;
}
