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
			new Bicicleta("0o1p2q3r4s5t6u7v","MOUNTAIN"),
			new Bicicleta("0a1b2c3d4e5f6g7h","BMX")//mirar por que sale un numero random en gestion de estaciones

			));
	}
	
	
}
