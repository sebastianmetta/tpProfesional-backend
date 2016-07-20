package ar.fiuba.tpProfesional.infraestructura.command

import grails.validation.ValidationErrors
import groovy.transform.ToString

@ToString
@grails.validation.Validateable
class HabitacionFiltroCommand {
	
	String estadoCama
	
	static constraints = {
		
	}
	
	
}

