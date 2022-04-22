package es.codeurjc.biciUrjc.model;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Bicicleta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_b;
	// el id nos servira para realizar todas las operaciones con las bicis pero no estara de cara al publico como los usuarios o estaciones
	
	public String numeroSerie;
	public String modelo;
	public String fechaAlta;
	public String Estado;
	
	@OneToOne
	public estacionBicicletas estacion; // estacion a la que pertenece
	
	
	public Bicicleta() {}
	
	public Bicicleta(String numeroSerie, String modelo) {
		super();
		this.modelo = modelo;
		this.numeroSerie=numeroSerie;

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.fechaAlta = dtf.format(LocalDateTime.now());
		this.Estado="Sin-Base";
				
	}
	
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie=numeroSerie;
	}
	
	public void setEstado(String estado) {
		this.Estado=estado;
	}
	
	public void setModelo(String modelo) {
		this.modelo=modelo;
	}
	
	public void setEstacion(estacionBicicletas estacion) {
		this.estacion=estacion;
	}
	
	public String getNumeroSerie() {
		return this.numeroSerie;
	}
	
	public String getEstado() {
		return this.Estado;
	}
	
	public String getModelo() {
		return this.modelo;
	}
	
	public estacionBicicletas getEstacion() {
		return this.estacion;
	}

}