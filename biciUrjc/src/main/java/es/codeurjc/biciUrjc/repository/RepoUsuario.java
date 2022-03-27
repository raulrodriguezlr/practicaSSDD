package es.codeurjc.biciUrjc.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.biciUrjc.model.Usuario;



public interface RepoUsuario  extends JpaRepository<Usuario, Long>{
	
}
