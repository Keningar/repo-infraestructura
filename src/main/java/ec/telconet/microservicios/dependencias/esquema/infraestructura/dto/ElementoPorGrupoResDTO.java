package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;

/**
 * DTO Response Elemento por grupo
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 23/06/2020
 */
@Data
public class ElementoPorGrupoResDTO {
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
	private Long oficinaId;
	private String estado;
}
