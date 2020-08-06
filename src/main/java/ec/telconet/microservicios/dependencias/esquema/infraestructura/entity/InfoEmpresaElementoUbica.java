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
 * ENTIDAD EmpresaElementoUbica
 * 
 * @author Karen Rodríguez Véliz <mailto:kyrdoriguez@telconet.ec>
 * @version 1.0
 * @since 20/05/2020
 */
@Data
@Entity
@Table(name = "INFO_EMPRESA_ELEMENTO_UBICA", schema = InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
public class InfoEmpresaElementoUbica {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INFO_EMPRESA_ELEMENTO_UBI")
	@SequenceGenerator(sequenceName = "SEQ_INFO_EMPRESA_ELEMENTO_UBI", allocationSize = 1, initialValue = 1, name = "SEQ_INFO_EMPRESA_ELEMENTO_UBI")
	@Column(name = "ID_EMPRESA_ELEMENTO_UBICACION")
	private Long idEmpresaElementoUbicacion;
	
	@Column(name = "EMPRESA_COD")
	private String empresaCod;
	
	@Column(name = "ELEMENTO_ID", insertable = true, updatable = true)
	private Long elementoId;
	
	@Column(name = "UBICACION_ID", insertable = true, updatable = true)
	private Long ubicacionId;
	
	@Column(name = "USR_CREACION")
	private String usrCreacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = InfraestructuraConstants.TIMEZONE_DATE)
	@Column(name = "FE_CREACION")
	private Date feCreacion;
	
	@Column(name = "IP_CREACION")
	private String ipCreacion;
	
	public static final String idEmpresaElementoUbicacionValue = "idEmpresaElementoUbicacion";
}
