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
 * Entidad ADMI_TIPO_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Data
@Entity
@Table(name = "ADMI_TIPO_ELEMENTO", schema = InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
public class AdmiTipoElemento {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMI_TIPO_ELEMENTO")
	@SequenceGenerator(sequenceName = "SEQ_ADMI_TIPO_ELEMENTO", allocationSize = 1, initialValue = 1, name = "SEQ_ADMI_TIPO_ELEMENTO")
	@Column(name = "ID_TIPO_ELEMENTO")
	private Long idTipoElemento;
	@Column(name = "NOMBRE_TIPO_ELEMENTO")
	private String nombreTipoElemento;
	@Column(name = "DESCRIPCION_TIPO_ELEMENTO")
	private String descripcionTipoElemento;
	@Column(name = "CLASE_TIPO_ELEMENTO")
	private String claseTipoElemento;
	@Column(name = "ESTADO")
	private String estado;
	@Column(name = "USR_CREACION")
	private String usrCreacion;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = InfraestructuraConstants.TIMEZONE_DATE)
	@Column(name = "FE_CREACION")
	private Date feCreacion;
	@Column(name = "USR_ULT_MOD")
	private String usrUltMod;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = InfraestructuraConstants.TIMEZONE_DATE)
	@Column(name = "FE_ULT_MOD")
	private Date feUltMod;
	@Column(name = "ES_DE")
	private String esDe;
	
	public static final String idTipoElementoValue = "idTipoElemento";
}
