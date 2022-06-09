package com.salesianostriana.dam.proyectotiendahermandad.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.proyectotiendahermandad.modelo.Producto;


public interface ProductoRepositorio extends JpaRepository<Producto, Long>{

	public  List<Producto> findByNombreContainingIgnoreCase(String nombre);
	
}
