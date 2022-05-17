package com.salesianostriana.dam.proyectohermandadestrella.model;

import java.time.LocalDate;

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
 *         Esta clase será la que nos va a ayudar a la hora de crear los productos
 *         dentro de la aplicación para ello vamos a guardar los datos básicos
 *         que debe tener un producto.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Producto {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private double pvp;
		
	private String nombre,descripcion;

	private LocalDate fechaDeEntrada;
}
	