package ec.telconet.microservicios.dependencias.esquema.infraestructura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoKardex;

/**
 * Repositorio INFO_KARDEX
 * 
 * @author José Castillo <mailto:jmcastillo@telconet.ec>
 * @version 1.0
 * @since 03/05/2023
 */
@Repository
public interface InfoKardexRepository extends JpaRepository<InfoKardex, Long> {
	List<InfoKardex> findByFilial(String filial);
}
