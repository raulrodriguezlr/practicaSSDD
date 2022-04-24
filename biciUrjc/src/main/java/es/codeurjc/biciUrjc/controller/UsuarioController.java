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
	
	// Menu principal del modulo de gestion de estaciones
	@GetMapping("/gestionUsuarios")
	public String lists(Model model) {
		List<Usuario> usuarios= userInterface.findAll();
		model.addAttribute("usuario", usuarios);
		return "Gestion_Usuarios/modulo_gestion_usuarios";
	}
	
	// Mostrar detalles de un usuario
	@GetMapping("/gestionUsuarios/{id}")
	public String detalleUsuario(Model model,@PathVariable (value="id")long id){ 
		//lo que ponemos en el modelo es lo que queremos que nos llegue como respuesta
		Optional<Usuario> user = Uservice.findOne(id);
		if(user.isPresent()) {
			Usuario usuario = user.get();
			model.addAttribute("usuarios", usuario);
			return "Gestion_Usuarios/detallesUsuario";
		}else {
			model.addAttribute("fallo","Fallo al mostrar los detalles del usuarios");
			return "fallo";
		}
	}
	
	// Menu de agregar usuario
	@GetMapping("/agregarUsuarios")
	public String agregarUsuario(Model model) {	
		return "Gestion_Usuarios/agregarUsuario";
	}
	
	// Agregar usuario
	@GetMapping("/agregarUsuario")
	public String agregarUsuario(Model model,@RequestParam String nombre, @RequestParam String apellido, @RequestParam String contraseña) {
		Usuario user = new Usuario(nombre,apellido,contraseña);
		Uservice.save(user);
		return "redirect:/gestionUsuarios";
	}
	
	// Menu para editar usuario
	@GetMapping("/gestionUsuarios/editar/{id}")
	public String editarUsuarios(Model model,@PathVariable (value="id")long id){ 
		Optional<Usuario> user = Uservice.findOne(id);
		if(user.isPresent()) {
			Usuario usuario = user.get();
			model.addAttribute("Usuario", usuario);
			return "Gestion_Usuarios/editarUsuario";
		}else {
			model.addAttribute("fallo","Fallo al editar el usuario");
			return "fallo";
		}
	}
	
	// Editar nombre
	@GetMapping("/editarUsuario/nombre/{id}")
	public String editarNombre (Model model,@PathVariable (value="id")long id,@RequestParam Optional<String> nombre){
		Optional<Usuario> user = Uservice.findOne(id);
		if(user.isPresent()) {
			String nombreNuevo = nombre.get();
			Uservice.editarNombre(id, nombreNuevo);
			return "redirect:/gestionUsuarios/{id}";
		}
		else {
			model.addAttribute("fallo","Fallo al cambiar el nombre");
			return  "fallo";
		}
		
	}
	
	// Editar apellido
	@GetMapping("/editarUsuario/apellido/{id}")
	public String editarApellido (Model model,@PathVariable (value="id")long id,@RequestParam Optional<String> apellido){
		Optional<Usuario> user = Uservice.findOne(id);
		if(user.isPresent()) {
			String apellidoNuevo = apellido.get();
			Uservice.editarApellido(id, apellidoNuevo);
			return "redirect:/gestionUsuarios/{id}";
			
		}
		else {
			model.addAttribute("fallo","Fallo al cambiar el apellido");
			return  "fallo";
		}
		
	}
	
	// Editar contraseña
	@GetMapping("/editarUsuario/contraseña/{id}")
	public String editarContraseña (Model model,@PathVariable (value="id")long id,@RequestParam Optional<String> contraseña){
		Optional<Usuario> user = Uservice.findOne(id);
		if(user.isPresent()) {
			String contraseñaNuevo = contraseña.get();
			Uservice.editarContraseña(id, contraseñaNuevo);
			return "redirect:/gestionUsuarios/{id}";
			
		}
		else {
			model.addAttribute("fallo","Fallo al cambiar la contraseña");
			return  "fallo";
		}
		
	}
	
	// Dar de baja a un usuario
	@GetMapping("/editarUsuario/baja/{id}")
	public String editarInactivo (Model model,@PathVariable (value="id")long id){
		Optional<Usuario> user = Uservice.findOne(id);
		if(user.isPresent()) {		
			Uservice.editarActivo(id, "INACTIVO");
			return "redirect:/gestionUsuarios";
			
		}
		else {
			model.addAttribute("fallo","Fallo al cambiar el estado");
			return  "fallo";
		}
		
	}
	
	// Dar de alta a un usuario
	@GetMapping("/editarUsuario/alta/{id}")
	public String editarActivo (Model model,@PathVariable (value="id")long id){
		Optional<Usuario> user = Uservice.findOne(id);
		if(user.isPresent()) {
			Uservice.editarActivo(id, "ACTIVO");
			return "redirect:/gestionUsuarios";
		}
		else {
			model.addAttribute("fallo","Fallo al cambiar el estado");
			return  "fallo";
		}
	}
	
}
