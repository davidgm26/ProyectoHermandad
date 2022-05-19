package com.salesianostriana.dam.proyectotiendahermandad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	
	@GetMapping("/inicioSesion")
	public String mostrarLogin() {
		return "inicioSesion";
	}
	
	
}
