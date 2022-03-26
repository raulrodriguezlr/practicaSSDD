package es.codeurjc.biciUrjc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



import es.codeurjc.biciUrjc.*;

public interface GestionUsuario  extends JpaRepository<Usuario, Long>{
	List<Usuario> findByUsuario(String user);
	List<Usuario> findAll();
	List<Usuario> saveAll();
	List<Usuario> deleteByUsuario(Usuario user);
	List<Usuario> AddByUsuario(Usuario user);
}
