package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * DTO Request para obtener la factibilidad
 * 
 * @author Antonio Ayala <mailto:afayala@telconet.ec>
 * @version 1.0
 * @since 21/07/2022
 */
@Data
public class DatosFactibilidadConnectivityReqDTO {
	private String idEmpresa;
	private String prefijoEmpresa;
	private String dependeDeEdificio;
	private BigDecimal latitud;
	private BigDecimal longitud;
	private String strLogin;
}
