package ar.fiuba.tpProfesional.security

import grails.validation.ValidationErrors;

@grails.validation.Validateable
class RegistroCommand {
	String username
	String email
	String password
	String dni
	String nombreYApellido
	String telefono
	int roleId
	
	static constraints = {
		username blank: false, validator: { value, command ->
			if (value) {
				if (User.findByUsername(value)) {
					//TODO: Ver como obtener el mensaje desde properties: return 'validator.username.exists'
					return 'El nombre de usuario ya se encuentra en uso, por favor seleccione otro'
				}
			}
		}
		email blank: false, email: true
		password blank: false
		dni blank: false
		roleId blank: false
		nombreYApellido blank: false
	}
}
