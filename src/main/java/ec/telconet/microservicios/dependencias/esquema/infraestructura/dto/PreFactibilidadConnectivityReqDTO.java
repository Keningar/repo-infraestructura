package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * DTO Request para obtener la prefactibilidad
 * 
 * @author Lizbeth Cruz <mailto:mlcruz@telconet.ec>
 * @version 1.0
 * @since 06/07/2021
 */
@Data
public class PreFactibilidadConnectivityReqDTO {
	private String idEmpresa;
	private String prefijoEmpresa;
	private String dependeDeEdificio;
	private BigDecimal latitud;
	private BigDecimal longitud;
}
