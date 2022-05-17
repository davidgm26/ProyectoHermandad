package com.salesianostriana.dam.proyectohermandadestrella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectohermandadestrella.model.Producto;
import com.salesianostriana.dam.proyectohermandadestrella.servicio.ProductoServicio;

@Controller
public class ProductoController {

	@Autowired
	private ProductoServicio productoServicio;

	public ProductoController(ProductoServicio productoServicio) {
		this.productoServicio = productoServicio;
	}

	// Mappings para las paginas de relleno
	@GetMapping({ "/index", "/" })
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

	@GetMapping("/reglas")
	public String mostrarReglas() {
		return "reglas";
	}

	@GetMapping("/musica")
	public String mostrarMusica() {
		return "composicionesMusicales";
	}

	@GetMapping("/recorrido")
	public String mostrarRecorrido() {
		return "recorrido";
	}

	// Mappings sesión iniciada
	@GetMapping("/inicioSesion")
	public String mostrarLogin() {
		return "inicioSesion";
	}

	@GetMapping("/admin")
	public String listarTodos(Model model) {
		model.addAttribute("productos", productoServicio.findAll());
		return "indexSesion";
	}

	@GetMapping("/nuevo")
	public String mostrarFormulario(Model model) {
		model.addAttribute("producto", new Producto());
		return "form";
	}

	@PostMapping("/nuevo/submit")
	public String procesaFormulario(@ModelAttribute("producto") Producto producto) {
		productoServicio.save(producto);
		return "redirect:/admin";
	}
}
