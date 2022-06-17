package com.salesianostriana.dam.proyectotiendahermandad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectotiendahermandad.modelo.LineaVenta;
import com.salesianostriana.dam.proyectotiendahermandad.modelo.Producto;
import com.salesianostriana.dam.proyectotiendahermandad.servicios.LineaVentaServicio;
import com.salesianostriana.dam.proyectotiendahermandad.servicios.ProductoServicio;
import com.salesianostriana.dam.proyectotiendahermandad.servicios.VentaServicio;

/**
 * Esta clase será el controlador para todo lo relacionado con el usuario
 * adminstrador (ADMIN).
 * 
 * @author garcia.madav20
 *
 */

@Controller
public class AdminController {

	@Autowired
	private ProductoServicio productoServicio;

	@Autowired
	private VentaServicio ventaServicio;

	@Autowired
	private LineaVentaServicio lineaVentaServicio;

	public AdminController(ProductoServicio productoServicio) {
		this.productoServicio = productoServicio;
	}

	/**
	 * Mapping que nos va a mandar a la lista de productos en la que el
	 * administrador podrá gestionar el listado de productos de la tienda.
	 * 
	 * @param model
	 * @return
	 */
	/*
	 * Los mappings deben de tener el rol al que pertenecen delante es decir ->
	 * /admin/lo que sea
	 */

	@GetMapping({ "/admin/producto", "/admin" })
	public String listarTodos(Model model) {
		model.addAttribute("productos", productoServicio.findAll());
		return "indexSesion";
	}

	/**
	 * Crea un nuevo producto al que le pasaremos los atributos a traves del
	 * formulario
	 * 
	 * @param model
	 * @return HTML del formulario
	 */
	@GetMapping("/admin/nuevo")
	public String mostrarFormulario(Model model) {
		model.addAttribute("producto", new Producto());
		return "form";
	}

	/**
	 * Mapping para guardar un producto dentro de la lista de productos
	 * 
	 * @param producto
	 * @param model
	 * @return Nos redirige a la pagina principal del administrador (tabla con todos
	 *         los productos)
	 */
	@PostMapping("/admin/nuevo/submit")
	public String procesaFormulario(@ModelAttribute("producto") Producto producto, Model model) {

		model.addAttribute("producto", producto);
		productoServicio.save(producto);
		return "redirect:/admin/producto";
	}

	/**
	 * Mapping para borrar un producto de la lista, lo buscamos por id y lo
	 * borramos, comprobando que no esté en ninguna linea
	 * 
	 * @param id
	 * @return Nos redirige a la pagina principal del administrador (tabla con todos
	 *         los productos)
	 */
	@GetMapping("/admin/borrarProducto/{id}")
	public String borrarProducto(@PathVariable("id") Long id, Model model) {
		productoServicio.deleteById(id);
		return "redirect:/admin/producto";
	}


	@GetMapping("/admin/editar/{id}")
	public String editarProducto(@PathVariable("id") Long id, Model model) {

		Producto producto = productoServicio.findById(id);

		if (producto != null) {
			model.addAttribute("producto", producto);
			return "form";
		} else {
			return "redirect:/admin/producto";
		}

	}

	@GetMapping("/admin/historicoVenta")
	public String listarTodas(Model model) {
		model.addAttribute("ventas", ventaServicio.findAll());
		return "historicoVentas";
	}

	@GetMapping("/admin/borrarVenta/{id}")
	public String borrarVenta(@PathVariable("id") Long id, Model model) {
		ventaServicio.deleteById(id);
		return "redirect:/admin/historicoVenta";
	}

	@GetMapping("/admin/historicoVenta/detallesVenta/{id}")
	public String listarLineasUnaVenta(@PathVariable("id") Long id, Model model) {
		model.addAttribute("lineasVenta", ventaServicio.findById(id).getLineaVenta());
		return "detalleVenta";
	}

	@GetMapping("/admin/borrarLineaVenta/{id}")
	public String borrarLineaVenta(@PathVariable("id") Long id, Model model) {
		LineaVenta lv = lineaVentaServicio.findById(id);
		long id_venta = lv.getVenta().getId();
		lineaVentaServicio.borrarUnaUdProductoDeLineaVenta(id);
		if (lv.getUd() == 0) {
			lineaVentaServicio.delete(lv);
		}
		return "redirect:/admin/historicoVenta/detallesVenta/" + id_venta;
	}

	@ModelAttribute("mediaVentas")
	public Double calcularMediaTotalVentas() {
		return ventaServicio.calcularMediaTotalVentasSinDescuento();
	}

	@ModelAttribute("mediaVentasDescuento")
	public Double calcularMediaTotalVentasConDescuento() {
		return ventaServicio.calcularMediaTotalVentasConDescuento();
	}

	@ModelAttribute("numPedido")
	public int imprimirNumPedido() {
		return ventaServicio.generarNumPedido();
	}

	@ModelAttribute("numDias")
	public int imprimirNumDias() {
		return ventaServicio.generarNumDias();
	}

}
