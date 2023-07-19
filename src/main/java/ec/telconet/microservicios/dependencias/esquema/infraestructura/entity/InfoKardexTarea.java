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
 * Entidad INFO_KARDEX_TAREA
 * 
 * @author José Castillo <mailto:jmcastillo@telconet.ec>
 * @version 1.0
 * @since 08/05/2023
 */
@Data
@Entity
@Table(name = "INFO_KARDEX_TAREA", schema = InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
public class InfoKardexTarea {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_INFO_KARDEX_TAREA")
	@SequenceGenerator(sequenceName = "SEQ_INFO_KARDEX_TAREA", allocationSize = 1, initialValue = 1, name = "SEQ_INFO_KARDEX_TAREA")
	@Column(name = "ID_KARDEX_TAREA")
	private Long idKardexTarea;
	@Column(name = "TIPO_MANTENIMIENTO_ID", insertable = true, updatable = true)
	private Long tipoMantenimientoId;
	@ManyToOne
	@JoinColumn(name = "TIPO_MANTENIMIENTO_ID", insertable = false, updatable = false)
	private InfoTipoMantenimiento infoTipoMantenimiento;
	@Column(name = "NIVEL_TAREA", insertable = true, updatable = true)
	private Long nivelTarea;	
	@Column(name = "DESCRIPCION_TAREA")
	private String descripcionTarea;
	@Column(name = "KARDEX_TAREA_ID", insertable = true, updatable = true)
	private Long kardexTareaId;
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
	
	public static final String idKardexTareaValue = "idDetalleKardex";
	public static final String tipoMantenimientoIdValue = "tipoMantenimientoId";
}
