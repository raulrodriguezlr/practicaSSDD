package es.codeurjc.biciUrjc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	//esto es el main
	
}
