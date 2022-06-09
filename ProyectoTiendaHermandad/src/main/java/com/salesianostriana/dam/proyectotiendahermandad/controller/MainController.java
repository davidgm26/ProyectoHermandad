package com.salesianostriana.dam.proyectotiendahermandad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.proyectotiendahermandad.modelo.Producto;
import com.salesianostriana.dam.proyectotiendahermandad.servicios.ProductoServicio;

/**
 * Esta clase será el controlador para todo lo relacionado con las vistas de
 * acceso público
 * 
 * @author garcia.madav20
 *
 */
@Controller
public class MainController {

	@Autowired
	private ProductoServicio productoServicio;
	
	@GetMapping({ "/public/index", "/" })
	public String mostrarPagPrincipal() {
		return "index";
	}

	@GetMapping("/public/cristo")
	public String mostrarPagCristo() {
		return "cristo";
	}

	@GetMapping("/public/virgen")
	public String mostrarPagVirgen() {
		return "virgen";
	}

	@GetMapping("/public/juntaDeGobierno")
	public String mostrarPagJuntaGobierno() {
		return "juntaDeGobierno";
	}

	@GetMapping("/public/reglas")
	public String mostrarReglas() {
		return "reglas";
	}

	@GetMapping("/public/musica")
	public String mostrarMusica() {
		return "composicionesMusicales";
	}

	@GetMapping("/public/recorrido")
	public String mostrarRecorrido() {
		return "recorrido";
	}

	@GetMapping("/inicioSesion")
	public String mostrarLogin() {
		return "inicioSesion";
	}
		
	/*Método para buscar productos*/
	@GetMapping("/buscar")
	public String buscar(Model model, @RequestParam String nombre) {
		model.addAttribute("productos", productoServicio.findByNombre(nombre));
		return "indexUser";
	}
	
}		



