package modelo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Pruebas {

    public static void main(String[] args) {
        try {
            // Obtener una factoría
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

            // Pedir a la factoría la construcción del analizador
            DocumentBuilder analizador = factoria.newDocumentBuilder();

            // Analizar el documento desde la URL
            Document documento = analizador.parse("http://api.geonames.org/findNearbyWikipedia?lat=47&lng=9&username=aadd&style=full&lang=es");

            // Obtener la lista de elementos 'title'
            NodeList elementos = documento.getElementsByTagName("summary");

            // Iterar sobre los elementos y imprimir su contenido
            for (int i = 0; i < elementos.getLength(); i++) {
                Element elemento = (Element) elementos.item(i);
                String titulo = elemento.getTextContent();
                System.out.println("Título: " + titulo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
