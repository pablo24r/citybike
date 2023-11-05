package repositorios;

import modelo.Bici;

public class RepositorioJPABici extends RepositorioJPA<Bici> {

	@Override
	public Class<Bici> getClase() {
		return Bici.class;
	}

}
