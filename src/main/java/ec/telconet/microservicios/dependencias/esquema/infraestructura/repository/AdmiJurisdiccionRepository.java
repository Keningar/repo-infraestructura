package ec.telconet.microservicios.dependencias.esquema.infraestructura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.AdmiJurisdiccion;

/**
 * Repositorio ADMI_JURISDICCION
 * 
 * @author Lizbeth Cruz <mailto:mlcruz@telconet.ec>
 * @version 1.0
 * @since 19/07/2021
 */
@Repository
public interface AdmiJurisdiccionRepository extends JpaRepository<AdmiJurisdiccion, Long> {
}
