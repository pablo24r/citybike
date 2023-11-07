package repositorios;

import modelo.Bici;

public class RepositorioJPABici extends RepositorioJPA<Bici> {

	@Override
	public Class<Bici> getClase() {
		return Bici.class;
	}

	@Override
	public String getNombre() {
		return Bici.class.getName().substring(Bici.class.getName().lastIndexOf(".") + 1);
	}

}
