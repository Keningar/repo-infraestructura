package ec.telconet.microservicios.dependencias.esquema.infraestructura.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Clase utilizada para configurar el datasource del esquema DB_INFRAESTRUCTURA
 * 
 * @author Marlon Plúas <mailto:mpluas@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "InfraestructuraEMFactory", transactionManagerRef = "InfraestructuraTM", basePackages = {
		"ec.telconet.microservicios.dependencias.esquema.infraestructura.repository" })
@EntityScan(basePackages = { "ec.telconet.microservicios.dependencias.esquema.infraestructura.entity" })
public class DsInfraestructuraConfig {
	
}
