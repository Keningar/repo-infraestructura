package ec.telconet.microservicios.dependencias.esquema.infraestructura.service;

import org.springframework.stereotype.Service;
import ec.telconet.microservicio.dependencia.util.exception.GenericException;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.ListaMigracionCabDTO;
import ec.telconet.microservicios.dependencias.esquema.infraestructura.dto.MigracionCabDTO;

/**
 * Service del paquete INKG_MIGRACION_ALTA_DENSIDAD, se deberá crear un DTO por procedimiento o función que no esten
 * relacionado a una entidad en especifico
 * 
 * @author Jesús Bozada <mailto:jbozada@telconet.ec>
 * @version 1.0
 * @since 25/01/2023
 */
@Service
public interface InkgMigracionAltaDensidadService {
	public ListaMigracionCabDTO obtenerCabMigracion() throws GenericException;

	public MigracionCabDTO validarCabMigracion(MigracionCabDTO request) throws GenericException;

	public MigracionCabDTO obtenerSplittersAgrupados(MigracionCabDTO request) throws GenericException;

	public MigracionCabDTO obtenerEnlacesAgrupados(MigracionCabDTO request) throws GenericException;

	public MigracionCabDTO obtenerClientesAgrupados(MigracionCabDTO request) throws GenericException;

	public MigracionCabDTO obtenerScopesAgrupados(MigracionCabDTO request) throws GenericException;

	public void procesarOlts(MigracionCabDTO request) throws GenericException;

	public void procesarSplitters(MigracionCabDTO request) throws GenericException;

	public void procesarEnlaces(MigracionCabDTO request) throws GenericException;

	public void procesarClientes(MigracionCabDTO request) throws GenericException;
	
	public void procesarScopes(MigracionCabDTO request) throws GenericException;

	public MigracionCabDTO reversarCabMigracion(MigracionCabDTO request) throws GenericException;
}
