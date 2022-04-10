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
	public Estado estado;
	
	private enum Estado{
		ACTIVO,INACTIVO
	}
	public Usuario() {}
	
	public Usuario(String name, String apellido,String contraseña) {
		super();
		this.nombre = name; 
		this.apellido=apellido;
		this.contraseña=contraseña;
	
	//	this.foto=(photo);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.fechaAlta = dtf.format(LocalDateTime.now());
		estado=Estado.ACTIVO;
				
	}
	
	public Usuario(String name,String contraseña) {
		super();
		this.nombre = name; 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.fechaAlta = dtf.format(LocalDateTime.now());
		this.contraseña=contraseña;
	//	this.foto=Optional.empty();
		estado=Estado.ACTIVO;
				
	}
}