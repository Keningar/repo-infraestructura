package ec.telconet.microservicios.dependencias.esquema.infraestructura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoElemento;

/**
 * Repositorio INFO_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Repository
public interface InfoElementoRepository extends JpaRepository<InfoElemento, Long> {
	boolean existsByNombreElementoAndEstado(String nombreElemento, String estado);
}
