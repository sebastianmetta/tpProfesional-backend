package ar.fiuba.tpProfesional

import ar.fiuba.tpProfesional.security.Authority;
import ar.fiuba.tpProfesional.security.Authority.AuthorityType;
import ar.fiuba.tpProfesional.security.RegistroCommand;
import ar.fiuba.tpProfesional.usuario.Administrativo;
import ar.fiuba.tpProfesional.usuario.Enfermero
import ar.fiuba.tpProfesional.usuario.Medico
import grails.transaction.Transactional


@Transactional
class UsuarioService {

    def createUser(int roleId) {
		Authority userAuthority = Authority.findById(roleId)
		def newUser
		if (userAuthority.getAuthority().equals(AuthorityType.ENFERMERO)) {
			newUser = new Enfermero()()
		} else if (userAuthority.getAuthority().equals(AuthorityType.ADMINISTRATIVO)) {
			newUser = new Administrativo()
		} else {
			newUser = new Medico()
		}
		return newUser
    }
}
