package ar.fiuba.tpProfesional

import org.springframework.http.HttpStatus;

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.rest.RestfulController
import grails.validation.Validateable
import grails.validation.ValidationErrors;
import ar.fiuba.tpProfesional.security.Authority
import ar.fiuba.tpProfesional.security.RegistroCommand;
import ar.fiuba.tpProfesional.security.User
import ar.fiuba.tpProfesional.security.UserAuthority

class RegistroController extends RestfulController {
	def save(){
		
		log.info("Procesando solicitud de registro: " + request.JSON)
		
		def command
		try {			
			command = new RegistroCommand(request.JSON)
		} catch(Exception e) {
			response.status = 422
			def error = [description: 'JSON inválido.']
			render error as JSON
			return
		}
		
		command.validate()
		if (command.hasErrors()) {
			response.status = 422
			render command.errors as JSON
			return

		} else {
			Authority newUserAuthority = Authority.findById(command.getRoleId())
			if (!newUserAuthority) {
				response.status = 422
				render g.message(code: 'validator.role.notExists') as JSON
				return
			}
			User newUser = new User()

			newUser.setUsername(command.getUsername())
			newUser.setPassword(command.getPassword())
			newUser.setEmail(command.getEmail())
			newUser.setDni(command.getDni())
			newUser.setNombreYApellido(command.getNombreYApellido())
			newUser.setTelefono(command.getTelefono())

			newUser.setEnabled(true)
			newUser.setAccountExpired(false)
			newUser.setPasswordExpired(false)

			if (newUser.hasErrors()){
				response.status = 422
				render newUser.errors
				return
			}
			newUser.save(flush: true)

			def uA = UserAuthority.create newUser, newUserAuthority
			if (!uA.validate()) {
				response.status = 422
				render uA.errors
				return
			} else {
				uA.save(flush: true)
			}
			response.status = 200
			//Para un recurso 'comun' se deberia devolver su url, ej: /user/1 pero en este caso ver que conviene.
			render newUser as JSON
		}
	}

	def notAllowed() {
		// Return Method not allowed HTTP status code.
		render status: HttpStatus.METHOD_NOT_ALLOWED.value()
	}
	
}

