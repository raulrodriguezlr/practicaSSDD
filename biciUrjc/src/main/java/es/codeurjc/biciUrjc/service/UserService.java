package es.codeurjc.biciUrjc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.biciUrjc.model.Usuario;
import es.codeurjc.biciUrjc.repository.RepoUsuario;


@Service
public class UserService {
	
	@Autowired
	private RepoUsuario repository;
	public UserService(RepoUsuario repository) {
		this.repository=repository;
	}
	
	public Optional<Usuario> findOne(Long id){
		return repository.findById(id);
	}
	
	public boolean exist(Long id) {
		return repository.existsById(id);
	}

	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Usuario save (Usuario user) {
		Usuario newUser = repository.save(user);
		return newUser;
	}

	public void delete(Long Id) {
		repository.deleteById(Id);
	}
}
