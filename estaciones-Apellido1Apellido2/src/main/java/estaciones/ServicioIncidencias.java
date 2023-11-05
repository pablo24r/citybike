package estaciones;

import java.time.LocalDate;
import java.util.List;

import modelo.Bici;
import modelo.Incidencia;
import repositorios.EntidadNoEncontrada;
import repositorios.FactoriaRepositorios;
import repositorios.Repositorio;
import repositorios.RepositorioException;

public class ServicioIncidencias implements IServicioIncidencias {

	private Repositorio<Bici, String> repositorioBicis = FactoriaRepositorios.getRepositorio(Bici.class);
	
	@Override
	public void crearIncidencia(String idBici, String descripcionIncidencia) throws RepositorioException, EntidadNoEncontrada {
		
		if (descripcionIncidencia == null || descripcionIncidencia.isEmpty())
			throw new IllegalArgumentException("descripción: la incidencia debe tener una descripción");
		
		Bici b = repositorioBicis.getById(idBici);
		Incidencia i = new Incidencia(LocalDate.now(), descripcionIncidencia);
		b.añadirIncidencia(i);
	}

	@Override
	public void gestionarIncidencias() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Incidencia> recuperarIncidenciasAbiertas() {
		// TODO Auto-generated method stub
		return null;
	}

}
