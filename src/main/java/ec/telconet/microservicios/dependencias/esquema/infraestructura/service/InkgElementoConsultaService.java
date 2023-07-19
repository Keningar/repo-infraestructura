package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.DatosVehiculoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.DatosVehiculoResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoKardexReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoKardexResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorGrupoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorGrupoResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorPlacaReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ElementoPorTipoReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ListaKardexReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ListaKardexResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ListaPlacaDiscoResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ModelosElemMonitorizadosReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ModelosElemMonitorizadosResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.TareasKardexReqDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.TareasKardexResDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.TotalRegistrosKardexResDTo;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoElemento;

/**
 * Service del paquete INKG_ELEMENTO_CONSULTA, se deberá crear un DTO por procedimiento o función que no estén
 * relacionado a una entidad en especifico
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Service
public interface InkgElementoConsultaService {
	public List<DatosVehiculoResDTO> datosVehiculo(DatosVehiculoReqDTO request) throws GenericException;
	public List<ElementoPorGrupoResDTO> elementoPorGrupo(ElementoPorGrupoReqDTO request) throws GenericException;
	public List<ModelosElemMonitorizadosResDTO> modelosElementoMonitorizado(ModelosElemMonitorizadosReqDTO request) throws GenericException;
	public List<ListaKardexResDTO> listaKardex(ListaKardexReqDTO request) throws GenericException;
	public List<TotalRegistrosKardexResDTo> totalRegistrosKardex(ListaKardexReqDTO request) throws GenericException;
	public List<ListaPlacaDiscoResDTO> placasVehiculo(ElementoPorPlacaReqDTO request) throws GenericException;
	public List<ElementoKardexResDTO> elementoKardex(ElementoKardexReqDTO request) throws GenericException;
	public List<TareasKardexResDTO> kardexTarea(TareasKardexReqDTO request) throws GenericException;


}
