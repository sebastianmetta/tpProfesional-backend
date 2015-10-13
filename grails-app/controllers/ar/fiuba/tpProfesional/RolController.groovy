package ar.fiuba.tpProfesional

import org.springframework.beans.BeanUtils;

import ar.fiuba.tpProfesional.security.Authority;
import ar.fiuba.tpProfesional.security.User;
import ar.fiuba.tpProfesional.security.UserAuthority;
import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils;
import grails.rest.RestfulController

class RolController extends RestfulController {

	def list() {
		def result = Authority.getAll().collect {
			[
				id: it.id,
				descripcion: it.authority,
			]
		}
		render(contentType: 'text/json', text: result as JSON)
		
	}

}

