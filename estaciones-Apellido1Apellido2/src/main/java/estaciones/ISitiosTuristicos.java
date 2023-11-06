package estaciones;

import java.util.List;

import modelo.SitioTuristico;
import modelo.SitioTuristicoCompleto;
import repositorios.EntidadNoEncontrada;
import repositorios.RepositorioException;

public interface ISitiosTuristicos {

	List<SitioTuristico> getSitiosDeInteres(String lat, String lon);
	
	SitioTuristicoCompleto getInfoSitioDeInteres(String id) throws RepositorioException, EntidadNoEncontrada;
	
}
