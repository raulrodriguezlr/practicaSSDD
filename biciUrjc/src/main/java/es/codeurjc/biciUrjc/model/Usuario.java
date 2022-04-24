package es.codeurjc.biciUrjc.model;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	public String contraseña;
	
	public String nombre;
	public String apellido;
	
	public String fechaAlta;
	public String Estado;
	
	
	public Usuario() {}
	
	public Usuario(String name, String apellido,String contraseña) {
		super();
		this.nombre = name; 
		this.apellido=apellido;
		this.contraseña=contraseña;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.fechaAlta = dtf.format(LocalDateTime.now());
		this.Estado="ACTIVO";
				
	}
	public Usuario(String name,String contraseña) {
		super();
		this.nombre = name; 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.fechaAlta = dtf.format(LocalDateTime.now());
		this.contraseña=contraseña;
		this.Estado="ACTIVO";
				
	}
	public void setName(String nombreNuevo) {
	
		this.nombre=nombreNuevo;
	}
	public void setActivo(String estado) {
		
		this.Estado=estado;
	}
	public void setSurname(String apellido) {
		this.apellido=apellido;
	}
	public void setPassword(String contraseña) {
		this.contraseña=contraseña;
	}
	public String getName() {
		return this.nombre;
	}
	public String getApellido() {
		return this.apellido;
	}
	public String getContraseña() {
		return this.contraseña;
	}
	public String getEstado() {
		return this.Estado;
	}
}