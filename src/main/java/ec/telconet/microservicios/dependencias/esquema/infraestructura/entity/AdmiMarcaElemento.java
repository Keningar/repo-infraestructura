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
 * Entidad ADMI_MARCA_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Data
@Entity
@Table(name = "ADMI_MARCA_ELEMENTO", schema = InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
public class AdmiMarcaElemento {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMI_MARCA_ELEMENTO")
	@SequenceGenerator(sequenceName = "SEQ_ADMI_MARCA_ELEMENTO", allocationSize = 1, initialValue = 1, name = "SEQ_ADMI_MARCA_ELEMENTO")
	@Column(name = "ID_MARCA_ELEMENTO")
	private Long idMarcaElemento;
	@Column(name = "NOMBRE_MARCA_ELEMENTO")
	private String nombreMarcaElemento;
	@Column(name = "DESCRIPCION_MARCA_ELEMENTO")
	private String descripcionMarcaElemento;
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
	
	public static final String idMarcaElementoValue = "idMarcaElemento";
}
