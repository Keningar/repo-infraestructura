package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.dto.PageDTO;
import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.DetalleElementoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoDetalleElemento;

/**
 * Service INFO_DETALLE_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public interface InfoDetalleElementoService {
	public InfoDetalleElemento guardar(InfoDetalleElemento request) throws GenericException;
	public InfoDetalleElemento actualizar(InfoDetalleElemento request) throws GenericException;
	public Boolean eliminar(InfoDetalleElemento request) throws GenericException;
	public List<InfoDetalleElemento> lista() throws GenericException;
	public List<InfoDetalleElemento> listaPor(InfoDetalleElemento request) throws GenericException;
	public Page<InfoDetalleElemento> paginaListaPor(PageDTO<InfoDetalleElemento> request) throws GenericException;
	public List<InfoDetalleElemento> detalleElementoPorElemento(DetalleElementoReqDTO request) throws GenericException;
}
