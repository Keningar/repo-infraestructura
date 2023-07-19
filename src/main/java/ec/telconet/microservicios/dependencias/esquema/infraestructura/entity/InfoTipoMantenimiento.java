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
 * Entidad INFO_ELEMENTO
 * 
 * @author José Castillo <mailto:jmcastillo@telconet.ec>
 * @version 1.0
 * @since 22/03/2020
 */
@Data
@Entity
@Table(name = "INFO_TIPO_MANTENIMIENTO", schema = InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
public class InfoTipoMantenimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INFO_TIPO_MANTENIMIENTO")
	@SequenceGenerator(sequenceName = "SEQ_INFO_TIPO_MANTENIMIENTO", allocationSize = 1, initialValue = 1, name = "SEQ_INFO_TIPO_MANTENIMIENTO")
	@Column(name = "ID_TIPO_MANTENIMIENTO")
	private Long idTipoMantenimiento;
	@Column(name = "DESCRIPCION_MANTENIMIENTO")
	private String descripcionMantenimiento;
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
	

	public static final String idTipoMantenimientoValue = "idTipoMantenimiento";
}
