package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraConstants;
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
	private String publishId;
	private String disco;
	private String motor;
	private String chasis;
	private String gps;
	private String imei;
	private String chip;
	private String cuadrilla;
	private String nombreCuadrilla;
	private String estado;
	private String marca;
	private Long oficinaId;
	private String responsableTablet;
	private Long responsableTabletPersonaId;
	private Long responsableDepartamentoId;
	private String responsableDepartamento;
	private String observacion;
	private String usrCreacion;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = InfraestructuraConstants.TIMEZONE_DATE)
	private Date feCreacion;
}
