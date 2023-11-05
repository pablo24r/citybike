package estaciones;

import java.util.List;

import modelo.Bici;
import modelo.Estacion;
import modelo.SitioTuristico;
import repositorios.EntidadNoEncontrada;
import repositorios.RepositorioException;

public interface IServicioEstaciones {

	String darAltaEstacion(String nombre, int puestos, String direccion, String lat, String lon) throws RepositorioException;
	
	List<SitioTuristico> getSitiosTuristicosCercanos(String id) throws RepositorioException, EntidadNoEncontrada; 
	
	void setSitiosTuristicos(String id, List<SitioTuristico> sitiosTuristicos) throws RepositorioException, EntidadNoEncontrada;
	
	Estacion getEstacion(String id) throws RepositorioException, EntidadNoEncontrada;
	
	String darAltaBici(String modelo, Estacion estacion) throws RepositorioException;
	
	void estacionarBici(String idBici, String idEstacion) throws RepositorioException, EntidadNoEncontrada;
	
	void retirarBici(String id) throws RepositorioException, EntidadNoEncontrada;
	
	void darBajaBici(String id, String motivo) throws RepositorioException, EntidadNoEncontrada;
	
	List<Bici> getBicicletasCercanas(String lat, String lon);
	
	List<Estacion> getEstacionesConMasSitiosTuristicosCerca();
	
}
