package es.codeurjc.biciUrjc;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;





@Component
@Profile("local")
public abstract class DatabaseUsuario implements   GestionUsuario {//no se porque me ha obligado a poner abstract
	@Autowired
	private GestionUsuario UserRepository;
	
	@PostConstruct
	public void userDB() {
		UserRepository.saveAll(Arrays.asList(
			new Usuario("Raul Rodriguez", "Qwerty123456"),
			new Usuario("Daniel Requena","123456Qwerty")));
	} 
	
}
