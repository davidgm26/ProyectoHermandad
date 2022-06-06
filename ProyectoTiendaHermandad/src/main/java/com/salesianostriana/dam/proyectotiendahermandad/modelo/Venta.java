package com.salesianostriana.dam.proyectotiendahermandad.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {

	
	private LocalDate fechaVenta;
	@ManyToOne
	private List<LineaVenta>lineas;
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	
}
