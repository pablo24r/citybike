package pruebas;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class PruebaDBpedia {

    public static void main(String[] args) {
        try {
            // URL del artículo en DBpedia
        	String id = "Catedral de Murcia";
        	id = id.replace(" ", "_");
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
            System.out.println("Nombre: " + nombre);

            // Propiedad: Resumen
            JsonArray resumenArray = article.getJsonArray("http://dbpedia.org/ontology/abstract");
            JsonObject resumenObj = resumenArray.getJsonObject(0);
            String resumen = resumenObj.getString("value");
            System.out.println("Resumen: " + resumen);

            // Propiedad: Categorías
            JsonArray categoriasArray = article.getJsonArray("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
            for (JsonObject categoriaObj : categoriasArray.getValuesAs(JsonObject.class)) {
                String categoria = categoriaObj.getString("value");
                System.out.println("Categoría: " + categoria);
            }

            // Propiedad: Enlaces externos
            JsonArray enlacesExternosArray = article.getJsonArray("http://dbpedia.org/ontology/wikiPageExternalLink");
            for (JsonObject enlaceObj : enlacesExternosArray.getValuesAs(JsonObject.class)) {
                String enlaceExterno = enlaceObj.getString("value");
                System.out.println("Enlace Externo: " + enlaceExterno);
            }

            // Propiedad: Imagen en Wikimedia
            JsonArray imagenWikimediaArray = article.getJsonArray("http://es.dbpedia.org/property/imagen");
            JsonObject imagenWikipediaObjeto = imagenWikimediaArray.getJsonObject(0);
            String imagenWikipediaString = imagenWikipediaObjeto.getString("value");
            System.out.println("Imagen en Wikimedia: " + imagenWikipediaString);

            // Puedes seguir extrayendo otras propiedades aquí

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


