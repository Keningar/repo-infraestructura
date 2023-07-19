package ec.telconet.microservicios.dependencias.esquema.infraestructura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoDetalleKardex;

/**
 * Repositorio INFO_DETALLE_KARDEX
 * 
 * @author José Castillo <mailto:jmcastillo@telconet.ec>
 * @version 1.0
 * @since 23/05/2023
 */
@Repository
public interface InfoDetalleKardexRepository extends JpaRepository<InfoDetalleKardex, Long> {

}
