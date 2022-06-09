package com.salesianostriana.dam.proyectotiendahermandad.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectotiendahermandad.modelo.Producto;
import com.salesianostriana.dam.proyectotiendahermandad.repositorio.ProductoRepositorio;

@Service
public class ProductoServicio extends BaseService <Producto, Long, ProductoRepositorio> {

	@Autowired
	private ProductoRepositorio ProductoRepositorio;

	public ProductoServicio(ProductoRepositorio repo) {
		this.ProductoRepositorio = repo;
	}

	/**
	 * Inserta un nuevo producto
	 * 
	 * @param a el producto a insertar
	 * @return El producto ya insertado (con el Id no vacío).
	 */
	public Producto add(Producto p) {
		return ProductoRepositorio.save(p);
	}

	/**
	 * Edita un producto, si existe; si no, lo inserta como uno nuevo.
	 * 
	 * @param a
	 * @return
	 */
	public Producto edit(Producto p) {
		return ProductoRepositorio.save(p);
	}

	/**
	 * Elimina el producto
	 * 
	 * @param a
	 */
	public void delete(Producto p) {
		ProductoRepositorio.delete(p);
	}

	/**
	 * Elimina a un producto por su Id
	 * 
	 * @param id
	 */
	public void delete(long id) {
		ProductoRepositorio.deleteById(id);
	}

	/**
	 * Devuelve todos los productos
	 * 
	 * @return
	 */
	public List<Producto> findAll() {
		return ProductoRepositorio.findAll();
	}

	/**
	 * Devuelve un producto en base a su Id
	 * 
	 * @param id
	 * @return el producto encontrado o <code>null</code>
	 */
	public Producto findById(long id) {
		return ProductoRepositorio.findById(id).orElse(null);
	}
	
	public List<Producto> findByNombre(String nombre) {
		return ProductoRepositorio.findByNombreContainingIgnoreCase(nombre);
	}
}
