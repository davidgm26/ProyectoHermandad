package com.salesianostriana.dam.proyectotiendahermandad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/public")
@Controller
public class MainController {

	
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
	@GetMapping("/historia")
	public String mostrarHistoria() {
		return "historia";
	}
	
	@GetMapping("/avisolegal")
	public String mostrarAvisoLegal() {
		return "avisoLegal";
	}

	@GetMapping("/inicioSesion")
	public String mostrarLogin() {
		return "inicioSesion";
	}
			
}		



