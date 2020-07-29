package ec.telconet.microservicios.dependencias.esquema.infraestructura.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

/**
 * Properties del modulo Infraestructura
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Data
@Configuration
@PropertySource("classpath:infraestructura.properties")
public class InfraestructuraProperties {
	@Value("${db.paquete.elemento.consulta}")
	private String paqueteElementoConsulta;
	@Value("${db.paquete.elemento.transaccion}")
	private String paqueteElementoTransaccion;
	@Value("${db.proce.elementoPorTipo}")
	private String proceElementoPorTipo;
	@Value("${db.proce.elementoPorRegionParams}")
	private String proceElementoPorRegionParams;
	@Value("${db.proce.elementoPorProvinciaParams}")
	private String proceElementoPorProvinciaParams;
	@Value("${db.proce.elementoPorParroquiaParams}")
	private String proceElementoPorParroquiaParams;
	@Value("${db.proce.elementoPorCantonParams}")
	private String proceElementoPorCantonParams;
	@Value("${db.proce.elementoPorFilialParams}")
	private String proceElementoPorFilialParams;
	@Value("${db.proce.elementoPorEsMonitorizado}")
	private String proceElementoPorEsMonitorizado;
	@Value("${db.proce.detalleElementoPorElemento}")
	private String proceDetalleElementoPorElemento;
	@Value("${db.proce.historialElementoPorElemento}")
	private String proceHistorialElementoPorElemento;
	@Value("${db.proce.historialElementoPorFecha}")
	private String proceHistorialElementoPorFecha;
	@Value("${db.proce.datosVehiculo}")
	private String proceDatosVehiculo;
	@Value("${db.proce.elementoPorGrupo}")
	private String proceElementoPorGrupo;
	@Value("${db.paquete.soluciones.transaccion}")
	private String nombrePaqueteSolucionesTrans;
	@Value("${db.proce.soluciones.crearMaquinaVirtual}")
	private String proceCrearMaquinaVirtual;
	@Value("${db.proce.soluciones.crearFactibilidadServidor}")
	private String proceCrearFactibilidadServidor;
	@Value("${db.proce.soluciones.crearFactibPoolServidor}")
	private String proceCrearFactibPoolServidor;
	@Value("${db.proce.soluciones.crearFactibilidadMV}")
	private String proceCrearFactibilidadMV; 
}
