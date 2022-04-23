package es.codeurjc.biciUrjc.controller;

import java.util.ArrayList;
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
import es.codeurjc.biciUrjc.service.EstacionService;


@Controller
public class BicicletaController {
	
	@Autowired
	private RepoBicicletas biciInterface;
	@Autowired
	private BicicletaService biciService;
	@Autowired
	private EstacionService estaService;
	@Autowired
	private EstacionService estacionInterface;
	

	@GetMapping("/gestionBicicletas")
	public String lists(Model model) {
		List<Bicicleta> bicicletas= biciInterface.findAll();
		model.addAttribute("bicicleta", bicicletas);
		return "Gestion_Bicicletas/modulo_gestion_bicicletas";
	}
	
	@GetMapping("/gestionBicicletas/{id_b}")
	public String detalleBicicleta(Model model,@PathVariable (value="id_b")long id){ 
		
		Optional<Bicicleta> bici = biciService.findOne(id);
		if(bici.isPresent()) {
			Bicicleta bicicleta = bici.get();
			model.addAttribute("bicicleta", bicicleta);
			if (bicicleta.getEstado().equals("Sin-Base")) {
				return "Gestion_Bicicletas/detallesBicicletaSinBase";
			}
			else if (bicicleta.getEstado().equals("Baja")) {
				return "Gestion_Bicicletas/detallesBicicletaBaja";
			}
			else {
				return "Gestion_Bicicletas/detallesBicicleta";
			}
		}else {
			model.addAttribute("fallo","Fallo al mostrar los detalles de la bicicleta");
			return "fallo";
		}
	}
	
	@GetMapping("/agregarBicicletas")
	public String agregarBicicletas(Model model) {
		return "Gestion_Bicicletas/agregarBicicleta";
	}
	
	@GetMapping("/agregarBicicleta")
	public String agregarBicicleta(Model model,@RequestParam String numeroSerie,@RequestParam String modelo) {
		Bicicleta bici = new Bicicleta(numeroSerie, modelo);
		biciService.save(bici);
		return "redirect:/gestionBicicletas";
	}
	
	@GetMapping("/gestionBicicleta/asignarBase/{id_b}")
	public String AsignarBase(Model model,@PathVariable (value="id_b")long id) {
		Optional<Bicicleta> bici = biciService.findOne(id);
		if(bici.isPresent()) {
			Bicicleta bicicleta = bici.get();
			List<estacionBicicletas> estaciones= estacionInterface.findAll();
			List<estacionBicicletas> estaciones_act= new ArrayList<estacionBicicletas>();
			List<estacionBicicletas> estaciones_inact= new ArrayList<estacionBicicletas>();
			for(int i=0; i<estaciones.size(); i++) {
				estacionBicicletas estacion = estaciones.get(i);
				if (estacion.estacionLlena() || estacion.getEstado()=="INACTIVO") 
					estaciones_inact.add(estaciones.get(i));
				else
					estaciones_act.add(estaciones.get(i));
			}
			
			model.addAttribute("estaciones_act",estaciones_act);
			model.addAttribute("estaciones_inact",estaciones_inact);
			model.addAttribute("bicicleta",bicicleta);
			return "Gestion_Bicicletas/asignarEstacion";
		}
		else {
			model.addAttribute("fallo","Fallo al asginar base");
			return "fallo";
		}
	}
	
	@GetMapping("/asignarEstacion/{id}/{id_b}")
	public String AsignarEstacion(Model model,@PathVariable (value="id")long id_estacion,@PathVariable (value="id_b")long id_bici) {
		Optional<estacionBicicletas> est = estaService.findOne(id_estacion);
		Optional<Bicicleta> bici = biciService.findOne(id_bici);
		if(est.isPresent() && bici.isPresent()) {
			estacionBicicletas estacion = est.get();
			Bicicleta bicicleta = bici.get();
			bicicleta.setEstacion(estacion);
			estacion.agregarBici(bicicleta);
			biciService.editarEstado(id_bici,"En-Base");
			
			if(estacion.estacionLlena())
				estaService.editarActivo(id_estacion,"INACTIVO");
			
			return "redirect:/gestionBicicletas";
		}
		else {
			model.addAttribute("fallo","Fallo al asginar base");
			return "fallo";
		}
	}
	
	@GetMapping("/gestionBicicleta/baja/{id}/{id_b}")
	public String BajaEstacion(Model model,@PathVariable (value="id")long id_estacion,@PathVariable (value="id_b")long id_bici) {
		Optional<estacionBicicletas> est = estaService.findOne(id_estacion);
		Optional<Bicicleta> bici = biciService.findOne(id_bici);
		if(est.isPresent() && bici.isPresent()) {
			Bicicleta bicicleta = bici.get();
			estacionBicicletas estacion = est.get();
			estacion.eliminarBici(bicicleta);
			estaService.editarActivo(id_estacion,"ACTIVO");
			biciService.editarEstado(id_bici,"Baja");
			bicicleta.setEstacion(null);
			return "redirect:/gestionBicicletas";
		}
		else {
			model.addAttribute("fallo","Fallo al asginar base");
			return "fallo";
		}
	}
}
