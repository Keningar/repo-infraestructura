package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class OltReqDTO {
	private String opcion;
	private String empresa;
	private String usrCreacion;
	private String ipCreacion;
	private String comandoConfiguracion;
	private String ejecutaComando;
	@SerializedName("datos_elemento")
	private DatosElementoReqDTO datosElemento;
	@SerializedName("cambio_plan")
	private CambioPlanReqDTO cambioPlan;
	@SerializedName("datos_cliente")
	private List<DatoClienteReqDTO> datosCliente;
}
