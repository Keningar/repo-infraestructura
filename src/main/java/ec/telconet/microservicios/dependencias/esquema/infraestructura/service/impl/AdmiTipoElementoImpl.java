package ec.telconet.microservicios.dependencias.esquema.infraestructura.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.dto.PageDTO;
import ec.telconet.microservicio.dependencia.util.enumerado.StatusHandler;
import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.AdmiTipoElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.AdmiTipoElementoRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.AdmiTipoElementoService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraValidators;

/**
 * Impl Service ADMI_TIPO_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public class AdmiTipoElementoImpl implements AdmiTipoElementoService {
	@Autowired
	private InfraestructuraValidators infraestructuraValidators;
	
	@Autowired
	private AdmiTipoElementoRepository admiTipoElementoRepo;
	
	/**
	 * Método que guarda un tipo de elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public AdmiTipoElemento guardar(AdmiTipoElemento request) throws GenericException {
		AdmiTipoElemento response = new AdmiTipoElemento();
		try {
			infraestructuraValidators.validarGuardarTipoElemento(request);
			request.setEstado(StatusHandler.Activo.toString());
			request.setFeCreacion(new Date());
			response = admiTipoElementoRepo.save(request);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que actualiza un tipo de elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public AdmiTipoElemento actualizar(AdmiTipoElemento request) throws GenericException {
		AdmiTipoElemento response = new AdmiTipoElemento();
		try {
			request = infraestructuraValidators.validarActualizarTipoElemento(request);
			request.setFeUltMod(new Date());
			response = admiTipoElementoRepo.save(request);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que elimina un tipo de elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public Boolean eliminar(AdmiTipoElemento request) throws GenericException {
		Boolean response = false;
		try {
			request.setEstado(StatusHandler.Eliminado.toString());
			request.setFeUltMod(new Date());
			request = infraestructuraValidators.validarActualizarTipoElemento(request);
			admiTipoElementoRepo.saveAndFlush(request);
			response = true;
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de tipo de elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public List<AdmiTipoElemento> lista() throws GenericException {
		List<AdmiTipoElemento> response = new ArrayList<AdmiTipoElemento>();
		try {
			infraestructuraValidators.validarMaximoDataList(admiTipoElementoRepo.count());
			response = admiTipoElementoRepo.findAll();
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de tipo de elemento con filtros
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public List<AdmiTipoElemento> listaPor(AdmiTipoElemento request) throws GenericException {
		List<AdmiTipoElemento> response = new ArrayList<AdmiTipoElemento>();
		try {
			infraestructuraValidators.validarTipoElementoVacio(request);
			Example<AdmiTipoElemento> listFiltros = Example.of(request);
			response = admiTipoElementoRepo.findAll(listFiltros, Sort.by(Direction.DESC, AdmiTipoElemento.idTipoElementoValue));
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la paginación de una lista de tipo de elemento con filtros
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public Page<AdmiTipoElemento> paginaListaPor(PageDTO<AdmiTipoElemento> request) throws GenericException {
		Page<AdmiTipoElemento> response = null;
		Pageable pageable;
		try {
			infraestructuraValidators.validarPagina(request);
			Example<AdmiTipoElemento> listFiltros = Example.of(request.getTabla());
			if (request.getOrder() != null && request.getOrderValue() != null) {
				pageable = PageRequest.of(request.getPage(), request.getSize(),
						Sort.by(Direction.valueOf(request.getOrder()), request.getOrderValue()));
			} else {
				pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.Direction.DESC, AdmiTipoElemento.idTipoElementoValue);
			}
			response = admiTipoElementoRepo.findAll(listFiltros, pageable);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
}
