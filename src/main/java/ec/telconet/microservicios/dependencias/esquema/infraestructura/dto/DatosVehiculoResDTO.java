package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;

/**
 * DTO Response DatosVehiculoResDTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 05/05/2020
 */
@Data
public class DatosVehiculoResDTO {
	private Long idElemento;
	private String placa;
	private String descripcionElemento;
	private String modelo;
	private String tipo;
	private String disco;
	private String motor;
	private String chasis;
	private String gps;
	private String imei;
	private String chip;
	private String cuadrilla;
	private String nombreCuadrilla;
	private String estado;
}
