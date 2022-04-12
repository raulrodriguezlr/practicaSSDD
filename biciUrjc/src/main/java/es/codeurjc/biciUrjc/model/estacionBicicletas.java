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
public class estacionBicicletas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	public int numeroSerie;
	public String coordenadas;
	
	public int capacidad;
	public Estado estado;
	public String fechaInstalacion;
	
	private enum Estado{
		ACTIVO,INACTIVO
	}
	public estacionBicicletas() {}
	
	public estacionBicicletas(int numeroSerie, String coordenadas,int capacidad) {
		super();
		this.numeroSerie = numeroSerie; 
		this.coordenadas=coordenadas;
		this.capacidad=capacidad;

		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.fechaInstalacion = dtf.format(LocalDateTime.now());
		estado=Estado.ACTIVO;
				
	}
	
	
	public void setNumeroSerie(int numSerie) {
	
		this.numeroSerie=numSerie;
	}
	public void setSCoordenadas(String coordenadas) {
		this.coordenadas=coordenadas;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad=capacidad;
	}
	public int getNumeroSerie() {
		return this.numeroSerie;
	}
	public String getCoordenadas() {
		return this.coordenadas;
	}
	public int getCapacidad() {
		return this.capacidad;
	}
}
