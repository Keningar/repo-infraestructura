package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import java.util.List;

import lombok.Data;

/**
 * Clase DTO Metricas
 *
 * @author Jesús Bozada <mailto:jbozada@telconet.ec>
 * @version 1.0
 * @since 15/08/2022
 */
@Data
public class OltDTO {
	private List<Integer> idsPmCab;
    private String nombreOlt;
    private String tipoProceso;
    private String prefijoEmpresa;
    private String estado;
    private String observacion;
    private OltResDTO response;
    private OltReqDTO request;
    private String infoDetPm;
}
