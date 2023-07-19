package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;

/**
 * @author jmcastillo
 *
 */

@Data
public class TotalRegistrosKardexResDTo {
	private Long total;
	private Float subtotal;
	private Float ivatotal;
}
