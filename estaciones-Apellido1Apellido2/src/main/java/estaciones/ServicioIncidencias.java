package estaciones;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import modelo.Bici;
import modelo.EstadoBici;
import modelo.EstadoIncidencia;
import modelo.Incidencia;
import repositorios.EntidadNoEncontrada;
import repositorios.FactoriaRepositorios;
import repositorios.Repositorio;
import repositorios.RepositorioException;
import servicio.FactoriaServicios;

public class ServicioIncidencias implements IServicioIncidencias {

	private Repositorio<Bici, String> repositorioBicis = FactoriaRepositorios.getRepositorio(Bici.class);
	private IServicioEstaciones servicio = FactoriaServicios.getServicio(IServicioEstaciones.class);
	
	@Override
	public void crearIncidencia(String idBici, String descripcionIncidencia) throws RepositorioException, EntidadNoEncontrada {
		
		if (descripcionIncidencia == null || descripcionIncidencia.isEmpty())
			throw new IllegalArgumentException("descripción: la incidencia debe tener una descripción");
		
		Bici b = repositorioBicis.getById(idBici);
		Incidencia i = new Incidencia(LocalDate.now(), descripcionIncidencia);
		b.añadirIncidencia(i);
		repositorioBicis.update(b); //ponemos la bici a NO_DISPONIBLE y añadimos la incidencia a su lista
	}
	
	//Incidencias pendientes
	@Override
	public void cancelarIncidencia(String idIncidencia, String idBici, String motivoCierre) throws RepositorioException, EntidadNoEncontrada {
		Bici b = repositorioBicis.getById(idBici);
		for (Incidencia i : b.getIncidencias()) {
			if (i.getId().equals(idIncidencia) && i.getEstado().equals(EstadoIncidencia.PENDIENTE)) {
				i.setEstado(EstadoIncidencia.CANCELADO);
				i.setMotivoCierre(motivoCierre);
				i.setFechaCierre(LocalDate.now());
				b.setEstado(EstadoBici.DISPONIBLE);
				repositorioBicis.update(b);
			}
		}
	}
	
	@Override
	public void asignarIncidencia(String idIncidencia, String idBici, String operario) throws RepositorioException, EntidadNoEncontrada {
		Bici b = repositorioBicis.getById(idBici);
		for (Incidencia i : b.getIncidencias()) {
			if (i.getId().equals(idIncidencia) && i.getEstado().equals(EstadoIncidencia.PENDIENTE)) {
				i.setEstado(EstadoIncidencia.ASIGNADA);
				i.setOperario(operario);
				repositorioBicis.update(b); //se hace el update tambien al retirar la bici pero lo hago aqui tambien pq sino creo que se va a rayar
				servicio.retirarBici(idBici);
			}
		}
	}
	
	
	//Incidencias asignadas
	@Override
	public void resolverIncidencia(String idIncidencia, String idBici, String motivoCierre, boolean reparada) throws RepositorioException, EntidadNoEncontrada {
		Bici b = repositorioBicis.getById(idBici);
		for (Incidencia i : b.getIncidencias()) {
			if (i.getId().equals(idIncidencia) && i.getEstado().equals(EstadoIncidencia.ASIGNADA)) {
				i.setMotivoCierre(motivoCierre);
				i.setFechaCierre(LocalDate.now());
				if (reparada) {
					b.setEstado(EstadoBici.DISPONIBLE);
					repositorioBicis.update(b); //se hace el update tambien al retirar la bici pero lo hago aqui tambien pq sino creo que se va a rayar
					servicio.estacionarBici(idBici, null);
				} else {
					repositorioBicis.update(b); //se hace el update tambien al retirar la bici pero lo hago aqui tambien pq sino creo que se va a rayar
					servicio.darBajaBici(idBici, "No se puede reparar");
				}
			}
		}
	}
	

	@Override
	public List<Incidencia> recuperarIncidenciasAbiertas() throws RepositorioException {
	    List<Bici> bicis = repositorioBicis.getAll();

	    List<Incidencia> incidenciasAbiertas = bicis.stream()
	        .flatMap(b -> b.getIncidencias().stream())
	        .filter(i -> i.getMotivoCierre() != null && i.getFechaCierre() != null)
	        .collect(Collectors.toList());

	    return incidenciasAbiertas;
	}


}
