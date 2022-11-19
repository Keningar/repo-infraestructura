package ec.telconet.microservicios.dependencias.esquema.infraestructura.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoProcesoMasivoCab;

/**
 * Repositorio INFO_PROCESO_MASIVO_CAB
 * 
 * @author Pedro Velez <mailto:psvelez@telconet.ec>
 * @version 1.0
 * @since 23/04/2022
 */
@Repository
public interface InfoProcesoMasivoCabRepository extends JpaRepository<InfoProcesoMasivoCab,Long> {
    
}
