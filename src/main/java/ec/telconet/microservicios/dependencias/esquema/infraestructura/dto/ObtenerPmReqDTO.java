package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;

@Data
public class ObtenerPmReqDTO {
	private String tipoProceso;
	private String empresaCod;
	private String opcion;
}
