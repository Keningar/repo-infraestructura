package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import java.util.List;

import lombok.Data;

/**
 * Clase DTO Migración Detalle
 *
 * @author Jesús Bozada <mailto:jbozada@telconet.ec>
 * @version 1.0
 * @since 25/01/2023
 */
@Data
public class MigracionDetDTO {
    private List<Integer> migracionDetItems;
}
