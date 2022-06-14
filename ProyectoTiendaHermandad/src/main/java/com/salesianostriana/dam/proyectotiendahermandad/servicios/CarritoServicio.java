package com.salesianostriana.dam.proyectotiendahermandad.servicios;

import java.time.LocalDate;
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
	private ProductoServicio productoServicio;

	@Autowired
	private VentaServicio ventaServicio;

	@Autowired
	private LineaVentaServicio lineaVentaServicio;

	private Map<Producto, Integer> productos = new HashMap<>();


	/**
	 * Metodo que recibe el producto y lo añade al carrito
	 * @param p
	 */
	public void aniadirProducto(Producto p) {
		if (productos.containsKey(p)) {
			productos.replace(p, productos.get(p) + 1);
		} else {
			productos.put(p, 1);
		}
	}

	/**
	 * Método que elimina un producto del carrito. Si en el carrito la cantidad de
	 * dicho producto es más de uno quita 1 y si es 1
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


	public Map<Producto, Integer> obtenerProductosCarrito() {
		return Collections.unmodifiableMap(productos);
	}

	
	public void checkout() {
		Venta v = new Venta();
		v.setFechaDeVenta(LocalDate.now());
		LineaVenta lv;
		double total = 0;

		if (!productos.isEmpty()) {
			ventaServicio.save(v);
			for (Map.Entry<Producto, Integer> lineaVenta : productos.entrySet()) {

				/*
				 * En este primer paso del carrito entramos al mapa y sacamos el producto (clave) y la cantidad que nos han comprado del producto
				 * en cuestión (value), con ello montamos una linea de pedido.
				 */
				
				lv = LineaVenta.builder()
							   .producto(lineaVenta.getKey())
							   .ud(lineaVenta.getValue())
							   .subTotal(lineaVenta.getKey().getPvp() * lineaVenta.getValue())
							   .build();

				//La linea de venta se añade a la venta gracias al metodo helper que tenemos.
				lv.aniadirAVenta(v);

				
				//Bajamos el Stock segun la cantidad pedida por el usuario en el carrito 
				productoServicio.bajarStock(lineaVenta.getKey().getId(),lineaVenta.getValue());
				//Guardamos la linea de venta dentro de la BBDD
				lineaVentaServicio.save(lv);
				
				//Se calcula el total de una linea de venta, y gracias al bucle obtendremos el valor final de nuestra venta
				total += (lineaVenta.getKey().getPvp() * lineaVenta.getValue());

			}

			//Guardamos el valor total de la venta sin descuento.
			v.setTotal(total);
			//Guardamos el valor de la venta con el descuento incluido.
			v.setTotalConDescuento(precioEspecialCorrecion(calcularDescuento(total)));
			//Se guarda la venta dentro de la base de datos.
			ventaServicio.save(v);
			//Se limpia el carrito para la próxima venta
			productos.clear();

		}

		productos.clear();

	}

	public void borrarCarritoCompleto() {
		productos.clear();
	}

	public double calcularDescuento(double total) {

		if (total < 50) {

		} else if (total >= 50 && total < 100) {

			total -= 10;

		} else if (total >= 100 && total < 150) {

			total -= 20;

		} else if (total >= 150 && total < 200) {

			total -= 30;

		} else if (total >= 200) {

			total -= 50;
		}
		return total;

	}

	public double calcularMediaUnaVenta(double total) {
		return total / productos.size();
	}

	public double precioEspecialCorrecion(double totalVenta) {
		
		LocalDate hoy = LocalDate.now();
		LocalDate fechaDescuento = LocalDate.of(2022, 06, 17);
		LocalDate fechaFinalDescuento = LocalDate.of(2022, 06, 20);

		if (hoy.compareTo(fechaDescuento) > 0
				&& hoy.compareTo(fechaFinalDescuento) < 0)
			totalVenta = totalVenta - ((totalVenta * 10) / 100);

		return totalVenta;
	}
}