package com.salesianostriana.dam.proyectotiendahermandad.servicios;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.salesianostriana.dam.proyectotiendahermandad.modelo.Producto;
import com.salesianostriana.dam.proyectotiendahermandad.repositorio.ProductoRepositorio;

@Service
@Scope (value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarritoServicio {
	
	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@Autowired
	private Map<Producto, Integer> productos = new HashMap<>();

	public CarritoServicio(ProductoRepositorio productoRepositorio) {
		this.productoRepositorio = productoRepositorio;
	}
	
	public void aniadirProducto (Producto p) {
		if (productos.containsKey(p)) {
			productos.replace(p, productos.get(p)+1);
			}else {
			productos.put(p, 1);
		}
	}
	
	public void borrarProducto (Producto p) {
        if (productos.containsKey(p)) {
            if (productos.get(p) > 1)
                productos.replace(p, productos.get(p) - 1);
            else if (productos.get(p) == 1) {
                productos.remove(p);
            }
        }
	}
	
    public Map<Producto, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(productos);
    }
	
    
	
	
	

}
