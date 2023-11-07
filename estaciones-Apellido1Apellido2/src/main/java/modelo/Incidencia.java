package modelo;

import java.time.LocalDate;

public class Incidencia {

	private LocalDate fechaCreacion;
	private LocalDate fechaCierre;
	private String descripcionIncidencia;
	private String motivoCierre;
	private EstadoIncidencia estado;

	public Incidencia(LocalDate fechaCreacion, String descripcionIncidencia) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.descripcionIncidencia = descripcionIncidencia;
		this.fechaCierre = null;
		this.motivoCierre = null;
		this.estado = EstadoIncidencia.PENDIENTE;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(LocalDate fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getDescripcionIncidencia() {
		return descripcionIncidencia;
	}

	public void setDescripcionIncidencia(String descripcionIncidencia) {
		this.descripcionIncidencia = descripcionIncidencia;
	}

	public String getMotivoCierre() {
		return motivoCierre;
	}

	public void setMotivoCierre(String motivoCierre) {
		this.motivoCierre = motivoCierre;
	}

	public EstadoIncidencia getEstado() {
		return estado;
	}

	public void setEstado(EstadoIncidencia estado) {
		this.estado = estado;
	}

}
