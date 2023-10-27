package estaciones;

import java.util.List;

import modelo.Estacion;
import modelo.SitioTuristico;
import repositorios.EntidadNoEncontrada;
import repositorios.RepositorioException;

public interface IServicioEstaciones {

	String darAlta(String nombre, int puestos, String direccion, String lat, String lon) throws RepositorioException;
	
	List<SitioTuristico> getSitiosTuristicosCercanos(String id) throws RepositorioException, EntidadNoEncontrada; 
	
	void setSitiosTuristicos(String id, List<SitioTuristico> sitiosTuristicos) throws RepositorioException, EntidadNoEncontrada;
	
	Estacion getEstacion(String id) throws RepositorioException, EntidadNoEncontrada;
	
}
