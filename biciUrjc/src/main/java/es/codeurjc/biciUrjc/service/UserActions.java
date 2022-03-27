package es.codeurjc.biciUrjc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.biciUrjc.model.Usuario;
import es.codeurjc.biciUrjc.repository.RepoUsuario;
import es.codeurjc.*;

@Service
public class UserActions {
	
	@Autowired
	private RepoUsuario repository;
	
	public Usuario save (Usuario user) {
		Usuario newUser = repository.save(user);
		return newUser;
	}
	

	public void delete(Long Id) {
		repository.deleteById(Id);
	}
}
