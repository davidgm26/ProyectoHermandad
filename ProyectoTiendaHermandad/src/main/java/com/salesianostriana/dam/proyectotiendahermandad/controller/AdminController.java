package com.salesianostriana.dam.proyectotiendahermandad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.proyectotiendahermandad.servicios.ProductoServicio;

@Controller
public class AdminController {
	
	
	@Autowired
	private ProductoServicio productoServicio;

	public AdminController(ProductoServicio productoServicio) {
		this.productoServicio = productoServicio;
	}
	
	@GetMapping("/admin")
public String listarTodos(Model model) {
	model.addAttribute("productos", productoServicio.findAll());
	return "indexSesion";
}


}
