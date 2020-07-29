package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO Request para guardar la factibilidad CLOUD IAAS Alquiler de Servidor.
 *
 * @author Karen Rodríguez Véliz <mailto:kyrdoriguez@telconet.ec>
 * @version 1.0
 * @since 21/05/2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FactibilidadServidorReqDTO extends DatosPrincipalesSolucionReqDTO {
	private Long cantonId;
    private String empresaCod;
}
