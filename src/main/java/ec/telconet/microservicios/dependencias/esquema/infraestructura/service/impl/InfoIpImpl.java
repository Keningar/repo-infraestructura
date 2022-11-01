package ec.telconet.microservicios.dependencias.esquema.infraestructura.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoIp;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.repository.InfoIpRepository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.service.InfoIpService;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.utils.InfraestructuraValidators;

/**
 * Impl Service INFO_IP
 * 
 * @author David De La Cruz <mailto:ddelacruz@telconet.ec>
 * @version 1.0
 * @since 11/01/2022
 */
@Service
public class InfoIpImpl implements InfoIpService{

    @Autowired
	private InfraestructuraValidators infraestructuraValidators;
	
	@Autowired
	@Qualifier("jdbcInfraestructura")
	private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private InfoIpRepository infoIpRepo;

    public List<InfoIp> listaPor(InfoIp request) throws GenericException {
        List<InfoIp> response = new ArrayList<>();
		try {
			infraestructuraValidators.validarIpVacio(request);
			Example<InfoIp> listFiltros = Example.of(request);
			response = infoIpRepo.findAll(listFiltros, Sort.by(Direction.DESC, InfoIp.ID_IP));
		} catch (GenericException e) {
			throw new GenericException(e.getMessageError(), e.getCodeError());
		}
		return response;
    }
    
}
