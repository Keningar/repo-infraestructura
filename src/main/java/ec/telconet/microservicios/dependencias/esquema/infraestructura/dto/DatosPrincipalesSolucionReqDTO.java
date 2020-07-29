package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;

/**
 * DTO Request solución DC.
 *
 * @author Karen Rodríguez Véliz <mailto:kyrdoriguez@telconet.ec>
 * @version 1.0
 * @since 21/05/2020
 */
@Data
public class DatosPrincipalesSolucionReqDTO {
    private Boolean habilitaCommit;
    private String usrCreacion;
    private String ipCreacion;
    private String estado;
    private Long servicioId;
}
