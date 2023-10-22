package estaciones;

import java.util.List;

import modelo.SitioTuristico;
import repositorios.EntidadNoEncontrada;
import repositorios.RepositorioException;

public interface ISItiosTuristicos {

	List<SitioTuristico> getSitiosDeInteres(String lat, String lon);
	
	String getInfoSitioDeInteres(String id) throws RepositorioException, EntidadNoEncontrada;
	
}
