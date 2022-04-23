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
			new Bicicleta("0A1B2C3D4E5F6G7H","BMX"),
			new Bicicleta("ZQRF54TH99PLF90T","MOUNTAIN"),
			new Bicicleta("XXAGER746DRGF90P","STREET"),
			new Bicicleta("V4L543ETINA9044M","MOUNTAIN"),
			new Bicicleta("BHUFBHD74BC545VB","BMX"),
			new Bicicleta("0O1P2Q3R4S5T6U7V","MOUNTAIN"),
			new Bicicleta("0A1B2C3D4E5F6G7H","BMX"),
			new Bicicleta("ZQRF54TH99PLF90T","MOUNTAIN"),
			new Bicicleta("XXAGER746DRGF90P","STREET"),
			new Bicicleta("V4L543ETINA9044M","MOUNTAIN"),
			new Bicicleta("BHUFBHD74BC545VB","BMX")
			));
	}
	
	
}
