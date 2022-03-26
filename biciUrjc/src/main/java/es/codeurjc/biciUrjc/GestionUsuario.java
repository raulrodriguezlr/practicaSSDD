package es.codeurjc.biciUrjc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



import es.codeurjc.biciUrjc.*;

public interface GestionUsuario  extends JpaRepository<Usuario, Long>{
	
}
