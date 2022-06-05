package com.salesianostriana.dam.proyectotiendahermandad.servicios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

		List<LineaVenta> lineasVenta = new ArrayList<LineaVenta>();
		Venta v = Venta.builder().build();
	}
}
