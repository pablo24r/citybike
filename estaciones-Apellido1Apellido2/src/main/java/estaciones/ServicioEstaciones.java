package estaciones;

import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import modelo.Estacion;
import modelo.SitioTuristico;
import repositorios.EntidadNoEncontrada;
import repositorios.FactoriaRepositorios;
import repositorios.Repositorio;
import repositorios.RepositorioException;

public class ServicioEstaciones implements IServicioEstaciones {

	private Repositorio<Estacion, String> repositorio = FactoriaRepositorios.getRepositorio(Estacion.class);

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
		
		String id = repositorio.add(estacion);
		
		return id;
	}

	@Override
	public List<SitioTuristico> getSitiosTuristicosCercanos(String id) throws RepositorioException, EntidadNoEncontrada {
		LinkedList<SitioTuristico> lista = new LinkedList<>();
		Estacion estacion = repositorio.getById(id);

		try {
			// Obtener una factoría
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

			// Pedir a la factoría la construcción del analizador
			DocumentBuilder analizador = factoria.newDocumentBuilder();

			// Analizar el documento desde la URL
			Document documento = analizador.parse("http://api.geonames.org/findNearbyWikipedia?lat=" + estacion.getLat() + "&lng="
					+ estacion.getLon() + "&username=aadd&style=full&lang=es");

			// Obtener la lista de elementos 'entry'
			NodeList entries = documento.getElementsByTagName("entry");

			// Iterar sobre los elementos 'entry'
			for (int i = 0; i < entries.getLength(); i++) {
				Element entry = (Element) entries.item(i);

				// Obtener las categorías y su contenido para cada 'entry'
				String title = entry.getElementsByTagName("title").item(0).getTextContent();
				String summary = entry.getElementsByTagName("summary").item(0).getTextContent();
				String wikipediaUrl = entry.getElementsByTagName("wikipediaUrl").item(0).getTextContent();
				String distancia = entry.getElementsByTagName("distance").item(0).getTextContent();

				SitioTuristico st = new SitioTuristico(title, summary, distancia, wikipediaUrl);
				lista.add(st);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public void setSitiosTuristicos(String id, List<SitioTuristico> sitiosTuristicos) throws RepositorioException, EntidadNoEncontrada {
		getEstacion(id).getListaSitios().addAll(sitiosTuristicos);
	}

	@Override
	public Estacion getEstacion(String id) throws RepositorioException, EntidadNoEncontrada {
		
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
		
		return repositorio.getById(id);
	}
	

}
