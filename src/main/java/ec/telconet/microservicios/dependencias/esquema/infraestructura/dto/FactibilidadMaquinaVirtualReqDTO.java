package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import java.util.List;

import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoDetalleElemento;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO Request para guardar la factibilidad de maquina virtual de una solución DC.
 *
 * @author Karen Rodríguez Véliz <mailto:kyrdoriguez@telconet.ec>
 * @version 1.0
 * @since 21/05/2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FactibilidadMaquinaVirtualReqDTO extends DatosPrincipalesSolucionReqDTO {
    private Long elementoId;
    private List<InfoDetalleElemento> 	factibilidad;
    private List<ServicioRecursoDetDTO> datastore;
}
