package es.codeurjc.biciUrjc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.biciUrjc.model.Bicicleta;
import es.codeurjc.biciUrjc.model.Usuario;
import es.codeurjc.biciUrjc.model.estacionBicicletas;
import es.codeurjc.biciUrjc.repository.RepoBicicletas;
import es.codeurjc.biciUrjc.service.BicicletaService;


@Controller
public class BicicletaController {
	
	@Autowired
	private RepoBicicletas biciInterface;
	@Autowired
	private BicicletaService biciService;
	

	@GetMapping("/gestionBicicletas")
	public String lists(Model model) {
		List<Bicicleta> bicicletas= biciInterface.findAll();
		model.addAttribute("bicicleta", bicicletas);
		return "modulo_gestion_bicicletas";
	}
	
	@GetMapping("/gestionBicicletas/{id}")
	public String detalleBicicleta(Model model,@PathVariable (value="id")long id){ 
		//lo que ponemos en el modelo es lo que queremos que nos llegue como respuesta
		Optional<Bicicleta> bici = biciService.findOne(id);
		if(bici.isPresent()) {
			Bicicleta bicicleta = bici.get();
			model.addAttribute("bicicleta", bicicleta);
			return "detallesBicicleta";
		}else {
			model.addAttribute("fallo","Fallo al mostrar los detalles de la bicicleta");
			return "fallo";
		}
	}
	
	@GetMapping("/agregarBicicletas")
	public String agregarBicicletas(Model model) {
		return "agregarBicicleta";
	}
	
	@GetMapping("/agregarBicicleta")
	public String agregarBicicleta(Model model,@RequestParam String numeroSerie,@RequestParam String modelo) {
		Bicicleta bici = new Bicicleta(numeroSerie, modelo);
		biciService.save(bici);
		return "redirect:/gestionBicicletas";
	}
	
}
