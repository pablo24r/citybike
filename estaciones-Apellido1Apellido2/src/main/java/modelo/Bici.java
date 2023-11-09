package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import repositorios.Identificable;

@Entity
@Table(name = "bici")
public class Bici implements Identificable, Serializable{
	@Id
	private String codigo;
	@Column(name="modelo")
	private String modelo;
	@Column(name = "fecha_alta", columnDefinition = "DATE")
	private LocalDate fechaAlta;
	@Column(name = "fecha_baja", columnDefinition = "DATE")
	private LocalDate fechaBaja;
	@Lob
	@Column(name="motivo")
	private String motivo;
	@Column(name="estacion_actual")
	private Estacion estacionActual; //probablemente haya que quitarlo y coger la estacionActual con el historico
	@Enumerated(EnumType.STRING)
	@Column(name="estado")
	private EstadoBici estado;
	@OneToMany(mappedBy = "bici", cascade = CascadeType.ALL)
	private List<Incidencia> incidencias;
	
	
	public Bici(String codigo, String modelo, LocalDate fechaAlta) {
		super();
		this.codigo = codigo;
		this.modelo = modelo;
		this.fechaAlta = fechaAlta;
		this.incidencias = new LinkedList<>();
		this.estacionActual = null;
		this.estado = EstadoBici.DISPONIBLE;
	}	
	
	public List<Incidencia> getIncidencias() {
		return incidencias;
	}

	public void setIncidencias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}
	
	public void añadirIncidencia(Incidencia incidencia) {
		this.incidencias.add(incidencia);
		this.setEstado(EstadoBici.NO_DISPONIBLE);
	}

	public Estacion getEstacionActual() {
		return estacionActual;
	}


	public void setEstacionActual(Estacion estacionActual) {
		this.estacionActual = estacionActual;
		estacionActual.aparcarEnEstacion();
	}

	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public LocalDate getFechaAlta() {
		return fechaAlta;
	}


	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}


	public LocalDate getFechaBaja() {
		return fechaBaja;
	}


	public void setFechaBaja(LocalDate fechaBaja) {
		this.fechaBaja = fechaBaja;
	}


	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public EstadoBici getEstado() {
		return estado;
	}


	public void setEstado(EstadoBici estado) {
		this.estado = estado;
	}

	@Override
	public String getId() {
		return codigo;
	}

	@Override
	public void setId(String id) {
		this.codigo = id;
	}

	
	
}
