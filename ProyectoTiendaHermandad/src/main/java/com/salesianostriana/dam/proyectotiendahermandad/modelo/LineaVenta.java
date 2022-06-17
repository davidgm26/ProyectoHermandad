package com.salesianostriana.dam.proyectotiendahermandad.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import org.hibernate.annotations.OnDelete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineaVenta {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Producto producto;
	
	private int ud;
	
	private double subTotal; 
	
	@ManyToOne
	private Venta venta;
	
	
	
	//Helper Venta
	
	public void aniadirAVenta(Venta venta) {
		this.venta = venta;
		venta.getLineaVenta().add(this);
	}
	
	public void borrarDeUnaVenta(Venta venta) {
		venta.getLineaVenta().remove(this);
		this.venta = null;
	}
	


}
