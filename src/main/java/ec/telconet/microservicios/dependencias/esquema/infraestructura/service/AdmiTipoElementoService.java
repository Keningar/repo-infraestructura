package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.dto.PageDTO;
import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.AdmiTipoElemento;

/**
 * Service ADMI_TIPO_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public interface AdmiTipoElementoService {
	public AdmiTipoElemento guardar(AdmiTipoElemento request) throws GenericException;
	public AdmiTipoElemento actualizar(AdmiTipoElemento request) throws GenericException;
	public Boolean eliminar(AdmiTipoElemento request) throws GenericException;
	public List<AdmiTipoElemento> lista() throws GenericException;
	public List<AdmiTipoElemento> listaPor(AdmiTipoElemento request) throws GenericException;
	public Page<AdmiTipoElemento> paginaListaPor(PageDTO<AdmiTipoElemento> request) throws GenericException;
}
