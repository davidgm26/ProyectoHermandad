package com.salesianostriana.dam.proyectotiendahermandad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectotiendahermandad.modelo.Producto;
import com.salesianostriana.dam.proyectotiendahermandad.servicios.ProductoServicio;

/**
 * Esta clase será el controlador para todo lo relacionado con la gestión del producto.
 * @author garcia.madav20
 *
 */
@Controller
public class ProductoController {

	@Autowired
	private ProductoServicio productoServicio;

	public ProductoController(ProductoServicio productoServicio) {
		this.productoServicio = productoServicio;
	}

	// Mappings sesión iniciada
	
/**
 * Crea un nuevo producto al que le pasaremos los atributos a traves del formulario
 * @param model
 * @return HTML del formulario
 */
	@GetMapping("/nuevo")
	public String mostrarFormulario(Model model) {
		model.addAttribute("producto", new Producto());
		return "form";
	}
/**
 * Mapping para guardar un producto dentro de la lista de productos
 * @param producto
 * @param model
 * @return Nos redirige a la pagina principal del administrador (tabla con todos los productos)
 */
	@PostMapping("/nuevo/submit")
	public String procesaFormulario(@ModelAttribute("producto") Producto producto,Model model) {
		model.addAttribute("producto", producto);
		productoServicio.save(producto);
		return "redirect:/admin";
	}
/**
 * Mapping para borrar un producto de la lista, lo buscamos por id y lo borramos.
 * @param id
 * @param model
 * @return Nos redirige a la pagina principal del administrador (tabla con todos los productos)
 */
    @GetMapping ("/borrarProducto/{id}")
    public String productoACarrito (@PathVariable("id") Long id, Model model) {
    	productoServicio.deleteById(id);
    return "redirect:/admin";
    }
    
	@GetMapping("/editar/{id}")
	public String editarProducto(@PathVariable("id") Long id, Model model) {

		Producto producto = productoServicio.findById(id);

		if (producto != null) {
			model.addAttribute("producto", producto);
			return "form";
		} else {
			return "redirect:/admin";
		}

	}
	
	

}

