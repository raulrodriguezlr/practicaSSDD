package es.codeurjc.biciUrjc;

import java.util.Arrays;
import es.codeurjc.biciUrjc.model.Usuario;
import es.codeurjc.biciUrjc.repository.RepoUsuario;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@Profile("local")
public class DBUserInitializer {
	
	
	
	@Autowired
	private RepoUsuario UserRepo;
		
	@PostConstruct
	
	public void userDB() {
		UserRepo.saveAll(Arrays.asList(
			new Usuario("Raul"," Rodriguez", "Qwerty123456"),
			new Usuario("Daniel"," Requena", "123456Qwerty"),
			new Usuario("David"," Gomez", "SSDD2021/2022"),
			new Usuario("Oscar"," Soto", "SSDDApoyo2021/2022"),
			new Usuario("alonso"," Vazquez", ".-SSDD.-")
			));
	}
	
	
}
