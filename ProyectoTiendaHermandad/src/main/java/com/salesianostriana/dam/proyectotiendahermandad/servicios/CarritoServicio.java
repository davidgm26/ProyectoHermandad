package com.salesianostriana.dam.proyectotiendahermandad.servicios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.salesianostriana.dam.proyectotiendahermandad.modelo.LineaVenta;
import com.salesianostriana.dam.proyectotiendahermandad.modelo.Producto;
import com.salesianostriana.dam.proyectotiendahermandad.modelo.Venta;
import com.salesianostriana.dam.proyectotiendahermandad.repositorio.ProductoRepositorio;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarritoServicio {

	@Autowired
	private ProductoRepositorio productoRepositorio;

	@Autowired
	private VentaServicio ventaServicio;
	
	@Autowired
	private LineaVentaServicio lineaVentaServicio;

	private Map<Producto, Integer> productos = new HashMap<>();

	public CarritoServicio(ProductoRepositorio productoRepositorio) {
		this.productoRepositorio = productoRepositorio;
	}

	public void aniadirProducto(Producto p) {
		if (productos.containsKey(p)) {
			productos.replace(p, productos.get(p) + 1);// Ya programamos como "mayores" y podemos poner algún número
														// directamente en el código
		} else {
			productos.put(p, 1);
		}
	}

	/**
	 * Método que elimina un producto del carrito. Si en el carrito la cantidad de
	 * dicho producto es más de uno baja la cantidad en una unidad y si es uno
	 * elimina el producto entero
	 * 
	 * @param producto
	 */

	public void borrarProducto(Producto p) {
		if (productos.containsKey(p)) {
			if (productos.get(p) > 1)
				productos.replace(p, productos.get(p) - 1);
			else if (productos.get(p) == 1) {
				productos.remove(p);
			}
		}
	}

	/**
	 * @return unmodifiable Copia del carrito no modificable, solo vista
	 */

	public Map<Producto, Integer> obtenerProductosCarrito() {
		return Collections.unmodifiableMap(productos);
	}

	/**
	 * Se debería tener un método para revisar si hay suficiente cantidad de
	 * productos en el stock (si se gestiona el stock) no desarrollada en este
	 * ejemplo, puesto que no se gestiona ni el stock ni cantidad de producto es
	 * atributo de la clase POJO Producto, esto debería estar gestionado en línea de
	 * pedido, pedido, etc. Se podría tener una excepción propia (del estilo
	 * NotEnoughProductsInStockException) y gestionarlo
	 */

	public void checkout() {
		Venta v = new Venta();
		v.setFechaDeVenta(LocalDate.now());
		LineaVenta lv;
		double total = 0;
		//ventaServicio.save(v);
		for (Map.Entry<Producto, Integer> lineaVenta : productos.entrySet()) {
			
				 LineaVenta.builder()
						   .nombre(lineaVenta.getKey().getNombre())
					       .ud(lineaVenta.getValue())
	         		       .subTotal(lineaVenta.getKey().getPvp()*lineaVenta.getValue())
				           .build().aniadirAVenta(v);

		}
		
		if (!productos.isEmpty()) {
			ventaServicio.save(v);
		}
		productoRepositorio.flush();
		productos.clear();

		}
	
}

	/*
	public double calcularDescuento () {
		double total=0;
			if (calcularTotalVenta()<50) {
			
		} else if(calcularTotalVenta()>50){
			
			total=calcularTotalVenta()-10;
			
		} else if(calcularTotalVenta()>100) {
			
			total=calcularTotalVenta()-20;
			
		} else if(calcularTotalVenta()>150) {
			
			total=calcularTotalVenta()-30;
			
		} else if(calcularTotalVenta()>200) {
			
			total=calcularTotalVenta()-50;
		}
		return total;
	}
*/	

