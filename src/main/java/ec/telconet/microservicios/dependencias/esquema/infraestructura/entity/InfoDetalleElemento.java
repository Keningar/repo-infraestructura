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
 * Entidad INFO_DETALLE_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Data
@Entity
@Table(name = "INFO_DETALLE_ELEMENTO", schema = InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
public class InfoDetalleElemento {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INFO_DETALLE_ELEMENTO")
	@SequenceGenerator(sequenceName = "SEQ_INFO_DETALLE_ELEMENTO", allocationSize = 1, initialValue = 1, name = "SEQ_INFO_DETALLE_ELEMENTO")
	@Column(name = "ID_DETALLE_ELEMENTO")
	private Long idDetalleElemento;
	@Column(name = "ELEMENTO_ID", insertable = true, updatable = true)
	private Long elementoId;
	@ManyToOne
	@JoinColumn(name = "ELEMENTO_ID", insertable = false, updatable = false)
	private InfoElemento elemento;
	@Column(name = "DETALLE_NOMBRE")
	private String detalleNombre;
	@Column(name = "DETALLE_VALOR")
	private String detalleValor;
	@Column(name = "DETALLE_DESCRIPCION")
	private String detalleDescripcion;
	@Column(name = "USR_CREACION")
	private String usrCreacion;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = InfraestructuraConstants.TIMEZONE_DATE)
	@Column(name = "FE_CREACION")
	private Date feCreacion;
	@Column(name = "IP_CREACION")
	private String ipCreacion;
	@Column(name = "REF_DETALLE_ELEMENTO_ID")
	private String refDetalleElementoId;
	@Column(name = "ESTADO")
	private String estado;
	
	public static final String idDetalleElementoValue = "idDetalleElemento";
	public static final String elementoIdValue = "elementoId";
}
