package com.salesianostriana.dam.proyectohermandad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectohermandad.model.Hermano;
import com.salesianostriana.dam.proyectohermandad.servicios.HermanoServicio;

@Controller
public class HermanoController {

	@Autowired
	private HermanoServicio hermanoServicio;

	public HermanoController(HermanoServicio hermanoServicio) {
		this.hermanoServicio = hermanoServicio;
	}

	@GetMapping("/")
	public String mostrarPagPrincipal() {
		return "index";
	}

	@GetMapping("/cristo")
	public String mostrarPagCristo() {
		return "cristo";
	}

	@GetMapping("/virgen")
	public String mostrarPagVirgen() {
		return "virgen";
	}

	@GetMapping("/juntaDeGobierno")
	public String mostrarPagJuntaGobierno() {
		return "juntaDeGobierno";
	}

	@GetMapping("/inicioSesion")
	public String mostrarLogin() {
		return "inicioSesion";
	}

	@GetMapping("/nuevo")
	public String mostrarFormulario(Model model) {
		model.addAttribute("hermano", new Hermano());
		return "form";
	}
	@GetMapping("/reglas")
	public String mostrarReglas(){
		return "reglas";
	}
	
	@GetMapping("/musica")
	public String mostrarMusica(){
		return "composicionesMusicales";
	}

	@GetMapping("/recorrido")
	public String mostrarRecorrido(){
		return "recorrido";
	}

	@GetMapping("/admin")
	public String mostrarPagPrincipalAdmin() {
		return "indexSesion";
	}

	@GetMapping("/listarHermanos")
	public String listarTodos(Model model) {
		model.addAttribute("lista", hermanoServicio.findAll());
		return "listadoHermanos";
	}

	@PostMapping("/nuevo/submit")
	public String procesarFormulario(@ModelAttribute("hermano") Hermano h) {
		hermanoServicio.add(h);
		hermanoServicio.save(h);
		return "redirect:/listarHermanos";
	}

}
