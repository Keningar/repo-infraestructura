package ec.telconet.microservicios.dependencias.esquema.infraestructura.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraConstants;
import lombok.Data;


/**
 * Entidad INFO_IP
 * 
 * @author David De La Cruz <mailto:ddelacruz@telconet.ec>
 * @version 1.0
 * @since 11/01/2022
 */
@Data
@Entity
@Table(name = "INFO_IP", schema = InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
public class InfoIp {
    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INFO_IP")
	@SequenceGenerator(sequenceName = "SEQ_INFO_IP", allocationSize = 1, initialValue = 1, name = "SEQ_INFO_IP")
	@Column(name = "ID_IP")
	private Long idIp;
	@Column(name = "ELEMENTO_ID")
	private Long elementoId;
    @Column(name = "IP")
    private String ip;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "SUBRED_ID")
    private Long subredId;
    @Column(name = "MASCARA")
    private String mascara;
    @Column(name = "GATEWAY")
    private String gateway;
    @Column(name = "TIPO_IP")
    private String tipoIp;
    @Column(name = "VERSION_IP")
    private String versionIp;
    @Column(name = "SERVICIO_ID")
    private Long servicioId;
    @Column(name = "INTERFACE_ELEMENTO_ID")
    private Long interfaceElementoId;
    @Column(name = "REF_IP_ID")
    private Long refIpId;
    @Column(name = "USR_CREACION")
	private String usrCreacion;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = InfraestructuraConstants.TIMEZONE_DATE)
	@Column(name = "FE_CREACION")
	private Date feCreacion;
	@Column(name = "IP_CREACION")
	private String ipCreacion;

    public static final String ID_IP = "idIp";
}
