package modelo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import repositorios.Identificable;

@Entity
@Table(name = "incidencia")
public class Incidencia implements Identificable, Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	@Column(name = "fecha_creacion", columnDefinition = "DATE")
	private LocalDate fechaCreacion;
	@Column(name = "fecha_cierre", columnDefinition = "DATE")
	private LocalDate fechaCierre;
	@Lob
	@Column(name = "descripcion_incidencia")
	private String descripcionIncidencia;
	@Lob
	@Column(name = "motivo_cierre")
	private String motivoCierre;
	@Enumerated (EnumType.STRING)
	@Column(name="estado")
	private EstadoIncidencia estado;
	@Column(name="operario")
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
