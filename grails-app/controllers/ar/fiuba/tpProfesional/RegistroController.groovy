package ar.fiuba.tpProfesional

import org.springframework.beans.BeanUtils;

import ar.fiuba.tpProfesional.security.Authority;
import ar.fiuba.tpProfesional.security.User;
import ar.fiuba.tpProfesional.security.UserAuthority;
import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils;
import grails.rest.RestfulController

class RegistroController extends RestfulController {
	
	def save(RegistracionCommand registroCommand){
		if (registroCommand.hasErrors()) {
			response.status = 422
			render registroCommand.errors
			return
		} else {
			Authority newUserAuthority = Authority.findById(registroCommand.getRoleId())
			if (!newUserAuthority) {
				response.status = 422
				render g.message(code: 'registrarCommand.role.notExists')
				return
			}
			User newUser = new User()
			newUser.setUsername(registroCommand.getUsername())
			newUser.setPassword(registroCommand.getPassword())
			newUser.setDireccion(registroCommand.getDireccion())
			newUser.setEmail(registroCommand.getEmail())
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
			response.status = 201
					//Para un recurso 'comun' se deberia devolver su url, ej: /user/1 pero en este caso ver que conviene.
					render newUser as JSON
		}
	}

}

class RegistracionCommand {
	//TODO: Agregar todos los campos del usuario necesarios.
	String username
	String email
	String password
	String direccion
	int roleId

	def grailsApplication

	static constraints = {
		username blank: false, validator: { value, command ->
			if (value) {
				def User = command.grailsApplication.getDomainClass(
						SpringSecurityUtils.securityConfig.userLookup.userDomainClassName).clazz
				if (User.findByUsername(value)) {
					return 'registerCommand.username.unique'
				}
			}
		}
		email blank: false, email: true
		direccion blank: false
		password blank: false
		roleId blank: false
	}

}
