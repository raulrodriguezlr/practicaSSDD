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
import es.codeurjc.biciUrjc.repository.RepoEstacionBicis;
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
	private RepoEstacionBicis estacionInterface;
	
	// Menu principal del modulo de gestion de bicicletas
	@GetMapping("/gestionBicicletas")
	public String lists(Model model) {
		List<Bicicleta> bicicletas= biciInterface.findAll();
		model.addAttribute("bicicleta", bicicletas);
		return "Gestion_Bicicletas/modulo_gestion_bicicletas";
	}
	
	// Mostrar detalles de una bicicleta
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
	
	// Mostrar menu para agregar una bicicleta
	@GetMapping("/agregarBicicletas")
	public String agregarBicicletas(Model model) {
		return "Gestion_Bicicletas/agregarBicicleta";
	}
	
	// Agregar una bicicleta
	@GetMapping("/agregarBicicleta")
	public String agregarBicicleta(Model model,@RequestParam String numeroSerie,@RequestParam String modelo) {
		Bicicleta bici = new Bicicleta(numeroSerie, modelo);
		biciService.save(bici);
		return "redirect:/gestionBicicletas";
	}
	
	// Mostrar listado de estaciones para asignar la bicicleta
	@GetMapping("/gestionBicicleta/asignarBase/{id_b}")
	public String AsignarBase(Model model,@PathVariable (value="id_b")long id) {
		Optional<Bicicleta> bici = biciService.findOne(id);
		if(bici.isPresent()) {
			Bicicleta bicicleta = bici.get();
			List<estacionBicicletas> estaciones= estacionInterface.findAll();
			List<estacionBicicletas> estaciones_act= new ArrayList<estacionBicicletas>();
			List<estacionBicicletas> estaciones_act_llena= new ArrayList<estacionBicicletas>();
			List<estacionBicicletas> estaciones_inact= new ArrayList<estacionBicicletas>();
			String completa="";
			for(int i=0; i<estaciones.size(); i++) {
				estacionBicicletas estacion = estaciones.get(i);
				if (estacion.estacionLlena()) {
					estaciones_act_llena.add(estaciones.get(i));
					completa = "(completa)";
				}
				else if(estacion.getEstado()=="INACTIVO"){
					estaciones_inact.add(estaciones.get(i));
				}
				else {
					estaciones_act.add(estaciones.get(i));
				}
			}
			model.addAttribute("completa", completa);
			model.addAttribute("estaciones_act_llena",estaciones_act_llena);
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
	
	// Asignar estacion a una bicicleta
	@GetMapping("/asignarEstacion/{id}/{id_b}")
	public String AsignarEstacion(Model model,@PathVariable (value="id")long id_estacion,@PathVariable (value="id_b")long id_bici) {
		Optional<estacionBicicletas> est = estaService.findOne(id_estacion);
		Optional<Bicicleta> bici = biciService.findOne(id_bici);
		if(est.isPresent() && bici.isPresent()) {
			estacionBicicletas estacion = est.get();
			biciService.establecerEstacion(id_bici,estacion);
			biciService.editarEstado(id_bici,"En-Base");
			return "redirect:/gestionBicicletas";
		}
		else {
			model.addAttribute("fallo","Fallo al asginar base");
			return "fallo";
		}
	}
	
	// Dar de baja bici con estacion
	@GetMapping("/gestionBicicleta/baja/{id}/{id_b}")
	public String BajaEstacion(Model model,@PathVariable (value="id")long id_estacion,@PathVariable (value="id_b")long id_bici) {
		Optional<estacionBicicletas> est = estaService.findOne(id_estacion);
		Optional<Bicicleta> bici = biciService.findOne(id_bici);
		if(est.isPresent() && bici.isPresent()) {
			Bicicleta bicicleta = bici.get();
			estacionBicicletas estacion = est.get();
			estaService.editarActivo(id_estacion,"ACTIVO");
			biciService.editarEstado(id_bici,"Baja");
			biciService.establecerEstacion(bicicleta.getId(), null);
			return "redirect:/gestionBicicletas";
		}
		else {
			model.addAttribute("fallo","Fallo al asginar base");
			return "fallo";
		}
	}
	
	// Dar de baja bici sin estacion
	@GetMapping("/gestionBicicleta/bajaSinBase/{id_b}")
	public String BajaEstacionSinBase(Model model, @PathVariable (value="id_b")long id_bici) {
		Optional<Bicicleta> bici = biciService.findOne(id_bici);
		if(bici.isPresent()) {
			Bicicleta bicicleta = bici.get();
			biciService.editarEstado(id_bici,"Baja");
			biciService.establecerEstacion(bicicleta.getId(), null);
			return "redirect:/gestionBicicletas";
		}
		else {
			model.addAttribute("fallo","Fallo al asginar base");
			return "fallo";
		}
	}
}
