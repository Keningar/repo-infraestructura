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
 * Entidad INFO_DETALLE_KARDEX
 * 
 * @author José Castillo <mailto:jmcastillo@telconet.ec>
 * @version 1.0
 * @since 23/05/2023
 */
@Data
@Entity
@Table(name = "INFO_DETALLE_KARDEX", schema = InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
public class InfoDetalleKardex {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_INFO_DETALLE_KARDEX")
	@SequenceGenerator(sequenceName = "SEQ_INFO_DETALLE_KARDEX", allocationSize = 1, initialValue = 1, name = "SEQ_INFO_DETALLE_KARDEX")
	@Column(name = "ID_DETALLE_KARDEX")
	private Long idDetalleKardex;
	@Column(name = "KARDEX_ID", insertable = true, updatable = true)
	private Long kardexId;
	@ManyToOne
	@JoinColumn(name = "KARDEX_ID", insertable = false, updatable = false)
	private InfoKardex infoKardex;
	@Column(name = "ESTADO")
	private String estado;
	@Column(name = "TIPO_TRABAJO")
	private String tipoTrabajo;
	@Column(name = "CANTIDAD")
	private String cantidad;
	@Column(name = "VALOR_TRABAJO")
	private Float valorTrabajo;
	@Column(name = "IVA_TRABAJO")
	private Float ivaTrabajo;
	@Column(name = "TOTAL_TRABAJO")
	private Float totalTrabajo;
	@Column(name = "DESPACHADO")
	private Float despachado;
	@Column(name = "DEVUELTO")
	private Float devuelto;
	@Column(name = "KARDEX_TAREA_ID", insertable = true, updatable = true)
	private Long kardexTareaId;	
	@Column(name = "OBSERVACION_TAREA")
	private String observacionTarea;
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

	public static final String idDetalleKardexValue = "idDetalleKardex";
	public static final String tipoMantenimientoIdValue = "tipoMantenimientoId";
}
