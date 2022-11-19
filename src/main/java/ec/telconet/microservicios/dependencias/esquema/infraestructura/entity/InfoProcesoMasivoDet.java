package ec.telconet.microservicios.dependencias.esquema.infraestructura.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraConstants;
import lombok.Data;

/**
 * Entidad INFO_PROCESO_MASIVO_DET
 * 
 * @author Pedro Velez <mailto:psvelez@telconet.ec>
 * @version 1.0
 * @since 03/04/2022
 */
@Data
@Entity
@Table(name = "INFO_PROCESO_MASIVO_DET", schema = InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
public class InfoProcesoMasivoDet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SEQ_INFO_PROCESO_MASIVO_DET")
    @SequenceGenerator(sequenceName = "SEQ_INFO_PROCESO_MASIVO_DET", allocationSize = 1, initialValue = 1, name = "SEQ_INFO_PROCESO_MASIVO_DET")
    @Column(name="ID_PROCESO_MASIVO_DET")
    private Long idProcesoMasivoDet;

   @Column(name="PROCESO_MASIVO_CAB_ID")
    private Long procesoMasivoCabId;

    @Column(name="PUNTO_ID")
    private Long puntoId;

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

    @Column(name="SERVICIO_ID")
    private Long servicioId;

    @Column(name="OBSERVACION")
    private String observacion;

    @Column(name="SOLICITUD_ID")
    private Long solicitudId;

    @Column(name="CANTIDAD_INTENTOS")
    private Long cantidadIntentos;

    @Column(name="LOGIN")
    private String login;

    @Column(name="SERIE_FISICA")
    private String serieFisica;

    @Column(name="PERSONA_EMPRESA_ROL_ID")
    private Long personaEmpresaRolId;

    @Column(name="PAGO_ID")
    private Long pagoId;

    
}
