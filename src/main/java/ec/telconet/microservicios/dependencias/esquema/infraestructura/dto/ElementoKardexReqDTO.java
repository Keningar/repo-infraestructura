package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)

public class ElementoKardexReqDTO {
	private String discoElemento;
	private String nombreElemento;
	private String regionElemento;
	private String tipoElemento;
}
