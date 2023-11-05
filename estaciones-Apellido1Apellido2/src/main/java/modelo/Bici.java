package modelo;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import repositorios.Identificable;

public class Bici implements Identificable {
	
	private String codigo;
	private String modelo;
	private LocalDate fechaAlta;
	private LocalDate fechaBaja;
	private String motivo;
	private List<Estacion> historicoEstaciones;
	private Estacion estacionActual;
	private EstadoBici estado;
	private List<Incidencia> incidencias;
	
	
	public Bici(String codigo, String modelo, LocalDate fechaAlta) {
		super();
		this.codigo = codigo;
		this.modelo = modelo;
		this.fechaAlta = fechaAlta;
		this.historicoEstaciones = new LinkedList<>();
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

	public void añadirEstacionAHistorico(Estacion estacion) {
		historicoEstaciones.add(estacion);
	}


	public Estacion getEstacionActual() {
		return estacionActual;
	}


	public void setEstacionActual(Estacion estacionActual) {
		this.estacionActual = estacionActual;
		this.añadirEstacionAHistorico(estacionActual);
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


	public List<Estacion> getHistoricoEstaciones() {
		return historicoEstaciones;
	}


	public void setHistoricoEstaciones(List<Estacion> estaciones) {
		this.historicoEstaciones = estaciones;
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
