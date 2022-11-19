package ec.telconet.microservicios.dependencias.esquema.infraestructura.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoProcesoMasivoDet;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.InfoProcesoMasivoDetRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InfoProcesoMasivoDetService;

@Service
public class InfoProcesoMasivoDetImpl implements InfoProcesoMasivoDetService{

    @Autowired
    InfoProcesoMasivoDetRepository procesoMasivoDetRepo;

    /**
	 * Método que retorna la lista detalles de procesos masivos con filtros
	 * 
	 * @author Pedro Velez <mailto:psvelez@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
    public List<InfoProcesoMasivoDet> listarPor( InfoProcesoMasivoDet request) throws GenericException{
        List<InfoProcesoMasivoDet> response;

        Example<InfoProcesoMasivoDet> listFiltros = Example.of(request);
        response = procesoMasivoDetRepo.findAll(listFiltros, Sort.by(Direction.ASC,"idProcesoMasivoDet" ));
        return response;
    }

    /**
	 * Método que actualiza registro para detalles de procesos masivos 
	 * 
	 * @author Pedro Velez <mailto:psvelez@telconet.ec>
	 * @version 1.0
	 * @since 02/03/2020
	 */
    public InfoProcesoMasivoDet actualizarRegistroPor(InfoProcesoMasivoDet request) throws GenericException{
        InfoProcesoMasivoDet response;

        response = procesoMasivoDetRepo.saveAndFlush(request);

        return response;
    }

    
}
