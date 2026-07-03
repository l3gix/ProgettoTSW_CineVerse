const nameOrLastnameErrorMessage = "Questo campo DEVE contenere soltanto lettere";
const emailErrorMessage = "Il campo email nel form deve avere questo formato: username@domain.ext";
const phoneErrorMessage = "Il campo telefono deve avere questo formato ###-#######";
const emptyFieldErrorMessage = "Questo campo non deve essere vuoto";
const passwordErrorMessage ="Questo campo deve contenere 8 caratteri di cui almeno uno maiuscolo";

function validateFormElem(formElem, span, errorMessage){
	if(formElem.checkValidity()){
		formElem.classList.remove("error");
		span.style.color = "black";
		span.innerHTML = "";
		return true;
	}
	formElem.classList.add("error");
	span.style.color = "red";
	if(formElem.validity.valueMissing){
		span.innerHTML = emptyFieldErrorMessage;
	}else{
		span.innerHTML = errorMessage;
	}
	return false;
}	