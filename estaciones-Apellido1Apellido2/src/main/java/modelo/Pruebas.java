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

            String latt = "37";
            String lngg = "0";
            
            // Analizar el documento desde la URL
            Document documento = analizador.parse("http://api.geonames.org/findNearbyWikipedia?lat=" + latt + "&lng=" + lngg +"&username=citybike&style=full&lang=es");

            // Obtener la lista de elementos 'entry'
            NodeList entries = documento.getElementsByTagName("entry");

            // Iterar sobre los elementos 'entry'
            for (int i = 0; i < entries.getLength(); i++) {
                Element entry = (Element) entries.item(i);

                // Obtener las categorías y su contenido para cada 'entry'
                String lang = entry.getElementsByTagName("lang").item(0).getTextContent();
                String title = entry.getElementsByTagName("title").item(0).getTextContent();
                String summary = entry.getElementsByTagName("summary").item(0).getTextContent();
                String lat = entry.getElementsByTagName("lat").item(0).getTextContent();
                String lng = entry.getElementsByTagName("lng").item(0).getTextContent();
                String wikipediaUrl = entry.getElementsByTagName("wikipediaUrl").item(0).getTextContent();
                String distancia = entry.getElementsByTagName("distance").item(0).getTextContent();


                // Imprimir las categorías y su contenido
                System.out.println("Lang: " + lang);
                System.out.println("Title: " + title);
                System.out.println("Summary: " + summary);
                System.out.println("Distancia: " + distancia);
                System.out.println("Latitude: " + lat);
                System.out.println("Longitude: " + lng);
                System.out.println("Wikipedia URL: " + wikipediaUrl);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

