package es.codeurjc.biciUrjc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.codeurjc.biciUrjc.model.Usuario;


@Transactional
public interface RepoUsuario  extends JpaRepository<Usuario, Long>{
	 	@Modifying
	   @Query(value = "update usuario set nombre = :nombre where id = :id",nativeQuery = true)
	    void updateNombreById(@Param("id") long id, @Param("nombre") String nombre);
	 	@Modifying
		   @Query(value = "update usuario set apellido = :apellido where id = :id",nativeQuery = true)
		    void updateApellidoById(@Param("id") long id, @Param("apellido") String apellido);
	 	@Modifying
		   @Query(value = "update usuario set contraseña = :contraseña where id = :id",nativeQuery = true)
		    void updateContraseñaById(@Param("id") long id, @Param("contraseña") String apellido);
	
}

