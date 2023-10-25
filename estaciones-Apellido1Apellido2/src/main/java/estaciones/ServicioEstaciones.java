package estaciones;

import java.util.List;


import modelo.Estacion;
import modelo.SitioTuristico;
import repositorios.EntidadNoEncontrada;
import repositorios.FactoriaRepositorios;
import repositorios.Repositorio;
import repositorios.RepositorioException;
import servicio.FactoriaServicios;

public class ServicioEstaciones implements IServicioEstaciones {

	private Repositorio<Estacion, String> repositorio = FactoriaRepositorios.getRepositorio(Estacion.class);
	private ISitiosTuristicos servicio = FactoriaServicios.getServicio(ISitiosTuristicos.class);

	@Override
	public String darAlta(String nombre, int puestos, String direccion, String lat, String lon, String info) throws RepositorioException {
		
		if (nombre.isEmpty() || nombre == null)
			throw new IllegalArgumentException("opciones: debes asignarle un nombre");
		
		if (puestos == 0)
			throw new IllegalArgumentException("opciones: el número de puestos no puede ser 0");
		
		if (direccion.isEmpty() || direccion == null)
			throw new IllegalArgumentException("opciones: debes asignarle una dirección");
		
		if (lat.isEmpty() || lat == null)
			throw new IllegalArgumentException("opciones: desbes asignarle una latitud");
		
		if (lon.isEmpty() || lon == null)
			throw new IllegalArgumentException("opciones: debes asignarle una longitud");
		
		if (info.isEmpty() || info == null)
			throw new IllegalArgumentException("opciones: debes asignarle una información turística");
		
		Estacion estacion = new Estacion(nombre,puestos, direccion, lat, lon, info);
		
		String id = nombre.replace(" ", "_");
		estacion.setId(id);
		repositorio.add(estacion);
		
		return id;
	}

	@Override
	public List<SitioTuristico> getSitiosTuristicosCercanos(String id) throws RepositorioException, EntidadNoEncontrada {
		if (repositorio.getById(id) == null) {
			throw new IllegalArgumentException("No existe una estación con ese id");
		}
		Estacion estacion = repositorio.getById(id);
		return servicio.getSitiosDeInteres(estacion.getLat(), estacion.getLon());
	}

	@Override
	public void setSitiosTuristicos(String id, List<SitioTuristico> sitiosTuristicos) throws RepositorioException, EntidadNoEncontrada {
		repositorio.getById(id).getListaSitios().addAll(sitiosTuristicos);
	}

	@Override
	public Estacion getEstacion(String id) throws RepositorioException, EntidadNoEncontrada {
		
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
		
		return repositorio.getById(id);
	}
	

}
