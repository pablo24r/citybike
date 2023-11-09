package estaciones;

import java.util.List;

import modelo.Incidencia;
import repositorios.EntidadNoEncontrada;
import repositorios.RepositorioException;

public interface IServicioIncidencias {

	void crearIncidencia(String idBici, String descripcionIncidencia) throws RepositorioException, EntidadNoEncontrada;
	
	List<Incidencia> recuperarIncidenciasAbiertas() throws RepositorioException;

	void cancelarIncidencia(String idIncidencia, String idBici, String motivoCierre) throws RepositorioException, EntidadNoEncontrada;

	void asignarIncidencia(String idIncidencia, String idBici, String operario) throws RepositorioException, EntidadNoEncontrada;

	void resolverIncidencia(String idIncidencia, String idBici, String motivoCierre, boolean reparada) throws RepositorioException, EntidadNoEncontrada;
	
}
