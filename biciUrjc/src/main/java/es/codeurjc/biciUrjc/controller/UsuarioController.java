package es.codeurjc.biciUrjc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.biciUrjc.model.Usuario;
import es.codeurjc.biciUrjc.repository.RepoUsuario;
import es.codeurjc.biciUrjc.service.UserService;

@Controller
public class UsuarioController {
	
	@Autowired
	private RepoUsuario userInterface;
	@Autowired
	private UserService Uservice;
	@GetMapping("/gestionUsuarios")
	public String lists(Model model) {
		List<Usuario> usuarios= userInterface.findAll();
		model.addAttribute("usuario", usuarios);
		return "modulo_gestion_usuarios";
	}
	@GetMapping("/gestionUsuarios/{id}")
	public String detalleUsuario(Model model,@PathVariable (value="id")long id){ 
		//lo que ponemos en el modelo es lo que queremos que nos llegue gue como respuesta
		Optional<Usuario> user = Uservice.findOne(id);
		if(user.isPresent()) {
			Usuario usuario = user.get();
			model.addAttribute("usuarios", usuario);
			return "detallesUsuario";
		}else {
			return "index";
		}
		

	}
	
	
}
