package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;

@Data
public class ModelosElemMonitorizadosResDTO {
	private Long idModeloElemento;
	private String nombreModeloElemento;
	private String descripcionModeloElemento;
	private Long marcaElementoId;
	private String nombreMarcaElemento;
	private Long tipoElementoId;
	private String nombreTipoElemento;
	private String estado;
}
