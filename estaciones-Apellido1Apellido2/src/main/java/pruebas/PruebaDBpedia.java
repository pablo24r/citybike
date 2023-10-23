package pruebas;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;


public class PruebaDBpedia {

	public static void main(String[] args) {
		try {
            // URL del artículo en DBpedia
            String dbpediaURL = "https://es.dbpedia.org/data/Catedral_de_Murcia.json";

            // Realizar la solicitud HTTP
            HttpURLConnection conn = (HttpURLConnection) new URL(dbpediaURL).openConnection();
            conn.setRequestMethod("GET");

            // Leer la respuesta
            InputStream inputStream = conn.getInputStream();

            // Analizar el JSON
            JsonObject json = Json.createReader(inputStream).readObject();

            // Extraer las propiedades necesarias
            String nombre = json.getJsonObject("http://www.w3.org/2000/01/rdf-schema#label").getString("value");
            String resumen = json.getJsonObject("http://dbpedia.org/ontology/abstract").getString("value");

            // Procesar y almacenar más propiedades según sea necesario

            // Cerrar la conexión y liberar recursos
            conn.disconnect();

            // Imprimir o almacenar la información en una estructura de datos
            System.out.println("Nombre: " + nombre);
            System.out.println("Resumen: " + resumen);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	}


