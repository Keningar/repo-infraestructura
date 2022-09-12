package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class DatoClienteReqDTO {
	private String puerto;
	@SerializedName("ont_id")
	private String ontId;
	private String spid;
	@SerializedName("service_profile")
	private String serviceProfile;
	private String gemport;
	private String vlan;
	@SerializedName("traffic_table")
	private String trafficTable;
	@SerializedName("line_profile")
	private String lineProfile;
	@SerializedName("serie_ont")
	private String serieOnt;
	@SerializedName("mac_ont")
	private String macOnt;
	@SerializedName("tipo_negocio_actual")
	private String tipoNegocioActual;
	@SerializedName("id_servicio")
	private Integer idServicio;
	private String login;
	@SerializedName("proceso_det_id")
	private Integer procesoDetId;
	private String accion;
	@SerializedName("capacidad_up")
	private String capacidadUp;
	@SerializedName("capacidad_down")
	private String capacidadDown;
	@SerializedName("ip_fijas_activas")
	private Integer ipFijasActivas;
}
