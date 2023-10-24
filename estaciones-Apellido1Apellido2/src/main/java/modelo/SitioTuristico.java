package modelo;

import repositorios.Identificable;

public class SitioTuristico implements Identificable {

	private String id;
	private String nombre;
	private String descripcion;
	private String distancia;
	private String URL;
	
	
	public SitioTuristico(String nombre, String descripcion, String distancia, String URL) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.distancia = distancia;
		this.URL = URL;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getDistancia() {
		return distancia;
	}


	public void setDistancia(String distancia) {
		this.distancia = distancia;
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
	
	
}
