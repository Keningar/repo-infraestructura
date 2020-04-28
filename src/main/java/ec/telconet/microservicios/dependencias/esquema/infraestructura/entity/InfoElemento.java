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
 * Entidad INFO_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Data
@Entity
@Table(name = "INFO_ELEMENTO", schema = InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
public class InfoElemento {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INFO_ELEMENTO")
	@SequenceGenerator(sequenceName = "SEQ_INFO_ELEMENTO", allocationSize = 1, initialValue = 1, name = "SEQ_INFO_ELEMENTO")
	@Column(name = "ID_ELEMENTO")
	private Long idElemento;
	@Column(name = "MODELO_ELEMENTO_ID", insertable = true, updatable = true)
	private Long modeloElementoId;
	@ManyToOne
	@JoinColumn(name = "MODELO_ELEMENTO_ID", insertable = false, updatable = false)
	private AdmiModeloElemento modeloElemento;
	@Column(name = "NOMBRE_ELEMENTO")
	private String nombreElemento;
	@Column(name = "DESCRIPCION_ELEMENTO")
	private String descripcionElemento;
	@Column(name = "SERIE_FISICA")
	private String serieFisica;
	@Column(name = "SERIE_LOGICA")
	private String serieLogica;
	@Column(name = "VERSION_OS")
	private String versionOs;
	@Column(name = "FUNCION")
	private String funcion;
	@Column(name = "CLAVE_CONFIGURACION")
	private String claveConfiguracion;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "FE_FABRICACION")
	private Date feFabricacion;
	@Column(name = "ACCESO_PERMANENTE")
	private String accesoPermanente;
	@Column(name = "OBSERVACION")
	private String observacion;
	@Column(name = "USR_RESPONSABLE")
	private String usrResponsable;
	@Column(name = "USR_CREACION")
	private String usrCreacion;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = InfraestructuraConstants.TIMEZONE_DATE)
	@Column(name = "FE_CREACION")
	private Date feCreacion;
	@Column(name = "IP_CREACION")
	private String ipCreacion;
	@Column(name = "REVISION")
	private String revision;
	@Column(name = "ESTADO")
	private String estado;
	@Column(name = "REF_ELEMENTO_ID")
	private Long refElementoId;
	
	public static final String idElementoValue = "idElemento";
	public static final String modeloElementoIdValue = "modeloElementoId";
}
