package es.codeurjc.biciUrjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import es.codeurjc.biciUrjc.model.Bicicleta;
import es.codeurjc.biciUrjc.model.Usuario;
import es.codeurjc.biciUrjc.model.estacionBicicletas;
import es.codeurjc.biciUrjc.repository.RepoBicicletas;
import es.codeurjc.biciUrjc.repository.RepoEstacionBicis;
import es.codeurjc.biciUrjc.repository.RepoUsuario;
import es.codeurjc.biciUrjc.service.BicicletaService;
import es.codeurjc.biciUrjc.service.EstacionService;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@Profile("local")
public class DBInitializer {
	
	
	
	@Autowired
	private RepoUsuario UserRepo;

	@Autowired
	private RepoBicicletas BicicletasRepo;

	@Autowired
	private RepoEstacionBicis EstacionRepo;

		
	@PostConstruct
	public void userDB() {
		UserRepo.saveAll(Arrays.asList(
			new Usuario("Raul"," Rodriguez", "Qwerty123456"),
			new Usuario("Daniel"," Requena", "123456Qwerty"),
			new Usuario("David"," Gomez", "SSDD2021/2022"),
			new Usuario("Oscar"," Soto", "SSDDApoyo2021/2022"),
			new Usuario("alonso"," Vazquez", ".-SSDD.-")
			));
		
	
		EstacionRepo.saveAll(Arrays.asList(
			new estacionBicicletas(123401,"40.336726, -3.875893", 5),
			new estacionBicicletas(123402,"40.335498, -3.874363", 10),
			new estacionBicicletas(123403,"40.334339, -3.878446", 5)
			));
		BicicletasRepo.saveAll(Arrays.asList(
				new Bicicleta("0O1P2Q3R4S5T6U7V","MOUNTAIN","En-Base"),
				new Bicicleta("0A1B2C3D4E5F6G7H","BMX","En-Base"),
				new Bicicleta("ZQRF54TH99PLF90T","MOUNTAIN","En-Base"),
				new Bicicleta("XXAGER746DRGF90P","STREET","En-Base"),
				new Bicicleta("V4L543ETINA9044M","MOUNTAIN","En-Base"),
				new Bicicleta("BHUFBHD74BC545VB","BMX","En-Base"),
				new Bicicleta("DAFT74FDFF66F90P","STREET","En-Base"),
				new Bicicleta("ZID904FBSFSANE10","STREET"),
				new Bicicleta("ASTUCHD74BC545VB","BMX")
				));
		
		List<Bicicleta> bici = BicicletasRepo.findAll();
		List<estacionBicicletas> estaciones= EstacionRepo.findAll();
		BicicletasRepo.updateEstacionById(bici.get(0).getId(), estaciones.get(0));
		BicicletasRepo.updateEstacionById(bici.get(1).getId(), estaciones.get(0));
		BicicletasRepo.updateEstacionById(bici.get(2).getId(), estaciones.get(0));
		BicicletasRepo.updateEstacionById(bici.get(3).getId(), estaciones.get(0));
		BicicletasRepo.updateEstacionById(bici.get(4).getId(), estaciones.get(1));
		BicicletasRepo.updateEstacionById(bici.get(5).getId(), estaciones.get(1));
		BicicletasRepo.updateEstacionById(bici.get(6).getId(), estaciones.get(1));
		
		
		
		
		}	
	
}
