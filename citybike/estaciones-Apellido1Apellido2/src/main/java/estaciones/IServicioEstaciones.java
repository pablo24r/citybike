package estaciones;

import java.util.List;

import modelo.Estacion;
import modelo.SitioTuristico;

public interface IServicioEstaciones {

	String darAlta(String nombre, int puestos, String direccion, String coordenadas);
	
	List<SitioTuristico> getSitiosTuristicosCercanos(String id); 
	
	void setSitiosTuristicos(String id, List<SitioTuristico> sitiosTuristicos);
	
	Estacion getEstacion(String id);
	
}
