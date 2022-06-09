package com.salesianostriana.dam.proyectotiendahermandad.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectotiendahermandad.modelo.LineaVenta;
import com.salesianostriana.dam.proyectotiendahermandad.repositorio.LineaVentaRepositorio;

@Service
public class LineaVentaServicio extends BaseService <LineaVenta, Long, LineaVentaRepositorio>{

	@Autowired
	private LineaVentaRepositorio lineaVentaRepositorio;
	
	public List<LineaVenta> findAll(){
		return lineaVentaRepositorio.findAll();
	}
	
}
