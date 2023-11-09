package modelo;

import java.time.LocalDate;

import repositorios.Identificable;

public class Incidencia implements Identificable{

	private String id;
	private LocalDate fechaCreacion;
	private LocalDate fechaCierre;
	private String descripcionIncidencia;
	private String motivoCierre;
	private EstadoIncidencia estado;
	private String operario;

	public Incidencia(LocalDate fechaCreacion, String descripcionIncidencia) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.descripcionIncidencia = descripcionIncidencia;
		this.fechaCierre = null;
		this.motivoCierre = null;
		this.estado = EstadoIncidencia.PENDIENTE;
		this.operario = null;
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

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getOperario() {
		return operario;
	}

	public void setOperario(String operario) {
		this.operario = operario;
	}

}
