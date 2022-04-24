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
import es.codeurjc.biciUrjc.repository.RepoEstacionBicis;
import es.codeurjc.biciUrjc.repository.RepoBicicletas;
import es.codeurjc.biciUrjc.service.BicicletaService;
import es.codeurjc.biciUrjc.service.EstacionService;


@Controller
public class EstacionBicicletasController {
	
	@Autowired
	private RepoEstacionBicis estacionInterface;
	@Autowired
	private EstacionService Estaservice;
	@Autowired
	private RepoBicicletas bicicletasInterface;
	@Autowired
	private BicicletaService biciService;
	
	// Menu principal del modulo de gestion de estaciones
	@GetMapping("/gestionEstaciones")
	public String lists(Model model) {
		List<estacionBicicletas> estacion= estacionInterface.findAll();
		model.addAttribute("estacion", estacion);
		return "Gestion_Estaciones/modulo_gestion_estacion_bicicletas";
	}
	
	// Obtener detalles de cada estacion
	@GetMapping("/gestionEstaciones/{id}")
	public String detalleEstacion(Model model,@PathVariable (value="id")long id){ 
		Optional<estacionBicicletas> est = Estaservice.findOne(id);	
		if(est.isPresent()) {
			estacionBicicletas estacion = est.get();
			List<Bicicleta> bicicletas= bicicletasInterface.findAll();
			List<Bicicleta> bicis = estacion.getBicis();		
			model.addAttribute("estaciones", estacion);
			model.addAttribute("bicicletas", bicis);
			return "Gestion_Estaciones/detallesEstacion";
		}else {
			model.addAttribute("fallo","Fallo al mostrar los detalles de la estacion");
			return "fallo";
		}
	}
	
	// Menu de agregar una estacion
	@GetMapping("/agregarEstaciones")
	public String agregarEstaciones(Model model) {	
		return "Gestion_Estaciones/agregarEstacion";
	}
	
	// Agregar estacion
	@GetMapping("/agregarEstacion")
	public String agregarEstacion(Model model,@RequestParam int numeroSerie, @RequestParam String coordenadas, @RequestParam int capacidad) {
		estacionBicicletas est = new estacionBicicletas(numeroSerie,coordenadas,capacidad);
		Estaservice.save(est);
		return "redirect:/gestionEstaciones";
	}
	
	// Menu para editar una estacion
	@GetMapping("/gestionEstacion/editar/{id}")
	public String editarEstacion(Model model,@PathVariable (value="id")long id){ 
		Optional<estacionBicicletas> est = Estaservice.findOne(id);
		if(est.isPresent()) {
			estacionBicicletas estacion = est.get();
			model.addAttribute("estacion", estacion);
			return "Gestion_Estaciones/editarEstacion";
		}else {
			model.addAttribute("fallo","Fallo al editar las coordenadas");
			return "fallo";
		}
	}
	
	// Editar coordenadas
	@GetMapping("/editarEstacion/coordenadas/{id}")
	public String editarCoordenadas(Model model,@PathVariable (value="id")long id,@RequestParam Optional<String> coordenadas){
		Optional<estacionBicicletas> est = Estaservice.findOne(id);
		estacionBicicletas estacion;
		if(est.isPresent()) {
			estacion = est.get();
			String coordenadaNueva = coordenadas.get();
			Estaservice.editarCoordenadas(id, coordenadaNueva);
			return "redirect:/gestionEstaciones";
		}
		else {
			model.addAttribute("fallo","Fallo al cambiar las coordenadas");
			return  "fallo";
		}
	}
	
	// Dar de baja una estacion eliminando todas sus bicis 
	@GetMapping("/gestionEstacion/baja/{id}")
	public String DarDeBajaEstacion(Model model,@PathVariable (value="id")long id) {
		Optional<estacionBicicletas> est = Estaservice.findOne(id);
		estacionBicicletas estacion;
		if(est.isPresent()) {
			estacion = est.get();
			Estaservice.editarActivo(id,"INACTIVO");
			
			// dejar a la bicis SIN BASE 
			List<Bicicleta> bicis = estacion.getBicis();
			for(int i=0; i<bicis.size(); i++) {
				Bicicleta bicicleta = bicis.get(i);
				biciService.establecerEstacion(bicicleta.getId(), null); // eliminamos la bici del array de bicis de la estacion
				biciService.editarEstado(bicicleta.getId(),"Sin-Base"); // asignamos a la bici Sin-Base
			}		
			return "redirect:/gestionEstaciones";
		}
		else {
			model.addAttribute("fallo","Fallo al eliminar la estacion");
			return  "fallo";
		}
	}
	
	// Dar de alta una estacion
	@GetMapping("/gestionEstacion/alta/{id}")
	public String DarDeAltaEstacion(Model model,@PathVariable (value="id")long id) {
		Optional<estacionBicicletas> est = Estaservice.findOne(id);
		estacionBicicletas estacion;
		if(est.isPresent()) {
			estacion = est.get();
			Estaservice.editarActivo(id,"ACTIVO");
			return "redirect:/gestionEstaciones";
		}
		else {
			model.addAttribute("fallo","Fallo al eliminar la estacion");
			return  "fallo";
		}
	}
}
