package modelo;

import java.time.LocalDate;

public class Incidencia {

	private LocalDate fecha;
	private String descripcion;
	private EstadoIncidencia estado;
	
	
	public Incidencia(LocalDate fecha, String descripcion) {
		super();
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.estado = EstadoIncidencia.PENDIENTE;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public EstadoIncidencia getEstado() {
		return estado;
	}


	public void setEstado(EstadoIncidencia estado) {
		this.estado = estado;
	}
	

}
