package ar.fiuba.tpProfesional.security

import grails.rest.Resource;

//@Resource(uri='api/users', formats=['json', 'xml'])
class User {

	transient springSecurityService

	String username
	String password
	String email
	String dni
	String nombreYApellido
	String telefono
	
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		email blank: true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Authority> getAuthorities() {
		UserAuthority.findAllByUser(this).collect { it.authority } as Set
	}
	
	/** getter for 1 authority by user architecture*/
	Authority getAuthority() {
		UserAuthority.findAllByUser(this).collect { it.authority }.first()
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
