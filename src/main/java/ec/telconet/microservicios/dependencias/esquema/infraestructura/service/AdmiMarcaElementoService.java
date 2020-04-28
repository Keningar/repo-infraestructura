package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.dto.PageDTO;
import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.AdmiMarcaElemento;

/**
 * Service ADMI_MARCA_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public interface AdmiMarcaElementoService {
	public List<AdmiMarcaElemento> lista() throws GenericException;
	public List<AdmiMarcaElemento> listaPor(AdmiMarcaElemento request) throws GenericException;
	public Page<AdmiMarcaElemento> paginaListaPor(PageDTO<AdmiMarcaElemento> request) throws GenericException;
}
