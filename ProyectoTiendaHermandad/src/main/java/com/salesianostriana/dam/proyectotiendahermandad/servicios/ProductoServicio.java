package com.salesianostriana.dam.proyectotiendahermandad.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectotiendahermandad.modelo.Producto;
import com.salesianostriana.dam.proyectotiendahermandad.repositorio.ProductoRepositorio;
import com.salesianostriana.dam.proyectotiendahermandad.servicio.base.BaseService;

@Service
public class ProductoServicio extends BaseService<Producto, Long, ProductoRepositorio> {

	private ProductoRepositorio ProductoRepositorio;
	
	public ProductoServicio(ProductoRepositorio repo) {
		this.ProductoRepositorio=repo;
	}
	
	/**
	 * Inserta un nuevo alumno
	 * 
	 * @param a el Alumno a insertar
	 * @return El alumno ya insertado (con el Id no vacío).
	 */
	public Producto add(Producto p) { return ProductoRepositorio.save(p); }
	
	
	/**
	 * Edita un alumno, si existe; si no, lo inserta como uno nuevo.
	 * @param a
	 * @return
	 */
	public Producto edit(Producto p) { return ProductoRepositorio.save(p); }

	/**
	 * Elimina el alumno
	 * 
	 * @param a
	 */
	public void delete(Producto p) { ProductoRepositorio.delete(p); }
	
	/**
	 * Elimina a un alumno por su Id
	 * @param id
	 */
	public void delete(long id) { ProductoRepositorio.deleteById(id); }
	
	/**
	 * Devuelve todos los alumnos
	 * @return
	 */
	public List<Producto> findAll() { return ProductoRepositorio.findAll(); }
	
	
	/**
	 * Devuelve un alumno en base a su Id
	 * @param id
	 * @return el alumno encontrado o <code>null</code>
	 */
	public Producto findById(long id) {
		return ProductoRepositorio.findById(id).orElse(null);
	}

}
