package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class CambioPlanReqDTO {
    @SerializedName("gem_port_nuevo")
	private String gemPortNuevo;
    @SerializedName("traffic_table_nuevo")
	private String trafficTableNuevo;
    @SerializedName("line_profile_nuevo")
	private String lineProfileNuevo;
    @SerializedName("tipo_negocio_nuevo")
	private String tipoNegocioNuevo;
    @SerializedName("vlan_nuevo")
	private String vlanNuevo;
}
