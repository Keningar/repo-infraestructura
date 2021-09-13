# db-repositorio-infraestructura

Repositorio oracle del esquema infraestructura, se encuentra toda la lógica de base de datos.

## Configuración

1. Configurar el datasource en el microservicio, utilice las variables que se encuentra en config/DsInfraestructuraConfig.
2. Agregar la dependencia al pom.xml del microservicio que va a hacer uso del repositorio, debe de asegurarse de utilizar la última version, verificar en el artifactory.

## Implementación

```
<dependency>
	<groupId>ec.telconet.microservicios.dependencias</groupId>
	<artifactId>db-repositorio-infraestructura</artifactId>
	<version>dev-mlcruz</version>
</dependency> 
```
