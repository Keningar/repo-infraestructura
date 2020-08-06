package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;

/**
 * DTO Request Ubicacion Elemento
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 04/08/2020
 */
@Data
public class UbicacionElementoReqDTO {
	private String empresaCod;
	private Long elementoId;
	private Long oficinaId;
	private String usrCreacion;
	private String ipCreacion;
	private Long parroquiaId;
	private String direccionUbicacion;
	private Long longitudUbicacion;
	private Long latitudUbicacion;
	private Long alturaSNM;
}
