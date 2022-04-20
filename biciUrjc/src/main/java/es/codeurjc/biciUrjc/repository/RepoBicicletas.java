package es.codeurjc.biciUrjc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.codeurjc.biciUrjc.model.Bicicleta;

@Transactional
public interface RepoBicicletas  extends JpaRepository<Bicicleta, Long>{

}

