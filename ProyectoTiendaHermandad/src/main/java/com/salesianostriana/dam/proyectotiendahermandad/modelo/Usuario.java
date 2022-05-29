package com.salesianostriana.dam.proyectotiendahermandad.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

	private String username,role,password,nombre;
	
}
