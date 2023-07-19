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
	@Value("${db.paquete.procesos.masivos}")
	private String paqueteProcesosMasivos;
    @Value("${db.paquete.migracion.alta.densidad}")
    private String paqueteMigracionAltaDensidad;
    @Value("${db.proce.obtenerCabMigracion}")
    private String proceObtenerCabMigracion;
    @Value("${db.proce.validarCabMigracion}")
    private String proceValidarCabMigracion;
    @Value("${db.proce.agruparDetalles}")
    private String proceAgruparDetalles;
    @Value("${db.proce.procesarOlts}")
    private String proceProcesarOlts;
    @Value("${db.proce.procesarSplitters}")
    private String proceProcesarSplitters;
    @Value("${db.proce.procesarEnlaces}")
    private String proceProcesarEnlaces;
    @Value("${db.proce.procesarClientes}")
    private String proceProcesarClientes;
    @Value("${db.proce.procesarScopes}")
    private String proceProcesarScopes;
    @Value("${db.proce.reversarCabMigracion}")
    private String proceReversarCabMigracion;
	@Value("${db.proce.elementoPorTipo}")
	private String proceElementoPorTipo;
	@Value("${db.proce.elementoPorTipoPlaca}")
	private String proceElementoPorTipoPlaca;
	@Value("${db.proce.detalleElementoKardex}")
	private String proceDetalleElementoKardex;
	@Value("${db.proce.kardexTarea}")
	private String proceKardexTarea;
	@Value("${db.proce.obtenerPm}")
	private String proceObtenerPm;
	@Value("${db.proce.procesarMw}")
	private String proceProcesarMw;
	@Value("${db.proce.procesarTelcos}")
	private String proceProcesarTelcos;
	@Value("${db.proce.procesarCabecerasPm}")
	private String proceProcesarCabecerasPm;
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
	@Value("${db.proce.elementoPorDepartamentoParams}")
	private String proceElementoPorDepartamentoParams;
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
	@Value("${db.proce.asignarUbicacionElemento}")
	private String proceAsignarUbicacionElemento;
	@Value("${db.proce.modificarUbicacionElemento}")
	private String proceModificarUbicacionElemento;
	@Value("${db.proce.elementoPorCuadrillaParams}")
	private String proceElementoPorCuadrillaParams;
	@Value("${db.paquete.factibilidadConnectivity.consulta}")
	private String paqueteFactibilidadConnectivityConsulta;
	@Value("${db.proce.factibilidadConnectivity.obtieneInfoPreFactibilidad}")
	private String proceObtieneInfoPreFactibilidad;
	@Value("${db.proce.modelosElemMonitorizados}")
	private String proceModelosElemMonitorizados;
	@Value("${db.proce.kardexDetalle}")
	private String proceKardexDetalle;
	@Value("${db.proce.kardexTotal}")
	private String proceKardexTotal;
	@Value("${db.proce.factibilidadConnectivity.obtenerDatosFactibilidad}")
	private String proceObtenerDatosFactibilidad;
}
