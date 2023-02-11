package ec.telconet.microservicios.dependencias.esquema.infraestructura.dto;

import java.util.List;

import lombok.Data;

/**
 * Clase DTO Migración Cabecera
 *
 * @author Jesús Bozada <mailto:jbozada@telconet.ec>
 * @version 1.0
 * @since 25/01/2023
 */
@Data
public class MigracionCabDTO {
    private Integer idMigracionCab;
    private String estado;
    private String tipoRegistro;
    private String tipoProceso;
    private List<MigracionDetDTO> hilosSplitters;
    private List<MigracionDetDTO> hilosEnlaces;
    private List<MigracionDetDTO> hilosClientes;
    private List<MigracionDetDTO> hilosScopes;
}
