package modelo;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import repositorios.Identificable;


public class Estacion implements Identificable {

	@Override
	public String toString() {
		return "Estacion [nombre=" + nombre + ", fechaAlata=" + fechaAlata + ", puestos=" + puestos + ", direccion="
				+ direccion + ", informacionTuristica=" + informacionTuristica + "]";
	}

	private String id;
	private String nombre;
	private LocalDate fechaAlata;
	private int puestos; //indica cuantas bicis puede tener en cada momento
	private String direccion, lat, lon;
	private String informacionTuristica;
	private List<SitioTuristico> listaSitios;
	
	public Estacion() {
		
	}
	
	public Estacion(String nombre, int puestos, String direccion, String latitud, String longitud) {
		super();
		this.nombre = nombre;
		this.fechaAlata = LocalDate.now();
		this.puestos = puestos;
		this.direccion = direccion;
		this.setLat(latitud);
		this.setLon(longitud);
		this.informacionTuristica = ""; //En la explicacion de la clase dice que se debe incluir este campo, pese a que en la funcion darAlta no se mencione.
		this.setListaSitios(new LinkedList<SitioTuristico>());
	}
	
	public void aparcarEnEstacion() {
		setPuestos(getPuestos()-1);
	}
	
	public void retirarBiciDeEstacion() {
		setPuestos(getPuestos()+1);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaAlata() {
		return fechaAlata;
	}

	public void setFechaAlata(LocalDate fechaAlata) {
		this.fechaAlata = fechaAlata;
	}

	public int getPuestos() {
		return puestos;
	}

	public void setPuestos(int puestos) {
		this.puestos = puestos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getInformacionTuristica() {
		return informacionTuristica;
	}

	public void setInformacionTuristica(String informacionTuristica) {
		this.informacionTuristica = informacionTuristica;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public List<SitioTuristico> getListaSitios() {
		return listaSitios;
	}

	public void setListaSitios(List<SitioTuristico> listaSitios) {
		this.listaSitios = listaSitios;
	}
	
}
