package com.salesianostriana.dam.proyectotiendahermandad.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
		
	private String nombre,descripcion,imagen;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaDeEntrada;

	private int unidadesStock;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "producto",fetch = FetchType.EAGER)
	@Autowired
	private List<LineaVenta> lineasVenta;


	
}
	