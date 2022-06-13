package com.salesianostriana.dam.proyectotiendahermandad.servicios;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectotiendahermandad.modelo.Venta;
import com.salesianostriana.dam.proyectotiendahermandad.repositorio.VentaRepositorio;

@Service
public class VentaServicio extends BaseService<Venta, Long, VentaRepositorio>{

	@Autowired
	private VentaRepositorio ventaRepositorio;
	
		public VentaServicio(VentaRepositorio ventaRepositorio) {
		super();
		this.ventaRepositorio = ventaRepositorio;
	}


	/**
	 * Inserta una nuevo venta
	 * 
	 * @param v La venta a insertar
	 * @return La venta ya insertada (con el Id no vacío).
	 */
	public Venta add(Venta v) { return ventaRepositorio.save(v); }
	
	
	/**
	 * Edita una venta, si existe; si no, la inserta como una nueva.
	 * @param v
	 * @return
	 */
	public Venta edit(Venta v) { return ventaRepositorio.save(v); }

	/**
	 * Elimina la venta
	 * 
	 * @param v
	 */
	public void delete(Venta v) { ventaRepositorio.delete(v); }
	
	/**
	 * Elimina una venta por su Id
	 * @param id
	 */
	public void delete(long id) { ventaRepositorio.deleteById(id); }
	
	/**
	 * Devuelve todas las ventas
	 * @return
	 */
	public List<Venta> findAll() { return ventaRepositorio.findAll(); }
	
	
	/**
	 * Devuelve una venta en base a su Id
	 * @param id
	 * @return La venta encontrada o <code>null</code>
	 */
	public Venta findById(long id) {
		return ventaRepositorio.findById(id).orElse(null);
	}

    public Double calcularMediaTotalVentasSinDescuento() {
    	
	   double sumaTotal = 0;
	   List<Venta>listadoVentas =findAll();
	   for (Venta venta : listadoVentas) {
		sumaTotal+=venta.getTotal();
	}
	   return sumaTotal/listadoVentas.size();
   }
    public Double calcularMediaTotalVentasConDescuento() {
    
	   double sumaTotal = 0;
	   List<Venta>listadoVentas =findAll();
	   for (Venta venta : listadoVentas) {
		sumaTotal+=venta.getTotalConDescuento();
	}
	   return sumaTotal/listadoVentas.size();
   }
    
}



