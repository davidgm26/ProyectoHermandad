package com.salesianostriana.dam.proyectotiendahermandad.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.proyectotiendahermandad.servicios.CarritoServicio;
import com.salesianostriana.dam.proyectotiendahermandad.servicios.ProductoServicio;

@Controller
public class UserController {

	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
	private CarritoServicio carritoServicio;
	
	// Mappings sesión iniciada
	@GetMapping("/user/indexUsuario")
    public String mostrarTienda(Model model) {
    	model.addAttribute("productos", productoServicio.findAll());
    	return "indexUser";
	}
	
	/*Método para buscar productos*/
	@GetMapping("/user/buscar")
	public String buscar(Model model, @RequestParam String nombre) {
		model.addAttribute("productos", productoServicio.findByNombre(nombre));
		return "indexUser";
	}
    
}

