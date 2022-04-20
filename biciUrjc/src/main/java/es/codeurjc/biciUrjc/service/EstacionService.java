package es.codeurjc.biciUrjc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.biciUrjc.model.estacionBicicletas;
import es.codeurjc.biciUrjc.repository.RepoEstacionBicis;


@Service
public class EstacionService{
	
	@Autowired
	private RepoEstacionBicis repository;
	public EstacionService(RepoEstacionBicis repository) {
		this.repository=repository;
	}
	
	public Optional<estacionBicicletas> findOne(Long id){
		return repository.findById(id);
	}
	
	public boolean exist(Long id) {
		return repository.existsById(id);
	}
	public Optional<estacionBicicletas> findOne(long id) {
		return repository.findById(id);
	}

	public List<estacionBicicletas> findAll() {
		return repository.findAll();
	}
	
	public estacionBicicletas save(estacionBicicletas estacion) {
		estacionBicicletas newEstacion = repository.save(estacion);
		return newEstacion;
	}
	public void editarCoordenadas(long id,String coord) {
		repository.updateCoordenadasById(id, coord);
	}
	public void editarActivo(long id,String Estado) {
		repository.updateEstadoById(id, Estado);
	}
	public void delete(Long Id) {
		repository.deleteById(Id);
	}
}
