package com.salesianostriana.dam.proyectotiendahermandad.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.proyectotiendahermandad.servicios.ProductoServicio;
import com.salesianostriana.dam.proyectotiendahermandad.servicios.VentaServicio;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
	private VentaServicio ventaServicio;
	
	// Mappings sesión iniciada
	@GetMapping("/indexUsuario")
    public String mostrarTienda(Model model) {
    	model.addAttribute("productos", productoServicio.findAll());
    	return "indexUser";
	}
	
	/*Método para buscar productos*/
	@GetMapping("/buscar")
	public String buscar(Model model, @RequestParam String nombre) {
		model.addAttribute("productos", productoServicio.findByNombre(nombre));
		return "indexUser";
	}
    
	@GetMapping("/exito")
	public String mostrarExitoVenta() {
		return "exito";
	}
	
	@ModelAttribute ("numPedido")
	public int mostrarNumPedido (){
		return ventaServicio.generarNumPedido();
	}
	@ModelAttribute ("numDias")
	public int mostrarNumDias(){
		return ventaServicio.generarNumDias();
	}
}

