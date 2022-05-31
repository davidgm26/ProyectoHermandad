package com.salesianostriana.dam.proyectotiendahermandad.modelo;
/**
 * Clase que nos servira para crear los dos usuarios tanto Admin como user
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

	/**
	 * @param username nombre que va a tener el usuario
	 * @param role rol que va a tener un usuario
	 * @param password contraseña que tendrá el usuario
	 * @param nombre nombre de la persona a la que pertenece el usuario
	*/
	
	private String username,role,password,nombre;
	
}
