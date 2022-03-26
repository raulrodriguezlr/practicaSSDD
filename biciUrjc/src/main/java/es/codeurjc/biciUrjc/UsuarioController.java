package es.codeurjc.biciUrjc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {
	@Autowired
	private GestionUsuario userInterface;
	@GetMapping("/")
	public String lists(Model model) {
		List<Usuario> usuarioList = userInterface.findAll();
		model.addAttribute("ListaUsuario", usuarioList);
		return "usuario";
		}
}
