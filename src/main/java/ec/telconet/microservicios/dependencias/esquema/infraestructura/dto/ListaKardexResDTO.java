/**
 * 
 */
package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import lombok.Data;

/**
 * @author jmcastillo
 *
 */

@Data
public class ListaKardexResDTO {
	private Long idKardex;
	private Long elementoId;
	private String region;
	private String disco;
	private String placa;
	private String modelo;
	private String anio;
	private String kmVehiculo;
	private String identificador;
	private String numeroIdentificador;
	private String fechaRegistro;
	private String proveedor;
	private String tipoTrabajo;
	private String cantidad;
	private String descripcionTarea;
	private String codigoTarea;
	private String valorTrabajo;
	private String ivaTrabajo;
	private String totalTrabajo;
	private String idTipoMantenimiento;
	private String despachado;
	private String devuelto;
	private String anioRegistro;
	private String mesRegistro;
}
