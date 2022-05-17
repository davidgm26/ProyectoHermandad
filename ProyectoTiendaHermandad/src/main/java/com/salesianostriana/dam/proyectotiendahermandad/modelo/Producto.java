package com.salesianostriana.dam.proyectotiendahermandad.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate fechaDeEntrada;
}
	