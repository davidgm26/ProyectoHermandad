package com.salesianostriana.dam.proyectotiendahermandad.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
<<<<<<< HEAD
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
=======
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
>>>>>>> c1ec5d44c9f1a27bca66bc60efa5d39550f07928

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
<<<<<<< HEAD
import lombok.NoArgsConstructor;
=======
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
>>>>>>> c1ec5d44c9f1a27bca66bc60efa5d39550f07928

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {
<<<<<<< HEAD

	
	private LocalDate fechaVenta;
	@ManyToOne
	private List<LineaVenta>lineas;
=======
>>>>>>> c1ec5d44c9f1a27bca66bc60efa5d39550f07928
	
	@Id
	@GeneratedValue
	private Long id;
	
<<<<<<< HEAD
	
	
=======
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaDeVenta;
	
	private double total;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany (mappedBy = "venta",fetch = FetchType.EAGER)
	private  List<LineaVenta> lineaVenta;

>>>>>>> c1ec5d44c9f1a27bca66bc60efa5d39550f07928
}
