package ec.telconet.microservicios.dependencias.esquema.infraestructura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.entity.InfoTipoMantenimiento;
@Repository
public interface InfoTipoMantenimientoRepository extends JpaRepository<InfoTipoMantenimiento, Long>{

}
