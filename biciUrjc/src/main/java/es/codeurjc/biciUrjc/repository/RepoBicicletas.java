package es.codeurjc.biciUrjc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.codeurjc.biciUrjc.model.Bicicleta;

@Transactional
public interface RepoBicicletas  extends JpaRepository<Bicicleta, Long>{
	@Modifying
	   @Query(value = "update bicicleta set estacion = :estacion where id_b = :id_b",nativeQuery = true)
	    void updateBaseById(@Param("id_b") long id, @Param("estacion") long estacion);
	 	@Modifying
		@Query(value = "update bicicleta set Estado = :Estado where id_b = :id_b",nativeQuery = true)
	 	void updateEstadoById(@Param("id_b") long id, @Param("Estado") String Estado);
}

