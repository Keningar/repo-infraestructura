package ec.telconet.microservicios.dependencias.esquema.infraestructura.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
 * Entidad INFO_HISTORIAL_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Data
@Entity
@Table(name = "INFO_HISTORIAL_ELEMENTO", schema = InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
public class InfoHistorialElemento {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INFO_HISTORIAL_ELEMENTO")
	@SequenceGenerator(sequenceName = "SEQ_INFO_HISTORIAL_ELEMENTO", allocationSize = 1, initialValue = 1, name = "SEQ_INFO_HISTORIAL_ELEMENTO")
	@Column(name = "ID_HISTORIAL")
	private Long idHistorial;
	@Column(name = "ELEMENTO_ID", insertable = true, updatable = true)
	private Long elementoId;
	@ManyToOne
	@JoinColumn(name = "ELEMENTO_ID", insertable = false, updatable = false)
	private InfoElemento elemento;
	@Column(name = "ESTADO_ELEMENTO")
	private String estadoElemento;
	@Column(name = "CAPACIDAD")
	private Double capacidad;
	@Column(name = "OBSERVACION")
	private String observacion;
	@Column(name = "USR_CREACION")
	private String usrCreacion;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = InfraestructuraConstants.TIMEZONE_DATE)
	@Column(name = "FE_CREACION")
	private Date feCreacion;
	@Column(name = "IP_CREACION")
	private String ipCreacion;
	
	public static final String idHistorialValue = "idHistorial";
	public static final String elementoIdValue = "elementoId";
}
