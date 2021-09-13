package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import java.util.List;

import lombok.Data;

/**
 * DTO Response para obtener la prefactibilidad
 * 
 * @author Lizbeth Cruz <mailto:mlcruz@telconet.ec>
 * @version 1.0
 * @since 06/07/2021
 */
@Data
public class PreFactibilidadConnectivityResDTO {
    private Float distanciaMaxCobertura;
    private Float distanciaMaxFactibilidad;
	private String existeCobertura;
	private String existeFactibilidad;
	private List<InfoCajaConector> infoCajasConectores;

	@Data
	public static class InfoCajaConector {
	    private Long idCaja;
	    private String nombreCaja;
	    private String nombreConector;
	    private Float distancia;
	    private Long numPuertosDisponibles;
	}
}
