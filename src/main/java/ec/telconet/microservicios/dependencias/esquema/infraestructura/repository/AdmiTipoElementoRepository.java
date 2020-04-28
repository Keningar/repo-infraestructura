package ec.telconet.microservicios.dependencias.esquema.infraestructura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.AdmiTipoElemento;

/**
 * Repositorio ADMI_TIPO_ELEMENTO
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Repository
public interface AdmiTipoElementoRepository extends JpaRepository<AdmiTipoElemento, Long> {
	List<AdmiTipoElemento> findByNombreTipoElemento(String nombreTipoElemento);
}
