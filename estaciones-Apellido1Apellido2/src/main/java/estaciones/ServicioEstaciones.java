package estaciones;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import modelo.Bici;
import modelo.Estacion;
import modelo.EstadoBici;
import modelo.Historico;
import modelo.SitioTuristico;
import repositorios.EntidadNoEncontrada;
import repositorios.FactoriaRepositorios;
import repositorios.Repositorio;
import repositorios.RepositorioException;
import servicio.FactoriaServicios;

public class ServicioEstaciones implements IServicioEstaciones {

	private Repositorio<Estacion, String> repositorioEstaciones = FactoriaRepositorios.getRepositorio(Estacion.class);
	private Repositorio<Bici, String> repositorioBicis = FactoriaRepositorios.getRepositorio(Bici.class);
	private Repositorio<Historico, String> repositorioHistorico = FactoriaRepositorios.getRepositorio(Historico.class);
	private ISitiosTuristicos servicio = FactoriaServicios.getServicio(ISitiosTuristicos.class);

	@Override
	public String darAltaEstacion(String nombre, int puestos, String direccion, String lat, String lon) throws RepositorioException {
		
		if (nombre.isEmpty() || nombre == null)
			throw new IllegalArgumentException("nombre: debes asignarle un nombre");
		
		if (puestos == 0)
			throw new IllegalArgumentException("puestos: el número de puestos no puede ser 0");
		
		if (direccion.isEmpty() || direccion == null)
			throw new IllegalArgumentException("dirección: debes asignarle una dirección");
		
		if (lat.isEmpty() || lat == null)
			throw new IllegalArgumentException("latitud: desbes asignarle una latitud");
		
		if (lon.isEmpty() || lon == null)
			throw new IllegalArgumentException("longitud: debes asignarle una longitud");
		
		Estacion estacion = new Estacion(nombre, puestos, direccion, lat, lon);
		
		String id = nombre.replace(" ", "_");
		estacion.setId(id);
		repositorioEstaciones.add(estacion);
		
		return id;
	}

	@Override
	public List<SitioTuristico> getSitiosTuristicosCercanos(String id) throws RepositorioException, EntidadNoEncontrada {
		if (repositorioEstaciones.getById(id) == null) {
			throw new IllegalArgumentException("No existe una estación con ese id");
		}
		Estacion estacion = repositorioEstaciones.getById(id);
		return servicio.getSitiosDeInteres(estacion.getLat(), estacion.getLon());
	}

	@Override
	public void setSitiosTuristicos(String id, List<SitioTuristico> sitiosTuristicos) throws RepositorioException, EntidadNoEncontrada {
		repositorioEstaciones.getById(id).getListaSitios().addAll(sitiosTuristicos);
	}

	@Override
	public Estacion getEstacion(String id) throws RepositorioException, EntidadNoEncontrada {
		
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
		
		return repositorioEstaciones.getById(id);
	}

	@Override
	public String darAltaBici(String modelo, Estacion estacion) throws RepositorioException, EntidadNoEncontrada {
		if (modelo.isEmpty() || modelo == null)
			throw new IllegalArgumentException("modelo: debes asignarle un modelo");
		
		if (estacion == null)
			throw new IllegalArgumentException("estación: debes asignarle una estación");
		
		String codigo = UUID.randomUUID().toString();
		Bici b = new Bici(codigo, modelo, LocalDate.now());
		repositorioBicis.add(b);
		estacionarBici(b.getId(), estacion.getId());
		return codigo;
	}

	@Override
	public void estacionarBici(String idBici, String idEstacion) throws RepositorioException, EntidadNoEncontrada {
		Bici b = repositorioBicis.getById(idBici);
		if (idEstacion != null) {
			Estacion e = repositorioEstaciones.getById(idEstacion);
			b.setEstacionActual(e);
			Historico h = new Historico(b, e, LocalDate.now());
			repositorioHistorico.add(h);
			repositorioEstaciones.update(e); //porque al aparcar en ella hay que actualizar que tiene un puesto menos en la persistencia
			repositorioBicis.update(b); //hay que actualizar que ahora mismo la bici esta en la estacion "e": this.estacionActual = e
		} else {
			LinkedList<Estacion> estaciones = (LinkedList<Estacion>) repositorioEstaciones.getAll();
			for (Estacion e : estaciones) {
				if (e.getPuestos() > 0) {
					b.setEstacionActual(e);
					Historico h = new Historico(b, e, LocalDate.now());
					repositorioHistorico.add(h);
					repositorioEstaciones.update(e); //porque al aparcar en ella hay que actualizar que tiene un puesto menos en la persistencia
					repositorioBicis.update(b); //hay que actualizar que ahora mismo la bici esta en la estacion "e": this.estacionActual = e
					break;
				}
			}
		}
		
	}

	@Override
	public void retirarBici(String id) throws RepositorioException, EntidadNoEncontrada {
		Bici b = repositorioBicis.getById(id);
		Estacion e = repositorioEstaciones.getById(b.getEstacionActual().getId());
		e.retirarBiciDeEstacion();
		b.setEstacionActual(null);
		List<Historico> historicos = repositorioHistorico.getAll();
		for (Historico h : historicos) {
			if (h.getBici().equals(b) && h.getFechaFin().equals(null)) { //hago este if porque puede hacer un historico de la misma bici de otro aparcamiento
				h.setFechaFin(LocalDate.now());
				repositorioHistorico.update(h);
			}
		}
		repositorioEstaciones.update(e); //porque hemos desaparcado en ella, puestos++
		repositorioBicis.update(b); //porque hay que actualizar estacionActual
	}

	@Override
	public void darBajaBici(String id, String motivo) throws RepositorioException, EntidadNoEncontrada {
		Bici b = repositorioBicis.getById(id);
		b.setMotivo(motivo);
		b.setFechaBaja(LocalDate.now());
		b.setEstado(EstadoBici.NO_DISPONIBLE);	
		retirarBici(b.getId());
	}

	@Override
	public List<Bici> getBicicletasCercanas(String lat, String lon) {
		//preguntar si es con geonames
		return null;
	}

	@Override
	public List<Estacion> getEstacionesConMasSitiosTuristicosCerca() {
		//ya lo hare
		return null;
	}
	

}
