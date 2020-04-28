package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.dto.PageDTO;
import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.AdmiModeloElemento;

/**
 * Service ADMI_MODELO_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public interface AdmiModeloElementoService {
	public AdmiModeloElemento guardar(AdmiModeloElemento request) throws GenericException;
	public List<AdmiModeloElemento> lista() throws GenericException;
	public List<AdmiModeloElemento> listaPor(AdmiModeloElemento request) throws GenericException;
	public Page<AdmiModeloElemento> paginaListaPor(PageDTO<AdmiModeloElemento> request) throws GenericException;
}
