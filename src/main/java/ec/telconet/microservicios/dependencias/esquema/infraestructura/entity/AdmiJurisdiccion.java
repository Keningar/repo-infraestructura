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
 * Entidad ADMI_JURISDICCION
 * 
 * @author Lizbeth Cruz <mailto:mlcruz@telconet.ec>
 * @version 1.0
 * @since 19/07/2021
 */
@Data
@Entity
@Table(name = "ADMI_JURISDICCION", schema = InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
public class AdmiJurisdiccion {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMI_JURISDICCION")
	@SequenceGenerator(sequenceName = "SEQ_ADMI_JURISDICCION", allocationSize = 1, initialValue = 1, name = "SEQ_ADMI_JURISDICCION")
	@Column(name = "ID_JURISDICCION")
	private Long idJurisdiccion;
	@Column(name = "OFICINA_ID")
	private Long oficinaId;
	@Column(name = "NOMBRE_JURISDICCION")
	private String nombreJurisdiccion;
	@Column(name = "DESCRIPCION_JURISDICCION")
	private String descripcionJurisdiccion;
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
	@Column(name = "CUPO")
	private Long cupo;
	@Column(name = "CUPO_MOBILE")
	private Long cupoMobile;
	
	public static final String idJurisdiccionValue = "idJurisdiccion";
}
