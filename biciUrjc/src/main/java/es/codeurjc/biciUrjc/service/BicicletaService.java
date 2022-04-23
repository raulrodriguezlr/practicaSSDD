package es.codeurjc.biciUrjc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.biciUrjc.model.Bicicleta;
import es.codeurjc.biciUrjc.model.estacionBicicletas;
import es.codeurjc.biciUrjc.repository.RepoBicicletas;


@Service
public class BicicletaService {
	
	@Autowired
	private RepoBicicletas repository;
	
	public BicicletaService(RepoBicicletas repository) {
		this.repository=repository;
	}
	
	public Optional<Bicicleta> findOne(Long id){
		return repository.findById(id);
	}
	
	public boolean exist(Long id) {
		return repository.existsById(id);
	}

	public Optional<Bicicleta> findOne(long id) {
		return repository.findById(id);
	}
	
	public List<Bicicleta> findAll() {
		return repository.findAll();
	}

	public void editarEstado(long id,String Estado) {
		repository.updateEstadoById(id, Estado);
	}
	
	public Bicicleta save (Bicicleta bici) {
		Bicicleta BiciNueva = repository.save(bici);
		return BiciNueva;
	}
	
	public void delete(Long Id) {
		repository.deleteById(Id);
	}
	public void establecerEstacion (long id,estacionBicicletas estacion) {
		repository.updateEstacionById(id, estacion);
	
		
	}
}
