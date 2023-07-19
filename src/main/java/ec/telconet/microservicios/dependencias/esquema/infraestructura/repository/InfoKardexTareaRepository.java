package ec.telconet.microservicios.dependencias.esquema.infraestructura.repository;

import org.springframework.stereotype.Repository;

import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoKardexTarea;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio INFO_KARDEX_TAREA
 * 
 * @author José Castillo <mailto:jmcastillo@telconet.ec>
 * @version 1.0
 * @since 08/05/2023
 */
@Repository
public interface InfoKardexTareaRepository extends JpaRepository<InfoKardexTarea, Long>{

}
