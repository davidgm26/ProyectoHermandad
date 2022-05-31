package com.salesianostriana.dam.proyectotiendahermandad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.proyectotiendahermandad.servicios.ProductoServicio;

/**
 * Esta clase será el controlador para todo lo relacionado con el usuario adminstrador (ADMIN).
 * @author garcia.madav20
 *
 */


@Controller
public class AdminController {
	
	
	@Autowired
	private ProductoServicio productoServicio;

	public AdminController(ProductoServicio productoServicio) {
		this.productoServicio = productoServicio;
	}
	
	/**
	 * Mapping que nos va a mandar a la lista de productos en la que el administrador podrá 
	 * gestionar el listado de productos de la tienda.
	 * @param model
	 * @return
	 */
	
	@GetMapping("/admin")
public String listarTodos(Model model) {
	model.addAttribute("productos", productoServicio.findAll());
	return "indexSesion";
}


}
