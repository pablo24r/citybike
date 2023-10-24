package modelo;

import java.awt.Image;
import java.util.List;

public class SitioTuristicoCompleto { 
	
	private String nombre;
	private String resumen;
	private List<String> categorias;
	private List<String> enlaces;
	private String imagen;
	private String URL;
	public SitioTuristicoCompleto(String nombre, String resumen, List<String> categorias, List<String> enlaces,
			String imagen, String uRL) {
		super();
		this.nombre = nombre;
		this.resumen = resumen;
		this.categorias = categorias;
		this.enlaces = enlaces;
		this.imagen = imagen;
		URL = uRL;
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
	public String toString() {
		return "Información completa del sitio turístico:\nNombre: " + nombre + "\nResumen: " + resumen
				+ "\nCategorias: " + categorias + "\nEnlaces: " + enlaces + "\nImagen: " + imagen + "\nURL: " + URL;
	}
	
	
	

}
