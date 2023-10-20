package modelo;

import java.util.Date;

public class Estacion {

	private String nombre;
	private Date fechaAlata;
	private int puestos; //indica cuantas bicis puede tener en cada momento
	private String direccion;
	private String informacionTuristica;
	
	public Estacion(String nombre, Date fechaAlata, int puestos, String direccion, String informacionTuristica) {
		super();
		this.nombre = nombre;
		this.fechaAlata = fechaAlata;
		this.puestos = puestos;
		this.direccion = direccion;
		this.informacionTuristica = informacionTuristica;
	}
	
}
