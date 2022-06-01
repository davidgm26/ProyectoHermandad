		document.getElementById("nombre").addEventListener("blur", comprobarNombre);
		document.getElementById("pvp").addEventListener("blur", comprobarPrecio);

		function revisarFormulario() {

			let resultado = false;

			//en las siguientes llamadas encadenadas con && hay que tener en cuenta que en el momento 
			//en el que una de las llamadas devuelva false, ya no se realizarán las siguientes
			resultado = comprobarNombre() &&
				comprobarPrecio(); /*&&
				comprobarCorreo() &&
				comprobarContrasena() &&
				comprobarEdad();

*/
			formulario.enviar.className = resultado ? "btn btn-success mb-2" : "btn btn-danger mb-2";

			return resultado; //lo tengo a false para que nunca envíe el formulario, cuando esto entrara en producción, habría que poner return resultado;
		}

		function comprobarNombre() {

			let regex = /^[a-zA-Z0-9\_\-]{4,50}$/; // Letras, numeros, guion y guion_bajo

			let nombre = formulario.nombre.value;
			let resultado = nombre.value != "" && regex.test(nombre.value);

			if (resultado) {
				nombre.nextElementSibling.hidden = true;
			} else {
				nombre.nextElementSibling.hidden = false;
			}

			cambiarApariencia(pvp, resultado)

			return resultado;
		}

		function comprobarPrecio() {
			let pvp = formulario.pvp.value;

			return resultado = pvp > 0;

			cambiarApariencia(pvp, resultado)
		}

		function cambiarApariencia(campo, estado) {
			if (estado) {
				campo.classList.remove("border-danger");
				campo.classList.add("border-success");
				campo.parentNode.nextElementSibling.style.visibility = 'hidden';
				//parentNode es el nodo HTML padre que contiene a un nodo. NextElementSibling es el siguiente elemento hermano. 
				//por tanto, al desplazarme por el árbol estoy accediendo al siguiente hermano al nodo padre 
				//(ver el código HTML para comprender el recorrido que estamos haciendo)
			}
			else {
				campo.classList.remove("border-success");
				campo.classList.add("border-danger");
				campo.parentNode.nextElementSibling.style.visibility = 'visible';
			}

		}
s