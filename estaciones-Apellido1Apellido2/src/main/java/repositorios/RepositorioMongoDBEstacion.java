package repositorios;

import modelo.Estacion;

public class RepositorioMongoDBEstacion extends RepositorioMongoDB<Estacion>{

	@Override
	public Class<Estacion> getClase() {
		return Estacion.class;
	}

}
