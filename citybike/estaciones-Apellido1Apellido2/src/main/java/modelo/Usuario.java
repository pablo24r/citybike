package modelo;

import java.util.Date;

public class Usuario {

	private String email;
	private Date fechaNacimiento;
	private int telefono;
	private String nombreApellidos;
	private boolean admin;
	public Usuario(String email, Date fechaNacimiento, int telefono, String nombreApellidos) {
		super();
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.nombreApellidos = nombreApellidos;
		this.admin = false;
	}
	
	public void setAdmin(boolean valor) {
		this.admin = valor;
	}
	
	
}
