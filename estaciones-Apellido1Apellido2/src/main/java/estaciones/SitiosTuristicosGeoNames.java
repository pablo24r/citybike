package estaciones;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import modelo.SitioTuristico;
import modelo.SitioTuristicoCompleto;
import repositorios.EntidadNoEncontrada;
import repositorios.FactoriaRepositorios;
import repositorios.Repositorio;
import repositorios.RepositorioException;

public class SitiosTuristicosGeoNames implements ISitiosTuristicos {

    private Repositorio<SitioTuristico, String> repositorio = FactoriaRepositorios.getRepositorio(SitioTuristico.class);
    private Repositorio<SitioTuristicoCompleto, String> repositorio2 = FactoriaRepositorios.getRepositorio(SitioTuristicoCompleto.class);

    @Override
    public List<SitioTuristico> getSitiosDeInteres(String lat, String lon) {
        LinkedList<SitioTuristico> lista = new LinkedList<>();

        try {
            // Obtener una factoría
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

            // Pedir a la factoría la construcción del analizador
            DocumentBuilder analizador = factoria.newDocumentBuilder();

            // Analizar el documento desde la URL
            Document documento = analizador
                    .parse("http://api.geonames.org/findNearbyWikipedia?lat=" + lat + "&lng=" + lon + "&username=citybike&style=full&lang=es");

            // Obtener la lista de elementos 'entry'
            NodeList entries = documento.getElementsByTagName("entry");

            // Iterar sobre los elementos 'entry'
            for (int i = 0; i < entries.getLength(); i++) {
                Element entry = (Element) entries.item(i);

                // Obtener las categorías y su contenido para cada 'entry'
                String nombre = entry.getElementsByTagName("title").item(0).getTextContent();
                String descripcion = entry.getElementsByTagName("summary").item(0).getTextContent();
                String wikipediaUrl = entry.getElementsByTagName("wikipediaUrl").item(0).getTextContent();
                String distancia = entry.getElementsByTagName("distance").item(0).getTextContent();

                if (!wikipediaUrl.isEmpty()) {
                    SitioTuristico st = new SitioTuristico(nombre, descripcion, distancia, wikipediaUrl);
                    String id = nombre.replace(" ", "_");
                    st.setId(id);
                    repositorio.add(st);
                    lista.add(st);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public String getInfoSitioDeInteres(String id) throws RepositorioException, EntidadNoEncontrada {

        SitioTuristicoCompleto sitioRep = repositorio2.getById(id);
        System.out.println("\nTengo ya " + id + " en el repositorioJSON?? " + (sitioRep!=null));
        if (sitioRep != null) {
            return sitioRep.toString();
        }

        String URLWikipedia = "";
        SitioTuristico st = repositorio.getById(id);
        if (st != null) {
            URLWikipedia = st.getURL();
        }

        try {
            // URL del artículo en DBpedia
            String dbpediaURL = "https://es.dbpedia.org/data/" + id + ".json";

            // Realizar la solicitud HTTP
            HttpURLConnection conn = (HttpURLConnection) new URL(dbpediaURL).openConnection();
            conn.setRequestMethod("GET");

            // Leer la respuesta
            InputStream inputStream = conn.getInputStream();

            // Analizar el JSON
            JsonReader jsonReader = Json.createReader(inputStream);
            JsonObject root = jsonReader.readObject();

            // Acceder a las propiedades
            JsonObject article = root.getJsonObject("http://es.dbpedia.org/resource/" + id);

            // Propiedad: Nombre
            JsonArray nombreArray = article.getJsonArray("http://www.w3.org/2000/01/rdf-schema#label");
            JsonObject nombreObj = nombreArray.getJsonObject(0);
            String nombre = nombreObj.getString("value");

            // Propiedad: Resumen
            JsonArray resumenArray = article.getJsonArray("http://dbpedia.org/ontology/abstract");
            JsonObject resumenObj = resumenArray.getJsonObject(0);
            String resumen = resumenObj.getString("value");

            // Propiedad: Categorías
            JsonArray categoriasArray = article.getJsonArray("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
            LinkedList<String> categorias = new LinkedList<>();
            for (JsonObject categoriaObj : categoriasArray.getValuesAs(JsonObject.class)) {
                String categoria = categoriaObj.getString("value");
                categorias.add(categoria);
            }

            // Propiedad: Enlaces externos
            JsonArray enlacesExternosArray = article.getJsonArray("http://dbpedia.org/ontology/wikiPageExternalLink");
            LinkedList<String> enlaces = new LinkedList<>();
            for (JsonObject enlaceObj : enlacesExternosArray.getValuesAs(JsonObject.class)) {
                String enlaceExterno = enlaceObj.getString("value");
                enlaces.add(enlaceExterno);
            }

            // Propiedad: Imagen en Wikimedia
            JsonArray imagenWikimediaArray = article.getJsonArray("http://es.dbpedia.org/property/imagen");
            String imagenWikipediaString;
            if (imagenWikimediaArray == null) {
                imagenWikipediaString = "";
            } else {
                JsonObject imagenWikipediaObjeto = imagenWikimediaArray.getJsonObject(0);
                imagenWikipediaString = imagenWikipediaObjeto.getString("value");
            }

            conn.disconnect();

            SitioTuristicoCompleto stc = new SitioTuristicoCompleto(nombre, resumen, categorias, enlaces,
                    imagenWikipediaString, URLWikipedia);
            stc.setId(id);
            repositorio2.add(stc);
            return stc.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR: ALGO HA IDO MAL";
    }
}

