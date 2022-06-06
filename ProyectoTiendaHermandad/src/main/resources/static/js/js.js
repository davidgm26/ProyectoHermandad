document.getElementById("nombre").addEventListener("blur", comprobarNombre);
document.getElementById("pvp").addEventListener("blur", comprobarPrecio);
document.getElementById("descripcion").addEventListener("blur", comprobarDescripcion);
document.getElementById("unidadesStock").addEventListener("blur", comprobarStock);


function revisarFormulario() {

    let resultado = false;

    resultado = comprobarNombre() &&
        comprobarPrecio() &&
        comprobarDescripcion() &&
        comprobarStock();

    formulario.enviar.className = resultado ? "btn btn-success mb-2" : "btn btn-danger mb-2";

    return resultado;
}

function comprobarNombre() {

    let regex = /^[a-zA-Z0-9\_\-]{4,50}$/; // Letras, numeros, guion y guion_bajo
    let nombre = document.getElementById("nombre");

    let resultado = nombre.value != "";

    if (resultado) {
        nombre.nextElementSibling.hidden = true;
    } else {
        nombre.nextElementSibling.hidden = false;
    }

    cambiarApariencia(nombre, resultado)

    return resultado;
}

function comprobarDescripcion() {

    let regex = /^[a-zA-Z0-9\_\-]{4,50}$/; // Letras, numeros, guion y guion_bajo

    let descripcion = formulario.descripcion;

    let resultado = descripcion.value != "";

    if (resultado) {
        descripcion.nextElementSibling.hidden = true;
    } else {
        descripcion.nextElementSibling.hidden = false;
    }

    cambiarApariencia(descripcion, resultado)

    return resultado;
}


function comprobarPrecio() {

    let precio = formulario.pvp;
    let resultado = pvp.value != "" && !isNaN(precio.value) && precio.value > 0;

    if (resultado) {
        pvp.nextElementSibling.hidden = true;
    } else {
        pvp.nextElementSibling.hidden = false;
    }

    cambiarApariencia(pvp, resultado)

    return resultado;

}

function comprobarStock() {

    let unidadesStock = formulario.unidadesStock;
    let resultado = unidadesStock.value != "" && !isNaN(unidadesStock.value) && unidadesStock.value > 0;

    if (resultado) {
        unidadesStock.nextElementSibling.hidden = true;
    } else {
        unidadesStock.nextElementSibling.hidden = false;
    }

    cambiarApariencia(unidadesStock, resultado)

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
