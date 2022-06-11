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

	public void checkout() {
		Venta v = new Venta();
		v.setFechaDeVenta(LocalDate.now());
		LineaVenta lv;
		double total = 0;

		if (!productos.isEmpty()) {
			ventaServicio.save(v);
			for (Map.Entry<Producto, Integer> lineaVenta : productos.entrySet()) {

				lv = LineaVenta.builder().producto(lineaVenta.getKey()).ud(lineaVenta.getValue())
						.subTotal(lineaVenta.getKey().getPvp() * lineaVenta.getValue()).build();

				lv.aniadirAVenta(v);

				lineaVenta.getKey().setUnidadesStock(lineaVenta.getKey().getUnidadesStock() - 1);

				lineaVentaServicio.save(lv);

				total += (lineaVenta.getKey().getPvp() * lineaVenta.getValue());

			}

			v.setTotal(total);
			v.setTotalConDescuento(precioEspecialCorrecion(calcularDescuento(total)));
			ventaServicio.save(v);
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