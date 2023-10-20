package estaciones;

import modelo.SitioTuristico;

public interface ISItiosTuristicos {

	SitioTuristico getSitiosDeInteres(String coordenadas);
	
	String getInfoSitioDeInteres(String id);
	
}
