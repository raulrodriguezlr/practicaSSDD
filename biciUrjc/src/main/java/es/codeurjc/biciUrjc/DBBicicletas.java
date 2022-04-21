package es.codeurjc.biciUrjc;

import java.util.Arrays;

import es.codeurjc.biciUrjc.model.Bicicleta;
import es.codeurjc.biciUrjc.model.estacionBicicletas;
import es.codeurjc.biciUrjc.repository.RepoBicicletas;
import es.codeurjc.biciUrjc.repository.RepoEstacionBicis;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@Profile("local")
public class DBBicicletas {
	
	
	
	@Autowired
	private RepoBicicletas BicicletasRepo;
		
	@PostConstruct
	
	public void userDB() {
		BicicletasRepo.saveAll(Arrays.asList(
			new Bicicleta("0O1P2Q3R4S5T6U7V","MOUNTAIN"),
			new Bicicleta("0A1B2C3D4E5F6G7H","BMX")//mirar por que sale un numero random en gestion de estaciones

			));
	}
	
	
}
