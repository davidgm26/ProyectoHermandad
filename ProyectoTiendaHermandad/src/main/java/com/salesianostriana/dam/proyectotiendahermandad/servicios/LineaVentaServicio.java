package com.salesianostriana.dam.proyectotiendahermandad.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectotiendahermandad.modelo.LineaVenta;
import com.salesianostriana.dam.proyectotiendahermandad.modelo.Producto;
import com.salesianostriana.dam.proyectotiendahermandad.repositorio.LineaVentaRepositorio;

@Service
public class LineaVentaServicio extends BaseService <LineaVenta, Long, LineaVentaRepositorio>{

	@Autowired
	private LineaVentaRepositorio lineaVentaRepositorio;
	
	public List<LineaVenta> findAll(){
		return lineaVentaRepositorio.findAll();
	}
	
	public LineaVenta edit(LineaVenta lv) {
		return lineaVentaRepositorio.save(lv);
	}
	
	/**
	 * Borra una unidad de la linea de venta por su id
	 * 
	 * @param id de la línea de venta
	 * 
	 */
	public LineaVenta borrarUnProductoDeLineaVenta(long id) {
		LineaVenta lv = findById(id);
		lv.setUd(lv.getUd()-1);
		return edit(lv);
	}
	

	
}
