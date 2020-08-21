package ec.telconet.microservicios.dependencias.esquema.infraestructura.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import ec.telconet.microservicio.dependencia.util.cons.CoreUtilConstants;
import ec.telconet.microservicio.dependencia.util.dto.PageDTO;
import ec.telconet.microservicio.dependencia.util.enumerado.StatusHandler;
import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicio.dependencia.util.general.Formato;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.DatosVehiculoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.DetalleElementoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorCantonParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorCuadrillaParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorDepartamentoParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorFilialParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorGrupoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorParroquiaParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorProvinciaParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorRegionParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorTipoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.HistorialElementoPorFechaReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.HistorialElementoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.UbicacionElementoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.AdmiMarcaElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.AdmiModeloElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.AdmiTipoElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoDetalleElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoHistorialElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.AdmiMarcaElementoRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.AdmiModeloElementoRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.AdmiTipoElementoRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.InfoDetalleElementoRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.InfoElementoRepository;

/**
 * Clase donde se encuentran las validaciones del modulo infraestructura
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Component
public class InfraestructuraValidators {
	@Autowired
	AdmiTipoElementoRepository admiTipoElementoRepo;
	
	@Autowired
	AdmiModeloElementoRepository admiModeloElementoRepo;
	
	@Autowired
	AdmiMarcaElementoRepository admiMarcaElementoRepo;
	
	@Autowired
	InfoElementoRepository infoElementoRepo;
	
	@Autowired
	InfoDetalleElementoRepository infoDetalleElementoRepo;
	
	public void validarGuardarTipoElemento(AdmiTipoElemento request) throws GenericException {
		if (request.getNombreTipoElemento() == null) {
			throw new GenericException("El valor nombreTipoElemento es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (admiTipoElementoRepo.findByNombreTipoElemento(request.getNombreTipoElemento()).size() > 0) {
			throw new GenericException("El tipo de elemento " + request.getNombreTipoElemento() + " ya existe", CoreUtilConstants.EXISTING_VALUES);
		}
		if (request.getClaseTipoElemento() == null) {
			throw new GenericException("El valor claseTipoElemento es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getUsrCreacion() == null) {
			throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public AdmiTipoElemento validarActualizarTipoElemento(AdmiTipoElemento request) throws GenericException {
		if (request.getIdTipoElemento() == null) {
			throw new GenericException("El valor idTipoElemento es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (!admiTipoElementoRepo.existsById(request.getIdTipoElemento())) {
			throw new GenericException("El id del tipo de elemento " + request.getIdTipoElemento() + " no existe", CoreUtilConstants.EXISTING_VALUES);
		}
		if (request.getUsrUltMod() == null) {
			throw new GenericException("El valor usrUltMod es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		Optional<AdmiTipoElemento> optionalTipoElemento = admiTipoElementoRepo.findById(request.getIdTipoElemento());
		AdmiTipoElemento response = optionalTipoElemento.orElse(request);
		response.setNombreTipoElemento(request.getNombreTipoElemento() != null ? request.getNombreTipoElemento() : response.getNombreTipoElemento());
		response.setDescripcionTipoElemento(
				request.getDescripcionTipoElemento() != null ? request.getDescripcionTipoElemento() : response.getDescripcionTipoElemento());
		response.setClaseTipoElemento(request.getClaseTipoElemento() != null ? request.getClaseTipoElemento() : response.getClaseTipoElemento());
		response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
		response.setUsrUltMod(request.getUsrUltMod() != null ? request.getUsrUltMod() : response.getUsrUltMod());
		response.setEsDe(request.getEsDe() != null ? request.getEsDe() : response.getEsDe());
		return response;
	}
	
	public void validarPagina(PageDTO<?> request) throws GenericException {
		if (request.getPage() == null) {
			throw new GenericException("El valor page es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getPage() < 0) {
			throw new GenericException("El valor page debe ser positivo o comenzar desde 0", CoreUtilConstants.INFORMATIVE_VALUES);
		}
		if (request.getSize() == null) {
			throw new GenericException("El valor size es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getSize() < 0) {
			throw new GenericException("El valor size debe ser positivo o comenzar desde 0", CoreUtilConstants.INFORMATIVE_VALUES);
		}
		if (request.getTabla() == null) {
			throw new GenericException("El valor tabla es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getOrder() != null && !(request.getOrder().equals("ASC") || request.getOrder().equals("DESC"))) {
			throw new GenericException("El valor order debe ser ASC o DESC", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarGuardarModeloElemento(AdmiModeloElemento request) throws GenericException {
		if (admiModeloElementoRepo.findByNombreModeloElemento(request.getNombreModeloElemento()).size() > 0) {
			throw new GenericException("El modelo elemento " + request.getNombreModeloElemento() + " ya existe", CoreUtilConstants.EXISTING_VALUES);
		}
		if (request.getTipoElementoId() == null) {
			throw new GenericException("El valor tipoElementoId es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (!admiTipoElementoRepo.existsById(request.getTipoElementoId())) {
			throw new GenericException("El id del tipo elemento " + request.getTipoElementoId() + " no existe", CoreUtilConstants.EXISTING_VALUES);
		}
		if (request.getMarcaElementoId() == null) {
			throw new GenericException("El valor marcaElementoId es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (!admiMarcaElementoRepo.existsById(request.getMarcaElementoId())) {
			throw new GenericException("El id de la marca elemento" + request.getMarcaElementoId() + " no existe", CoreUtilConstants.EXISTING_VALUES);
		}
		if (request.getUsrCreacion() == null) {
			throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getUsrUltMod() == null) {
			throw new GenericException("El valor usrUltMod es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarGuardarElemento(InfoElemento request) throws GenericException {
		if (request.getModeloElementoId() == null) {
			throw new GenericException("El valor modeloElementoId es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (!admiModeloElementoRepo.existsById(request.getModeloElementoId())) {
			throw new GenericException("El id del modelo elemento " + request.getModeloElementoId() + " no existe",
					CoreUtilConstants.EXISTING_VALUES);
		}
		if (request.getNombreElemento() == null) {
			throw new GenericException("El valor nombreElemento es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getDescripcionElemento() == null) {
			throw new GenericException("El valor descripcionElemento es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getIpCreacion() == null) {
			throw new GenericException("El valor ipCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getUsrCreacion() == null) {
			throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarGuardarHistorialElemento(InfoHistorialElemento request) throws GenericException {
		if (request.getElementoId() == null) {
			throw new GenericException("El valor elementoId es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (!infoElementoRepo.existsById(request.getElementoId())) {
			throw new GenericException("El id del elemento " + request.getElementoId() + " no existe", CoreUtilConstants.EXISTING_VALUES);
		}
		if (request.getEstadoElemento() == null) {
			throw new GenericException("El valor estadoElemento es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getIpCreacion() == null) {
			throw new GenericException("El valor ipCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getUsrCreacion() == null) {
			throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public InfoElemento validarActualizarElemento(InfoElemento request) throws GenericException {
		if (request.getIdElemento() == null) {
			throw new GenericException("El valor idElemento es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (!infoElementoRepo.existsById(request.getIdElemento())) {
			throw new GenericException("El id del elemento " + request.getIdElemento() + " no existe", CoreUtilConstants.EXISTING_VALUES);
		}
		if (request.getModeloElementoId() != null && !admiModeloElementoRepo.existsById(request.getModeloElementoId())) {
			throw new GenericException("El id del modelo elemento " + request.getModeloElementoId() + " no existe",
					CoreUtilConstants.EXISTING_VALUES);
		}
		Optional<InfoElemento> optionalElemento = infoElementoRepo.findById(request.getIdElemento());
		InfoElemento response = optionalElemento.orElse(request);
		response.setModeloElementoId(request.getModeloElementoId() != null ? request.getModeloElementoId() : response.getModeloElementoId());
		response.setNombreElemento(request.getNombreElemento() != null ? request.getNombreElemento() : response.getNombreElemento());
		response.setDescripcionElemento(
				request.getDescripcionElemento() != null ? request.getDescripcionElemento() : response.getDescripcionElemento());
		response.setSerieFisica(request.getSerieFisica() != null ? request.getSerieFisica() : response.getSerieFisica());
		response.setSerieLogica(request.getSerieLogica() != null ? request.getSerieLogica() : response.getSerieLogica());
		response.setVersionOs(request.getVersionOs() != null ? request.getVersionOs() : response.getVersionOs());
		response.setFuncion(request.getFuncion() != null ? request.getFuncion() : response.getFuncion());
		response.setClaveConfiguracion(request.getClaveConfiguracion() != null ? request.getClaveConfiguracion() : response.getClaveConfiguracion());
		response.setFeFabricacion(request.getFeFabricacion() != null ? request.getFeFabricacion() : response.getFeFabricacion());
		response.setAccesoPermanente(request.getAccesoPermanente() != null ? request.getAccesoPermanente() : response.getAccesoPermanente());
		response.setObservacion(request.getObservacion() != null ? request.getObservacion() : response.getObservacion());
		response.setUsrResponsable(request.getUsrResponsable() != null ? request.getUsrResponsable() : response.getUsrResponsable());
		response.setRevision(request.getRevision() != null ? request.getRevision() : response.getRevision());
		response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
		response.setRefElementoId(request.getRefElementoId() != null ? request.getRefElementoId() : response.getRefElementoId());
		return response;
	}
	
	public void validarElementoPorTipo(ElementoPorTipoReqDTO request) throws GenericException {
		if (request.getNombreTipo() == null && request.getTipoId() == null) {
			throw new GenericException("El valor tipoId o nombreTipo es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarGuardarDetalleElemento(InfoDetalleElemento request) throws GenericException {
		if (request.getElementoId() == null) {
			throw new GenericException("El valor elementoId es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (!infoElementoRepo.existsById(request.getElementoId())) {
			throw new GenericException("El id del elemento " + request.getElementoId() + " no existe", CoreUtilConstants.EXISTING_VALUES);
		}
		if (request.getDetalleNombre() == null) {
			throw new GenericException("El valor detalleNombre es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getDetalleValor() == null) {
			throw new GenericException("El valor detalleValor es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getDetalleDescripcion() == null) {
			throw new GenericException("El valor detalleDescripcion es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getIpCreacion() == null) {
			throw new GenericException("El valor ipCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getUsrCreacion() == null) {
			throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		InfoDetalleElemento existElementoReq = new InfoDetalleElemento();
		existElementoReq.setElementoId(request.getElementoId());
		existElementoReq.setDetalleNombre(request.getDetalleNombre());
		existElementoReq.setEstado(StatusHandler.Activo.toString());
		if (infoDetalleElementoRepo.findAll(Example.of(existElementoReq)).size() > 0) {
			throw new GenericException("El detalle " + request.getDetalleNombre() + " ya existe", CoreUtilConstants.EXISTING_VALUES);
		}
	}
	
	public InfoDetalleElemento validarActualizarDetalleElemento(InfoDetalleElemento request) throws GenericException {
		if (request.getIdDetalleElemento() == null) {
			throw new GenericException("El valor idDetalleElemento es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (!infoDetalleElementoRepo.existsById(request.getIdDetalleElemento())) {
			throw new GenericException("El id del detalle de elemento " + request.getIdDetalleElemento() + " no existe",
					CoreUtilConstants.EXISTING_VALUES);
		}
		if (request.getElementoId() != null && !infoElementoRepo.existsById(request.getElementoId())) {
			throw new GenericException("El id del elemento " + request.getElementoId() + " no existe", CoreUtilConstants.EXISTING_VALUES);
		}
		Optional<InfoDetalleElemento> optionalDetalleElemento = infoDetalleElementoRepo.findById(request.getIdDetalleElemento());
		InfoDetalleElemento response = optionalDetalleElemento.orElse(request);
		response.setElementoId(request.getElementoId() != null ? request.getElementoId() : response.getElementoId());
		response.setDetalleNombre(request.getDetalleNombre() != null ? request.getDetalleNombre() : response.getDetalleNombre());
		response.setDetalleValor(request.getDetalleValor() != null ? request.getDetalleValor() : response.getDetalleValor());
		response.setDetalleDescripcion(request.getDetalleDescripcion() != null ? request.getDetalleDescripcion() : response.getDetalleDescripcion());
		response.setRefDetalleElementoId(
				request.getRefDetalleElementoId() != null ? request.getRefDetalleElementoId() : response.getRefDetalleElementoId());
		response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
		return response;
	}
	
	public void validarDetalleElementoPorElemento(DetalleElementoReqDTO request) throws GenericException {
		if (request.getElementoId() == null && request.getNombreElemento() == null) {
			throw new GenericException("El valor elementoId o nombreElemento es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarHistorialElementoPorElemento(HistorialElementoReqDTO request) throws GenericException {
		if (request.getElementoId() == null && request.getNombreElemento() == null) {
			throw new GenericException("El valor elementoId o nombreElemento es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarHistorialElementoPorFecha(HistorialElementoPorFechaReqDTO request) throws GenericException {
		if (request.getFechaInicio() == null) {
			throw new GenericException("El valor fechaInicio es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getFechaFin() == null) {
			throw new GenericException("El valor fechaFin es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (!Formato.validarFecha(request.getFechaInicio(), "yyyy-MM-dd")) {
			throw new GenericException("El valor fechaInicio debe ser de tipo String y debe tener un formato yyyy-MM-dd",
					CoreUtilConstants.ERROR_PARSE_VALUES);
		}
		if (request.getFechaInicio().length() > 10) {
			throw new GenericException("El valor fechaInicio debe ser de tipo String y debe tener un formato yyyy-MM-dd",
					CoreUtilConstants.ERROR_PARSE_VALUES);
		}
		if (!Formato.validarFecha(request.getFechaFin(), "yyyy-MM-dd")) {
			throw new GenericException("El valor fechaFin debe ser de tipo String y debe tener un formato yyyy-MM-dd",
					CoreUtilConstants.ERROR_PARSE_VALUES);
		}
		if (request.getFechaFin().length() > 10) {
			throw new GenericException("El valor fechaFin debe ser de tipo String y debe tener un formato yyyy-MM-dd",
					CoreUtilConstants.ERROR_PARSE_VALUES);
		}
		if (!Formato.validarRangoDiasFechas(request.getFechaInicio(), request.getFechaFin(), 31, "yyyy-MM-dd")) {
			throw new GenericException("Los días máximos a consultar es de 31 días", CoreUtilConstants.INFORMATIVE_VALUES);
		}
	}
	
	public void validarMaximoDataList(long count) throws GenericException {
		if (count > InfraestructuraConstants.MAXIMO_DATA_LIST) {
			throw new GenericException("El número de datos que responde la lista supera los " + InfraestructuraConstants.MAXIMO_DATA_LIST
					+ " datos permitidos, por favor utilizar el proceso de filtrado", CoreUtilConstants.ERROR_PARSE_VALUES);
		}
	}
	
	public void validarTipoElementoVacio(AdmiTipoElemento request) throws GenericException {
		AdmiTipoElemento objNull = new AdmiTipoElemento();
		if (request.equals(objNull)) {
			throw new GenericException("Se deben declarar variables para filtrar la lista de tipos de elementos", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarMarcaElementoVacio(AdmiMarcaElemento request) throws GenericException {
		AdmiMarcaElemento objNull = new AdmiMarcaElemento();
		if (request.equals(objNull)) {
			throw new GenericException("Se deben declarar variables para filtrar la lista de marcas de elementos", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarModeloElementoVacio(AdmiModeloElemento request) throws GenericException {
		AdmiModeloElemento objNull = new AdmiModeloElemento();
		if (request.equals(objNull)) {
			throw new GenericException("Se deben declarar variables para filtrar la lista de modelos de elementos", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarElementoVacio(InfoElemento request) throws GenericException {
		InfoElemento objNull = new InfoElemento();
		if (request.equals(objNull)) {
			throw new GenericException("Se deben declarar variables para filtrar la lista de elementos", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarDetalleElementoVacio(InfoDetalleElemento request) throws GenericException {
		InfoDetalleElemento objNull = new InfoDetalleElemento();
		if (request.equals(objNull)) {
			throw new GenericException("Se deben declarar variables para filtrar la lista de detalles de elementos",
					CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarElementoPorRegionParams(ElementoPorRegionParamsReqDTO request) throws GenericException {
		if (request.getRegion() == null) {
			throw new GenericException("El valor region es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (!request.getRegion().equals("R1") && !request.getRegion().equals("R2")) {
			throw new GenericException("El valor region debe ser R1 o R2", CoreUtilConstants.INFORMATIVE_VALUES);
		}
	}
	
	public void validarElementoPorProvinciaParams(ElementoPorProvinciaParamsReqDTO request) throws GenericException {
		if (request.getProvinciaId() == null && request.getNombreProvincia() == null) {
			throw new GenericException("El valor provinciaId o nombreProvincia es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarElementoPorParroquiaParams(ElementoPorParroquiaParamsReqDTO request) throws GenericException {
		if (request.getParroquiaId() == null && request.getNombreParroquia() == null) {
			throw new GenericException("El valor parroquiaId o nombreParroquia es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarElementoPorCantonParams(ElementoPorCantonParamsReqDTO request) throws GenericException {
		if (request.getCantonId() == null && request.getNombreCanton() == null) {
			throw new GenericException("El valor cantonId o nombreCanton es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarElementoPorFilialParams(ElementoPorFilialParamsReqDTO request) throws GenericException {
		if (request.getFilialId() == null && request.getNombreFilial() == null) {
			throw new GenericException("El valor filialId o nombreFilial es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarDatosVehiculo(DatosVehiculoReqDTO request) throws GenericException {
		String nombreTipo = "VEHICULO";
		if (request.getIdElemento() != null) {
			if (!infoElementoRepo.existsById(request.getIdElemento())) {
				throw new GenericException("El id del elemento " + request.getIdElemento() + " no existe", CoreUtilConstants.EXISTING_VALUES);
			}
			if (!validarTipoElemento(request.getIdElemento(), nombreTipo)) {
				throw new GenericException("El elemento no es de tipo " + nombreTipo, CoreUtilConstants.INFORMATIVE_VALUES);
			}
		}
	}
	
	private Boolean validarTipoElemento(Long idElemento, String nombreTipo) {
		Boolean response;
		try {
			InfoElemento elemento = infoElementoRepo.findById(idElemento).get();
			AdmiModeloElemento modeloElemento = admiModeloElementoRepo.findById(elemento.getModeloElementoId()).get();
			AdmiTipoElemento tipoElemento = admiTipoElementoRepo.findById(modeloElemento.getTipoElementoId()).get();
			if (tipoElemento.getNombreTipoElemento().equalsIgnoreCase(nombreTipo)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			response = false;
		}
		return response;
	}
	
	public void validarElementoPorGrupo(ElementoPorGrupoReqDTO request) throws GenericException {
		if (request.getGrupoId() == null) {
			throw new GenericException("El valor grupoId es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarElementoPorDepartamentoParams(ElementoPorDepartamentoParamsReqDTO request) throws GenericException {
		if (request.getDepartamentoId() == null && request.getNombreDepartamento() == null) {
			throw new GenericException("El valor departamentoId o nombreDepartamento es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarAsignarUbicacionElemento(UbicacionElementoReqDTO request) throws GenericException {
		if (request.getElementoId() == null) {
			throw new GenericException("El valor elementoId es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getOficinaId() == null) {
			throw new GenericException("El valor oficinaId es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getEmpresaCod() == null) {
			throw new GenericException("El valor empresaCod es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getUsrCreacion() == null) {
			throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarModificarUbicacionElemento(UbicacionElementoReqDTO request) throws GenericException {
		if (request.getElementoId() == null) {
			throw new GenericException("El valor elementoId es requerido", CoreUtilConstants.MISSING_VALUES);
		}
		if (request.getOficinaId() == null) {
			throw new GenericException("El valor oficinaId es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
	
	public void validarElementoPorCuadrillaParams(ElementoPorCuadrillaParamsReqDTO request) throws GenericException {
		if (request.getCuadrillaId() == null && request.getNombreCuadrilla() == null) {
			throw new GenericException("El valor cuadrillaId o nombreCuadrilla es requerido", CoreUtilConstants.MISSING_VALUES);
		}
	}
}
