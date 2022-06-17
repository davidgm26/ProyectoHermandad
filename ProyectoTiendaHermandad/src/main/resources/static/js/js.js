//document.getElementById("formulario").addEventListener("submit",revisarFormulario);
document.getElementById("nombre").addEventListener("blur", comprobarNombre);
document.getElementById("pvp").addEventListener("blur", comprobarPrecio);
document.getElementById("descripcion").addEventListener("blur", comprobarDescripcion);
document.getElementById("unidadesStock").addEventListener("blur", comprobarStock);

AOS.init();


function revisarFormulario() {

    let resultado = false;

    resultado = comprobarNombre() &&
        comprobarPrecio() &&
        comprobarDescripcion() &&
        comprobarStock() &&
        evaluarFecha();

    formulario.enviar.className = resultado ? "btn btn-success mb-2" : "btn btn-danger mb-2";

    return resultado;
}

function comprobarNombre() {


    let nombre = document.getElementById("nombre");

    let resultado = nombre.value != "";

        nombre.nextElementSibling.hidden = resultado;

        cambiarApariencia(nombre, resultado)

    return resultado;
}

function comprobarDescripcion() {

    let descripcion = formulario.descripcion;

    let resultado = descripcion.value != "";

    descripcion.nextElementSibling.hidden = resultado;

    cambiarApariencia(descripcion, resultado)

    return resultado;
}


function comprobarPrecio() {

    let precio = formulario.pvp;
    let resultado = pvp.value != "" && !isNaN(precio.value) && precio.value > 0;


    pvp.nextElementSibling.hidden = resultado;


    cambiarApariencia(pvp, resultado)

    return resultado;

}

function comprobarStock() {

    let unidadesStock = formulario.unidadesStock;
    let resultado = unidadesStock.value != "" && !isNaN(unidadesStock.value) && unidadesStock.value > 0;

    unidadesStock.parentNode.nextElementSibling.hidden = resultado;

    cambiarApariencia(unidadesStock, resultado)

    return resultado;
}
function evaluarFecha() {

    let fechaDeEntrada = document.getElementById ("fecha");
	let resultado = fechaDeEntrada.value!="" && Date.parse(fechaDeEntrada.value) < Date.now();

       
    fechaDeEntrada.parentNode.nextElementSibling.hidden = resultado;
        
	cambiarApariencia(fechaDeEntrada, resultado);
	return resultado;
}


function cambiarApariencia(campo, estado) {
    if (estado) {
        campo.classList.remove("border-danger");
        campo.classList.add("border-success");
        campo.parentNode.nextElementSibling.style.visibility = 'hidden';
    }
    else {
        campo.classList.remove("border-success");
        campo.classList.add("border-danger");
        campo.parentNode.nextElementSibling.style.visibility = 'visible';
    }

}
