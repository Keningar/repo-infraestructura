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
 * Entidad INFO_KARDEX
 * 
 * @author José Castillo <mailto:jmcastillo@telconet.ec>
 * @version 1.0
 * @since 03/05/2023
 */
@Data
@Entity
@Table(name = "INFO_KARDEX", schema = InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
public class InfoKardex {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_INFO_KARDEX")
	@SequenceGenerator(sequenceName = "SEQ_INFO_KARDEX", allocationSize = 1, initialValue = 1, name = "SEQ_INFO_KARDEX")
	@Column(name = "ID_KARDEX")
	private Long idKardex;
	@Column(name = "FILIAL")
	private String filial;
	@Column(name = "ELEMENTO_ID", insertable = true, updatable = true)
	private Long elementoId;
	@ManyToOne
	@JoinColumn(name = "ELEMENTO_ID", insertable = false, updatable = false)
	private InfoElemento elemento;
	@Column(name = "KM_VEHICULO")
	private Float kmVehiculo;
	@Column(name = "ESTADO")
	private String estado;
	@Column(name = "IDENTIFICADOR")
	private String identificador;
	@Column(name = "NUMERO_IDENTIFICADOR")
	private String numeroIdentificador;
	@Column(name = "IDENTIFICADOR_EXCEL")
	private String identificadorExcel;
	@Column(name = "PROVEEDOR")
	private String proveedor;
	@Column(name = "VALOR_TRABAJO")
	private Float valorTrabajo;
	@Column(name = "IVA_TRABAJO")
	private Float ivaTrabajo;
	@Column(name = "TOTAL_TRABAJO")
	private Float totalTrabajo;
	@Column(name = "TIPO_VEHICULO")
	private String tipoVehiculo;
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
	
	public static final String idKardexValue = "idKardex";
	public static final String elementoIdValue = "elementoId";
}
