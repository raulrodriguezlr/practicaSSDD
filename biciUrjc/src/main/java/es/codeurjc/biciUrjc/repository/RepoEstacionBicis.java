package es.codeurjc.biciUrjc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import es.codeurjc.biciUrjc.model.estacionBicicletas;


@Transactional
public interface RepoEstacionBicis  extends JpaRepository<estacionBicicletas, Long>{

	 	@Modifying
	   @Query(value = "update estacionbicicletas set coordenadas = :coordenadas where id = :id",nativeQuery = true)
	    void updateCoordenadasById(@Param("id") long id, @Param("coordenadas") String coordenadas);
}

