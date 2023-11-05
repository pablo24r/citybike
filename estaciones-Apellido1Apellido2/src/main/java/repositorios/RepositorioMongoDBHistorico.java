package repositorios;

import modelo.Historico;

public class RepositorioMongoDBHistorico extends RepositorioMongoDB<Historico> {

	@Override
	public Class<Historico> getClase() {
		return Historico.class;
	}

}
