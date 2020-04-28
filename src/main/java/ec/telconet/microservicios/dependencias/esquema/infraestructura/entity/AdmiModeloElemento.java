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
 * Entidad ADMI_MODELO_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Data
@Entity
@Table(name = "ADMI_MODELO_ELEMENTO", schema = InfraestructuraConstants.SCHEMA_INFRAESTRUCTURA)
public class AdmiModeloElemento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ADMI_MODELO_ELEMENTO")
	@SequenceGenerator(sequenceName = "SEQ_ADMI_MODELO_ELEMENTO", allocationSize = 1, initialValue = 1, name = "SEQ_ADMI_MODELO_ELEMENTO")
	@Column(name = "ID_MODELO_ELEMENTO")
	private Long idModeloElemento;
	@Column(name = "MARCA_ELEMENTO_ID", insertable = true, updatable = true)
	private Long marcaElementoId;
	@ManyToOne
	@JoinColumn(name = "MARCA_ELEMENTO_ID", insertable = false, updatable = false)
	private AdmiMarcaElemento marcaElemento;
	@Column(name = "TIPO_ELEMENTO_ID", insertable = true, updatable = true)
	private Long tipoElementoId;
	@ManyToOne
	@JoinColumn(name = "TIPO_ELEMENTO_ID", insertable = false, updatable = false)
	private AdmiTipoElemento tipoElemento;
	@Column(name = "NOMBRE_MODELO_ELEMENTO")
	private String nombreModeloElemento;
	@Column(name = "DESCRIPCION_MODELO_ELEMENTO")
	private String descripcionModeloElemento;
	@Column(name = "MTTR")
	private Float mttr;
	@Column(name = "UNIDAD_MEDIDA_MTTR")
	private String unidadMedidaMttr;
	@Column(name = "MTBF")
	private Float mtbf;
	@Column(name = "UNIDAD_MEDIDA_MTBF")
	private String unidadMedidaMtbf;
	@Column(name = "ANCHO_MODELO")
	private Float anchoModelo;
	@Column(name = "UNIDAD_MEDIDA_ANCHO")
	private String unidadMedidaAncho;
	@Column(name = "LARGO_MODELO")
	private Float largoModelo;
	@Column(name = "UNIDAD_MEDIDA_LARGO")
	private String unidadMedidaLargo;
	@Column(name = "ALTO_MODELO")
	private Float altoModelo;
	@Column(name = "UNIDAD_MEDIDA_ALTO")
	private String unidadMedidaAlto;
	@Column(name = "PESO_MODELO")
	private Float pesoModelo;
	@Column(name = "UNIDAD_MEDIDA_PESO")
	private String unidadMedidaPeso;
	@Column(name = "U_RACK")
	private Float uRack;
	@Column(name = "CAPACIDAD_ENTRADA")
	private Double capacidadEntrada;
	@Column(name = "UNIDAD_MEDIDA_ENTRADA")
	private String unidadMedidaEntrada;
	@Column(name = "CAPACIDAD_SALIDA")
	private Double capacidadSalida;
	@Column(name = "UNIDAD_MEDIDA_SALIDA")
	private String unidadMedidaSalida;
	@Column(name = "CAPACIDAD_VA_FABRICA")
	private Float capacidadVaFabrica;
	@Column(name = "UNIDAD_VA_FABRICA")
	private String unidadVaFabrica;
	@Column(name = "CAPACIDAD_VA_PROMEDIO")
	private Float capacidadVaPromedio;
	@Column(name = "UNIDAD_VA_PROMEDIO")
	private String unidadVaPromedio;
	@Column(name = "PRECIO_PROMEDIO")
	private Float precioPromedio;
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
	@Column(name = "REQ_APROVISIONAMIENTO")
	private String reqAprovisionamiento;
	
	public static final String idModeloElementoValue = "idModeloElemento";
	public static final String marcaElementoIdValue = "marcaElementoId";
	public static final String tipoElementoIdValue = "tipoElementoId";
}
