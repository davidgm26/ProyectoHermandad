package com.salesianostriana.dam.proyectotiendahermandad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Esta clase será el controlador para todo lo relacionado con las vistas de
 * acceso público
 * 
 * @author garcia.madav20
 *
 */
@Controller
public class MainController {

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

	/*
	 * @GetMapping("/inicioSesion") public String mostrarLogin() { return
	 * "inicioSesion"; }
	 */

}
