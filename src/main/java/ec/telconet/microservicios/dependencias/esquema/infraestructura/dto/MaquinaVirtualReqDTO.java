package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import java.util.List;

import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoDetalleElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoEmpresaElementoUbica;
import lombok.Data;

/**
 * DTO Request Máquina virtual
 * 
 * @author Karen Rodríguez Véliz <mailto:kyrdoriguez@telconet.ec>
 * @version 1.0
 * @since 19/05/2020
 */
@Data
public class MaquinaVirtualReqDTO {
	private Boolean habilitaCommit;
	private String 	usrCreacion;
	private String 	ipCreacion;
	private String 	estado;
	private InfoElemento elemento;
	private InfoEmpresaElementoUbica 	empresaElementoUbica;
	private List<InfoDetalleElemento> 	detalle;
	private List<ServicioRecursoDetDTO> detalleRecursos;
}
