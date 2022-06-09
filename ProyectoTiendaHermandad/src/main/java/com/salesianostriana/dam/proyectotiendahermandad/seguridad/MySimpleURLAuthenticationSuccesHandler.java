package com.salesianostriana.dam.proyectotiendahermandad.seguridad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class MySimpleURLAuthenticationSuccesHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {


		
		String role = authentication.getAuthorities().toArray()[0].toString();

		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

		String targetURL = "";
		
		
		if (role.equalsIgnoreCase("ROLE_USER"))
			
			targetURL = "/user/indexUsuario";
			
			else
			
			targetURL = "/admin";

		redirectStrategy.sendRedirect(request, response, targetURL);

	}

}
