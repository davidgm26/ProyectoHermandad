package com.salesianostriana.dam.proyectotiendahermandad.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectotiendahermandad.servicios.ProductoServicio;

@Controller
@RequestMapping ("/user")
public class UserController {

	@Autowired
	private ProductoServicio productoServicio;
	
	
	// Mappings sesión iniciada
	@GetMapping("/indexUsuario")
    public String mostrarTienda(Model model) {
    	model.addAttribute("productos", productoServicio.findAll());
    	return "indexUser";
	
	}
}

