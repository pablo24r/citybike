package modelo;

import java.time.LocalDate;

import repositorios.Identificable;

public class Historico implements Identificable {

	private Bici bici;
	private Estacion estacion;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	public Historico() {

	}

	public Historico(Bici bici, Estacion estacion, LocalDate fechaInicio) {
		super();
		this.bici = bici;
		this.estacion = estacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = null;
	}

	public Bici getBici() {
		return bici;
	}

	public void setBici(Bici bici) {
		this.bici = bici;
	}

	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub

	}
}
