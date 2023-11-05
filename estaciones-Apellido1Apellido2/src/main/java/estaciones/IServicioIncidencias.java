package estaciones;

import java.util.List;

import modelo.Incidencia;
import repositorios.EntidadNoEncontrada;
import repositorios.RepositorioException;

public interface IServicioIncidencias {

	void crearIncidencia(String idBici, String descripcionIncidencia) throws RepositorioException, EntidadNoEncontrada;
	
	void gestionarIncidencias();
	
	List<Incidencia> recuperarIncidenciasAbiertas();
	
}
