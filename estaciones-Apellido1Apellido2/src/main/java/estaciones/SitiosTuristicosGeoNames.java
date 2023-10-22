package estaciones;

import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import modelo.SitioTuristico;
import repositorios.EntidadNoEncontrada;
import repositorios.FactoriaRepositorios;
import repositorios.Repositorio;
import repositorios.RepositorioException;

public class SitiosTuristicosGeoNames implements ISItiosTuristicos{

	private Repositorio<SitioTuristico, String> repositorio = FactoriaRepositorios.getRepositorio(SitioTuristico.class);

	@Override
	public List<SitioTuristico> getSitiosDeInteres(String lat, String lon) {
		LinkedList<SitioTuristico> lista = new LinkedList<>();
		
		try {
            // Obtener una factoría
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

            // Pedir a la factoría la construcción del analizador
            DocumentBuilder analizador = factoria.newDocumentBuilder();
            
            // Analizar el documento desde la URL
            Document documento = analizador.parse("http://api.geonames.org/findNearbyWikipedia?lat=" + lat + "&lng=" + lon +"&username=aadd&style=full&lang=es");

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
                repositorio.add(st);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return lista;
	}

	@Override
	public String getInfoSitioDeInteres(String id) throws RepositorioException, EntidadNoEncontrada {
		
		SitioTuristico st = repositorio.getById(id);
		return st.getDescripcion();
		
	}
	
}
