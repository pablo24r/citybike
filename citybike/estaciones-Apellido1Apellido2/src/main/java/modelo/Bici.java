package modelo;

import java.util.Date;
import java.util.List;

public class Bici {
	
	private String codigo;
	private String modelo;
	private Date fechaAlta;
	private Date fechaBaja;
	private String motivo;
	private List<Estacion> estaciones;
	
	
	public Bici(String codigo, String modelo, Date fechaAlta) {
		super();
		this.codigo = codigo;
		this.modelo = modelo;
		this.fechaAlta = fechaAlta;
	}


	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	
	

}
