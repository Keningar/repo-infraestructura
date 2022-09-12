package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class DatosElementoReqDTO {
    @SerializedName("nombre_elemento")
	private String nombreElemento;
    @SerializedName("ip_elemento")
	private String ipElemento;
}
