package es.codeurjc.biciUrjc.controller;
import es.codeurjc.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.codeurjc.biciUrjc.model.Usuario;
import es.codeurjc.biciUrjc.repository.RepoUsuario;

@Controller
public class UsuarioController {
	@Autowired
	private RepoUsuario userInterface;
	@GetMapping("/")
	public String lists(Model model) {
		List<Usuario> usuariosList = userInterface.findAll();
		model.addAttribute("usuario", usuariosList);
		return "usuario";
		
		
		}
}
