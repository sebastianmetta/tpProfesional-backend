package ar.fiuba.tpProfesional.paciente.command

import grails.validation.ValidationErrors
import groovy.transform.ToString

@ToString
@grails.validation.Validateable
class InternacionPacienteFiltroCommand {

	String idPaciente
	String consultaTodas
	String consultaUltima
	String fechaInternacion
	
	static constraints = {
		idPaciente blank:false
	}
}
