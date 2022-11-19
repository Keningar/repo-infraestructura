package ec.telconet.microservicios.dependencias.esquema.infraestructura.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoProcesoMasivoCab;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.InfoProcesoMasivoCabRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InfoProcesoMasivoCabService;

@Service
public class InfoProcesoMasivoCabImpl  implements InfoProcesoMasivoCabService{

    @Autowired
    InfoProcesoMasivoCabRepository  infoProcesoMasivoRepo;

	/**
	 * Método que retorna la lista de procesos masivos con filtros
	 * 
	 * @author Pedro Velez <mailto:psvelez@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
    public List<InfoProcesoMasivoCab> listarPor( InfoProcesoMasivoCab request) throws GenericException{
        List<InfoProcesoMasivoCab> response;

        Example<InfoProcesoMasivoCab> listFiltros = Example.of(request);
        response = infoProcesoMasivoRepo.findAll(listFiltros, Sort.by(Direction.ASC,"idProcesoMasivoCab" ));
        return response;
    }

    	/**
	 * Método que actualiza registro para procesos masivos 
	 * 
	 * @author Pedro Velez <mailto:psvelez@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
    public InfoProcesoMasivoCab actualizarRegistroPor(InfoProcesoMasivoCab request) throws GenericException{
        InfoProcesoMasivoCab response;

        response = infoProcesoMasivoRepo.saveAndFlush(request);

        return response;
    }
}
