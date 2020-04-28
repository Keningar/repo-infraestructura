package ec.telconet.microservicios.dependencias.esquema.infraestructura.service.impl;

import java.util.ArrayList;
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
import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.AdmiMarcaElemento;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.AdmiMarcaElementoRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.AdmiMarcaElementoService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraValidators;

/**
 * Impl Service ADMI_MARCA_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public class AdmiMarcaElementoImpl implements AdmiMarcaElementoService {
	@Autowired
	private InfraestructuraValidators infraestructuraValidators;
	
	@Autowired
	private AdmiMarcaElementoRepository admiMarcaElementoRepo;
	
	/**
	 * Método que retorna la lista de marca de elemento
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public List<AdmiMarcaElemento> lista() throws GenericException {
		List<AdmiMarcaElemento> response = new ArrayList<AdmiMarcaElemento>();
		try {
			infraestructuraValidators.validarMaximoDataList(admiMarcaElementoRepo.count());
			response = admiMarcaElementoRepo.findAll();
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la lista de marca de elemento con filtros
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public List<AdmiMarcaElemento> listaPor(AdmiMarcaElemento request) throws GenericException {
		List<AdmiMarcaElemento> response = new ArrayList<AdmiMarcaElemento>();
		try {
			infraestructuraValidators.validarMarcaElementoVacio(request);
			Example<AdmiMarcaElemento> listFiltros = Example.of(request);
			response = admiMarcaElementoRepo.findAll(listFiltros, Sort.by(Direction.DESC, AdmiMarcaElemento.idMarcaElementoValue));
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
	
	/**
	 * Método que retorna la paginación de una lista de marca de elemento con
	 * filtros
	 * 
	 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
	public Page<AdmiMarcaElemento> paginaListaPor(PageDTO<AdmiMarcaElemento> request) throws GenericException {
		Page<AdmiMarcaElemento> response = null;
		Pageable pageable;
		try {
			infraestructuraValidators.validarPagina(request);
			Example<AdmiMarcaElemento> listFiltros = Example.of(request.getTabla());
			if (request.getOrder() != null && request.getOrderValue() != null) {
				pageable = PageRequest.of(request.getPage(), request.getSize(),
						Sort.by(Direction.valueOf(request.getOrder()), request.getOrderValue()));
			} else {
				pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.Direction.DESC, AdmiMarcaElemento.idMarcaElementoValue);
			}
			response = admiMarcaElementoRepo.findAll(listFiltros, pageable);
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
	}
}
