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
public class ListaKardexReqDTO {
	//
	private String estado;
	private String inicio;
	private String total;
	private String limit;
	private String placa;
	private String disco;
	private String modelo;
	private String anio;
	private String km;
	private String identificador;
	private String numerIdentificador;
	private String fecha;
	private String anioFecha;
	private String mesFecha;
	private String proveedor;
	private String tipoTrabajo;
	private String cantidad;
	private String descripcionTrabajo;
	private String codigo;
	private String valor;
	private String iva;
	private String totalDetalle;
	private String tipoVehiculo;
	private String region;
}
