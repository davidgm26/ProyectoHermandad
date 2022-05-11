package com.salesianostriana.dam.proyectohermandad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.salesianostriana.dam.proyectohermandad.model.Hermano;
import com.salesianostriana.dam.proyectohermandad.servicios.HermanoServicio;

@Controller
public class HermanoController {

	
	private HermanoServicio hermanoServicio;

	public HermanoController(HermanoServicio hermanoServicio) {
		this.hermanoServicio = hermanoServicio;
	}
	
	@GetMapping({"/listarHermanos"})
	public String listarTodos(Model model) {
		model.addAttribute("lista", hermanoServicio.findAll());
		return "listadoHermanos";
	}
	
	@GetMapping("/altaHermano")
	public String mostrarFormulario(Model model) {
		model.addAttribute("hermano", new Hermano());
		return "formulario";
	}

	@PostMapping("/nuevo/submit")
	public String procesarFormulario(@ModelAttribute("hermano") Hermano h) {
		hermanoServicio.add(h);
		return "redirect:/listarHermanos";//Podría ser también return "redirect:/list
	}
}
