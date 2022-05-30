package com.salesianostriana.dam.proyectotiendahermandad.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "index.html";
    }
    
	@GetMapping("/inicioSesion")
	public String privateIndex(Model model, @AuthenticationPrincipal UserDetails user) {
		
		model.addAttribute("usuario", user.getUsername());
		
		return "inicioSesion";
	}

}
