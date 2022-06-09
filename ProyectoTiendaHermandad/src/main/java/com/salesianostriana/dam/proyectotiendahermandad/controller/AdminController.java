package com.salesianostriana.dam.proyectotiendahermandad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	private LineaVentaServicio lineaventaServicio;


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

	@GetMapping({"/admin/producto","/admin"})
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
		return "redirect:/admin";
	}

	/**
	 * Mapping para borrar un producto de la lista, lo buscamos por id y lo
	 * borramos.
	 * 
	 * @param id
	 * @param model
	 * @return Nos redirige a la pagina principal del administrador (tabla con todos
	 *         los productos)
	 */
	@GetMapping("/admin/borrarProducto/{id}")
	public String borrarProducto(@PathVariable("id") Long id, Model model) {
		productoServicio.deleteById(id);
		return "redirect:/admin";
	}

	@GetMapping("/admin/editar/{id}")
	public String editarProducto(@PathVariable("id") Long id, Model model) {

		Producto producto = productoServicio.findById(id);

		if (producto != null) {
			model.addAttribute("producto", producto);
			return "form";
		} else {
			return "redirect:/admin";
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
	public String listarLineasUnaVenta(@PathVariable("id") Long id ,Model model) {
		model.addAttribute("lineasVenta",ventaServicio.findById(id).getLineaVenta());
		return "detalleVenta";
	}

}
