package ar.fiuba.tpProfesional.infraestructura

import grails.validation.ValidationErrors
import groovy.transform.ToString

@ToString
@grails.validation.Validateable
class CamaFiltroCommand {
	
	String estado
	String idPaciente
	
	static constraints = {
		
	}
	
	
}

