package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.dto.PageDTO;
import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorCantonParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorCuadrillaParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorDepartamentoParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorFilialParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorMonitorizadoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorParroquiaParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorProvinciaParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorRegionParamsReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorTipoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoElemento;

/**
 * Service INFO_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public interface InfoElementoService {
	public InfoElemento guardar(InfoElemento request) throws GenericException;
	public InfoElemento actualizar(InfoElemento request) throws GenericException;
	public Boolean eliminar(InfoElemento request) throws GenericException;
	public List<InfoElemento> lista() throws GenericException;
	public List<InfoElemento> listaPor(InfoElemento request) throws GenericException;
	public Page<InfoElemento> paginaListaPor(PageDTO<InfoElemento> request) throws GenericException;
	public List<InfoElemento> elementoPorTipo(ElementoPorTipoReqDTO request) throws GenericException;
	public List<InfoElemento> elementoPorEsMonitorizado(ElementoPorMonitorizadoReqDTO request) throws GenericException;
	public List<InfoElemento> elementoPorRegionParams(ElementoPorRegionParamsReqDTO request) throws GenericException;
	public List<InfoElemento> elementoPorProvinciaParams(ElementoPorProvinciaParamsReqDTO request) throws GenericException;
	public List<InfoElemento> elementoPorParroquiaParams(ElementoPorParroquiaParamsReqDTO request) throws GenericException;
	public List<InfoElemento> elementoPorCantonParams(ElementoPorCantonParamsReqDTO request) throws GenericException;
	public List<InfoElemento> elementoPorFilialParams(ElementoPorFilialParamsReqDTO request) throws GenericException;
	public List<InfoElemento> elementoPorDepartamentoParams(ElementoPorDepartamentoParamsReqDTO request) throws GenericException;
	public List<InfoElemento> elementoPorCuadrillaParams(ElementoPorCuadrillaParamsReqDTO request) throws GenericException;
}
