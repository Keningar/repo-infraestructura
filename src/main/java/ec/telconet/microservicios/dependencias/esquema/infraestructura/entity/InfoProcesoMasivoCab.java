package ec.telconet.microservicios.dependencias.esquema.infraestructura.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraConstants;
import lombok.Data;

/**
 * Entidad INFO_PROCESO_MASIVO_CAB
 * 
 * @author Pedro Velez <mailto:psvelez@telconet.ec>
 * @version 1.0
 * @since 03/04/2022
 */
@Data
@Entity
@Table(name = "INFO_PROCESO_MASIVO_CAB", schema = InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
public class InfoProcesoMasivoCab {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INFO_PROCESO_MASIVO_CAB")
    @SequenceGenerator(sequenceName = "SEQ_INFO_PROCESO_MASIVO_CAB", allocationSize = 1, initialValue = 1, name = "SEQ_INFO_PROCESO_MASIVO_CAB")
    @Column(name="ID_PROCESO_MASIVO_CAB")
    private Long idProcesoMasivoCab;

    @Column(name="TIPO_PROCESO")
    private String tipoProceso;

    @Column(name="EMPRESA_ID")
    private Long empresaId;

    @Column(name="CANAL_PAGO_LINEA_ID")
    private Long canalPagoLineaId;

    @Column(name="CANTIDAD_PUNTOS")
    private Long cantidadPunto;

    @Column(name="CANTIDAD_SERVICIOS")
    private Long cantidadServicio;

    @Column(name="FACTURAS_RECURRENTES")
    private Long facturasRecurrentes;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = InfraestructuraConstants.TIMEZONE_DATE)
    @Column(name="FECHA_EMISION_FACTURA")
    private Date fechaEmisionFactura;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = InfraestructuraConstants.TIMEZONE_DATE)
    @Column(name="FECHA_CORTE_DESDE")
    private Date fechaCorteDesde;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = InfraestructuraConstants.TIMEZONE_DATE)
    @Column(name="FECHA_CORTE_HASTA")
    private Date fechaCorteHasta;

    @Column(name="VALOR_DEUDA")
    private Long valorDeuda;

    @Column(name="FORMA_PAGO_ID")
    private Long formaPagoId;

    @Column(name="IDS_BANCOS_TARJETAS")
    private String idsBancosTarjetas;

    @Column(name="IDS_OFICINAS")
    private String idsOficinas;

    @Column(name="ESTADO")
    private String estado;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = InfraestructuraConstants.TIMEZONE_DATE)
    @Column(name="FE_CREACION")
    private Date feCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = InfraestructuraConstants.TIMEZONE_DATE)
    @Column(name="FE_ULT_MOD")
    private Date feUltMod;

    @Column(name="USR_CREACION")
    private String usrCreacion;

    @Column(name="USR_ULT_MOD")
    private String usrUltMod;

    @Column(name="IP_CREACION")
    private String ipCreacion;

    @Column(name="PLAN_ID")
    private Long planId;

    @Column(name="PLAN_VALOR")
    private String planValor;

    @Column(name="PAGO_ID")
    private Long pagoId;

    @Column(name="PAGO_LINEA_ID")
    private Long pagoLineaId;

    @Column(name="RECAUDACION_ID")
    private Long recaudacionId;

    @Column(name="DEBITO_ID")
    private Long debitoId;

    @Column(name="ELEMENTO_ID")
    private Long elementoId;

    @Column(name="SOLICITUD_ID")
    private Long solicitudId;

    @Column(name="CASO_ID")
    private Long casoId;
    
}
