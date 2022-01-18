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
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.AdmiModeloElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.AdmiModeloElementoRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.AdmiModeloElementoService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraValidators;

/**
 * Impl Service ADMI_MODELO_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public class AdmiModeloElementoImpl implements AdmiModeloElementoService {
	@Autowired
	private InfraestructuraValidators infraestructuraValidators;
	
	@Autowired
	private AdmiModeloElementoRepository admiModeloElementoRepo;
	
	/**
	 * Método que guarda un modelo de elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public AdmiModeloElemento guardar(AdmiModeloElemento request) throws GenericException {
		AdmiModeloElemento response = new AdmiModeloElemento();
		try {
			infraestructuraValidators.validarGuardarModeloElemento(request);
			request.setEstado(StatusHandler.Activo.toString());
			request.setFeCreacion(new Date());
			request.setFeUltMod(new Date());
			response = admiModeloElementoRepo.save(request);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que actualizar un modelo de elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public AdmiModeloElemento actualizar(AdmiModeloElemento request) throws GenericException {
		AdmiModeloElemento response = new AdmiModeloElemento();
		try {
			request = infraestructuraValidators.validarActualizarModeloElemento(request);
			request.setFeUltMod(new Date());
			response = admiModeloElementoRepo.save(request);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de modelo de elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public List<AdmiModeloElemento> lista() throws GenericException {
		List<AdmiModeloElemento> response = new ArrayList<AdmiModeloElemento>();
		try {
			infraestructuraValidators.validarMaximoDataList(admiModeloElementoRepo.count());
			response = admiModeloElementoRepo.findAll();
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de modelo de elemento con filtros
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public List<AdmiModeloElemento> listaPor(AdmiModeloElemento request) throws GenericException {
		List<AdmiModeloElemento> response = new ArrayList<AdmiModeloElemento>();
		try {
			infraestructuraValidators.validarModeloElementoVacio(request);
			Example<AdmiModeloElemento> listFiltros = Example.of(request);
			response = admiModeloElementoRepo.findAll(listFiltros, Sort.by(Direction.DESC, AdmiModeloElemento.idModeloElementoValue));
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la paginación de una lista de modelo de elemento con
	 * filtros
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public Page<AdmiModeloElemento> paginaListaPor(PageDTO<AdmiModeloElemento> request) throws GenericException {
		Page<AdmiModeloElemento> response = null;
		Pageable pageable;
		try {
			infraestructuraValidators.validarPagina(request);
			Example<AdmiModeloElemento> listFiltros = Example.of(request.getTabla());
			if (request.getOrder() != null && request.getOrderValue() != null) {
				pageable = PageRequest.of(request.getPage(), request.getSize(),
						Sort.by(Direction.valueOf(request.getOrder()), request.getOrderValue()));
			} else {
				pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.Direction.DESC, AdmiModeloElemento.idModeloElementoValue);
			}
			response = admiModeloElementoRepo.findAll(listFiltros, pageable);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
}
