package modelo;

import java.util.List;

import javax.json.bind.annotation.JsonbProperty;

import repositorios.Identificable;

public class SitioTuristicoCompleto implements Identificable{ 
	
	private String id;
    @JsonbProperty("nombre-sitio")
    private String nombre;
    @JsonbProperty("resumen-sitio")
    private String resumen;
    @JsonbProperty("categorias-sitio")
    private List<String> categorias;
    @JsonbProperty("enlaces-sitio")
    private List<String> enlaces;
    @JsonbProperty("imagen-sitio")
    private String imagen;
    @JsonbProperty("URL-sitio")
    private String URL;
	
	public SitioTuristicoCompleto(String nombre, String resumen, List<String> categorias, List<String> enlaces,
			String imagen, String uRL) {
		super();
		this.nombre = nombre;
		this.resumen = resumen;
		this.categorias = categorias;
		this.enlaces = enlaces;
		this.imagen = imagen;
		this.URL = uRL;
	}

	public SitioTuristicoCompleto() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public List<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}

	public List<String> getEnlaces() {
		return enlaces;
	}

	public void setEnlaces(List<String> enlaces) {
		this.enlaces = enlaces;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + "\nResumen: " + resumen
				+ "\nCategorias: " + categorias + "\nEnlaces: " + enlaces + "\nImagen: " + imagen + "\nURL: " + URL;
	}
	
}
