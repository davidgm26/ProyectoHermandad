package com.salesianostriana.dam.proyectohermandadestrella.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author garcia.madav20
 * 
 *         Esta clase será la que nos va a ayudar a la hora de crear hermanos
 *         dentro de la aplicación para ello vamos a guardar los datos básicos
 *         que debe tener un hermano.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Hermano {

	private String nombre, apellidos,direccion, edad, fechaNacimiento, numTelefono;

	private String DNI;
	
	private int numHermano;
	@Id
	@GeneratedValue
	private Long id;

}