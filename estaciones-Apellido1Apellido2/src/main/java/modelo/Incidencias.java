package modelo;

import java.util.Date;

public class Incidencias {

	private Date fecha;
	private String descripcion;
	private boolean pendiente;
	
	
	public Incidencias(Date fecha, String descripcion, boolean pendiente) {
		super();
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.pendiente = pendiente;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public boolean isPendiente() {
		return pendiente;
	}


	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}
	

}
