package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoProcesoMasivoDet;

/**
 * Service INFO_PROCESO_MASIVO_DET
 * 
 * @author Pedro Velez <mailto:psvelez@telconet.ec>
 * @version 1.0
 * @since 03/04/2022
 */
@Service
public interface InfoProcesoMasivoDetService {

    public List<InfoProcesoMasivoDet> listarPor( InfoProcesoMasivoDet request) throws GenericException;
    public InfoProcesoMasivoDet actualizarRegistroPor(InfoProcesoMasivoDet request) throws GenericException;
    
}
