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
import es.codeurjc.biciUrjc.model.estacionBicicletas;
import es.codeurjc.biciUrjc.repository.RepoEstacionBicis;
import es.codeurjc.biciUrjc.repository.RepoUsuario;
import es.codeurjc.biciUrjc.service.UserService;

@Controller
public class EstacionBicicletasController {
	
	@Autowired
	private RepoEstacionBicis estacionInterface;
	@Autowired
	private UserService Uservice;
	
	@GetMapping("/gestionEstaciones")
	public String lists(Model model) {
		List<estacionBicicletas> estacion= estacionInterface.findAll();
		model.addAttribute("estacion", estacion);
		return "modulo_gestion_estacion_bicicletas";
	}
	
	
	
	
}
