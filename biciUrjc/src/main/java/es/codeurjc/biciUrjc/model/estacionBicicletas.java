package es.codeurjc.biciUrjc.model;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class estacionBicicletas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	public int numeroSerie;
	public String coordenadas;
	
	public int capacidad;
	public String estado;
	public String fechaInstalacion;
	@OneToMany (mappedBy="estacion")
	public  List<Bicicleta> bicis;
	
	public estacionBicicletas() {}
	
	public estacionBicicletas(int numeroSerie, String coordenadas,int capacidad) {
		super();
		this.numeroSerie = numeroSerie; 
		this.coordenadas=coordenadas;
		this.capacidad=capacidad;
		bicis=new ArrayList<Bicicleta>();

		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.fechaInstalacion = dtf.format(LocalDateTime.now());
		this.estado="ACTIVO";	
	}
	
	public List<Bicicleta> getBicis(){
		return bicis;
	}
	
	public void eliminarBici(Bicicleta bici) {
		try {
			int posicion = bicis.indexOf(bici);
			if (posicion!=-1) {
				bicis.remove(posicion);
			}
		}catch(NullPointerException ex) {
			System.out.println("NullPointerException");
		}
	}
	
	public boolean estacionLlena() {
		return capacidad==bicis.size();
	}
	
	public boolean estacionVacia() {
		return bicis.size()==0;
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
	public void setEstado(String estado) {
		this.estado=estado;
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
	public String getEstado() {
		return this.estado;
	}
	public long getId() {
		return id;
	}
	
}
