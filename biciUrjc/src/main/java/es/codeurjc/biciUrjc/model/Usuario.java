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
	private Long id;
	public String contraseña;
	
	public String nombre;
	//public Optional<URL> foto;
	
	public String fechaAlta;
	public Estado estado;//true=> activo false=> inactivo
	
	private enum Estado{
		ACTIVO,INACTIVO
	}
	public Usuario() {}
	
	public Usuario(String name,String contraseña,Optional<URL> photo) {
		super();
		this.nombre = name; 
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