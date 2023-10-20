package estaciones;

import modelo.SitioTuristico;

public interface ISItiosTuristicos {

	SitioTuristico getSitiosDeInteres(String lat, String lon);
	
	String getInfoSitioDeInteres(String id);
	
}
