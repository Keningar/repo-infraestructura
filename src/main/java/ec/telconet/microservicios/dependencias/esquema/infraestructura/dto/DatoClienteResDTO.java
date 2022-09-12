package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;

@Data
public class DatoClienteResDTO {
	private String accion;
	private String serieOnt;
	private String mensaje;
	private Integer idServicio;
	private String login;
	private String status;
	private Integer idProcesoDet;
}
