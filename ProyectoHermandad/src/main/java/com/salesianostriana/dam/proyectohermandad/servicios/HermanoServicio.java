package com.salesianostriana.dam.proyectohermandad.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectohermandad.model.Hermano;
import com.salesianostriana.dam.proyectohermandad.repositorio.HermanoRepositorio;
import com.salesianostriana.dam.proyectohermandad.servicios.base.BaseService;

@Service
public class HermanoServicio extends BaseService<Hermano, Long, HermanoRepositorio> {

	private HermanoRepositorio hermanoRepositorio;
	
	public HermanoServicio(HermanoRepositorio repo) {
		this.hermanoRepositorio=repo;
	}
	
	/**
	 * Inserta un nuevo alumno
	 * 
	 * @param a el Alumno a insertar
	 * @return El alumno ya insertado (con el Id no vacío).
	 */
	public Hermano add(Hermano h) { return hermanoRepositorio.save(h); }
	
	
	/**
	 * Edita un alumno, si existe; si no, lo inserta como uno nuevo.
	 * @param a
	 * @return
	 */
	public Hermano edit(Hermano h) { return hermanoRepositorio.save(h); }

	/**
	 * Elimina el alumno
	 * 
	 * @param a
	 */
	public void delete(Hermano h) { hermanoRepositorio.delete(h); }
	
	/**
	 * Elimina a un alumno por su Id
	 * @param id
	 */
	public void delete(long id) { hermanoRepositorio.deleteById(id); }
	
	/**
	 * Devuelve todos los alumnos
	 * @return
	 */
	public List<Hermano> findAll() { return hermanoRepositorio.findAll(); }
	
	
	/**
	 * Devuelve un alumno en base a su Id
	 * @param id
	 * @return el alumno encontrado o <code>null</code>
	 */
	public Hermano findById(long id) {
		return hermanoRepositorio.findById(id).orElse(null);
	}

}
