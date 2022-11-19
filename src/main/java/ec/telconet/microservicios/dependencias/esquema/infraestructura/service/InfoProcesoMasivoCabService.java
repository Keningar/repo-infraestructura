package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import java.util.List;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoProcesoMasivoCab;
import org.springframework.stereotype.Service;

/**
 * Service INFO_PROCESO_MASIVO_CAB
 * 
 * @author Pedro Velez <mailto:psvelez@telconet.ec>
 * @version 1.0
 * @since 03/04/2022
 */
@Service
public interface InfoProcesoMasivoCabService {

    public List<InfoProcesoMasivoCab> listarPor( InfoProcesoMasivoCab request) throws GenericException;
    public InfoProcesoMasivoCab actualizarRegistroPor(InfoProcesoMasivoCab request) throws GenericException;
    
}
