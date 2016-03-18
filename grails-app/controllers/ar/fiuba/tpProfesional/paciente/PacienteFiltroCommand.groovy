package ar.fiuba.tpProfesional.paciente

import grails.validation.ValidationErrors
import groovy.transform.ToString

@ToString
@grails.validation.Validateable
class PacienteFiltroCommand {
	
	String dni
	String nombreYApellido
	
	static constraints = {
		
	}
	
	
}

