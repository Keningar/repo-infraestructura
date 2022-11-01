package ec.telconet.microservicios.dependencias.esquema.infraestructura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoIp;

/**
 * Repositorio INFO_IP
 * 
 * @author David De La Cruz <mailto:ddelacruz@telconet.ec>
 * @version 1.0
 * @since 11/01/2022
 */
@Repository
public interface InfoIpRepository extends JpaRepository<InfoIp, Long>{
    
}
