package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import java.util.ArrayList;
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
public class ElementosOltDTO {
	private List<OltDTO> olts = new ArrayList<>();
	private Integer cantidad;
}
