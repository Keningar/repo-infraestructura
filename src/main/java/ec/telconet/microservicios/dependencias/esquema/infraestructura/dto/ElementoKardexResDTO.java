package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;

@Data
public class ElementoKardexResDTO {
	private String anio;
	private String disco;
	private String nombreElemento;
	private String kmMaximo;
}
