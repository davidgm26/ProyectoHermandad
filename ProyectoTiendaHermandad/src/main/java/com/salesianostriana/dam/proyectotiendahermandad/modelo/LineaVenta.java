package com.salesianostriana.dam.proyectotiendahermandad.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class LineaVenta {

	@Id
	@GeneratedValue
	private long id;
	
	private Producto p;

	private int ud;
	
	private double total; 
	
	@OneToOne
	private Venta venta;
	
}
