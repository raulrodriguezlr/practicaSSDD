package es.codeurjc.biciUrjc;
import java.net.URL;
import java.time.*;
import java.util.Date;

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
	public URL foto;
	public Date fechaAlta;

	public boolean estado;//true=> activo false=> inactivo
	
	
	public Usuario() {}
	
	public Usuario(String name,String contraseña,URL foto) {
		super();
		this.nombre = name; 
		this.contraseña=contraseña;
		this.fechaAlta= new Date();//coge la fecha y hora del sistema en ese momento
		this.foto=foto;
		estado=true;
				
	}
	public Usuario(String name,String contraseña) {
		super();
		this.nombre = name; 
		this.contraseña=contraseña;
		this.fechaAlta= new Date();//coge la fecha y hora del sistema en ese momento
		estado=true;
				
	}
}