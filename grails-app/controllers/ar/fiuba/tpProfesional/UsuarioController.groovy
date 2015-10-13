package ar.fiuba.tpProfesional

import org.springframework.beans.BeanUtils;

import ar.fiuba.tpProfesional.security.Authority;
import ar.fiuba.tpProfesional.security.User;
import ar.fiuba.tpProfesional.security.UserAuthority;
import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils;
import grails.rest.RestfulController

class UsuarioController extends RestfulController {

	def list() {
		def result = User.getAll().collect {
			[
				id: it.id,
				username: it.username,
				email: it.email,
				direccion: it.direccion,
			]
		}
		render(contentType: 'text/json', text: result as JSON)
		
	}

}

