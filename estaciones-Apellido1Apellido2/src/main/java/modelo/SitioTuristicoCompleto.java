package modelo;

import java.awt.Image;
import java.util.List;

public class SitioTuristicoCompleto { 
	
	private String nombre;
	private String resumen;
	private List<String> categorias;
	private List<String> enlaces;
	private Image imagen;
	private String URL;
	public SitioTuristicoCompleto(String nombre, String resumen, List<String> categorias, List<String> enlaces,
			Image imagen, String uRL) {
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
	public Image getImagen() {
		return imagen;
	}
	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	
	

}
