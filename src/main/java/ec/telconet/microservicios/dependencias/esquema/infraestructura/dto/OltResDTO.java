package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class OltResDTO {
	private Integer idMasivo;
	private String mensaje;
	private String status;
	@SerializedName("datos_cliente")
	private List<DatoClienteResDTO> datosCliente;
}
