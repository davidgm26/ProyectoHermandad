package com.salesianostriana.dam.proyectotiendahermandad.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectotiendahermandad.modelo.Producto;
import com.salesianostriana.dam.proyectotiendahermandad.servicios.CarritoServicio;
import com.salesianostriana.dam.proyectotiendahermandad.servicios.ProductoServicio;
import com.salesianostriana.dam.proyectotiendahermandad.servicios.VentaServicio;

@Controller
@RequestMapping("/user")
public class CarritoController {
	


	@Autowired
	private CarritoServicio carritoServicio;
	
	
	@Autowired
	private ProductoServicio productoServicio;
	
	
    	public CarritoController(CarritoServicio carritoServicio, ProductoServicio productoServicio,VentaServicio ventaServicio) {
		super();
		this.carritoServicio = carritoServicio;
		this.productoServicio = productoServicio;
	}
    
    @GetMapping ("/carrito")
    public String showCarrito (Model model) {
    	
    	if (model.addAttribute("productos",carritoServicio.obtenerProductosCarrito()) == null)
    		return "redirect:/user/indexUsuario";
    	return "carrito";
    }

    @GetMapping ("/productoACarrito/{id}")
    public String productoACarrito (@PathVariable("id") Long id, Model model) {
    	
    	carritoServicio.aniadirProducto(productoServicio.findById(id));
    	
    	    	return "redirect:/user/indexUsuario";
    }
    
    @GetMapping("/borrarProducto/{id}")
    public String removeProductFromCart(@PathVariable("id") Long id) {
    	carritoServicio.borrarProducto(productoServicio.findById(id));
        return "redirect:/carrito";
    }
    
    @ModelAttribute("total_carrito")
    public Double totalCarrito () {
    	
    	Map <Producto,Integer> carrito=carritoServicio.obtenerProductosCarrito();
    	double total=0.0;
    	if (carrito !=null) {
        	for (Producto p: carrito.keySet()) {
        		total+=p.getPvp()*carrito.get(p);
        	}
        	
        	
        	return total;
    	}
    	
    	return 0.0;
    }
    
    @ModelAttribute("total_carritodescuento")
    public Double totalCarritoDescuento() {
    	
    return carritoServicio.calcularDescuento(carritoServicio.precioEspecialCorrecion(totalCarrito()));
    }
    
    @ModelAttribute("mediaVenta")
    public Double calcularMediaVenta() {
    return carritoServicio.calcularMediaUnaVenta(totalCarritoDescuento());
    } 
    
    @PostMapping("/checkout")
    public String checkout(){
    	  	carritoServicio.checkout();
    return "redirect:/user/indexUsuario";
    }
    
    @GetMapping ("/borrarTodo")
    public String borrarCarritoCompleto() {
    	carritoServicio.borrarCarritoCompleto();
    	return "redirect:/carrito";
    }
    
}
	
	


	

