package repositorios;

import modelo.SitioTuristicoCompleto;

public class RepositorioSitiosTuristicosCompletosJSON extends RepositorioJSON<SitioTuristicoCompleto>{

	@Override
	public Class<SitioTuristicoCompleto> getClase() {
		return SitioTuristicoCompleto.class;
	}

}
