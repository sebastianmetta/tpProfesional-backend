package ar.fiuba.tpProfesional.paciente.command

import grails.validation.ValidationErrors
import groovy.transform.ToString

@ToString
@grails.validation.Validateable
class PacienteCommand {
	
	String dni
	String nombreYApellido
	Date fechaNacimiento
	String sexo
	String direccion
	String telefono
	String antecedentesFamiliares
	String observaciones
	
	static constraints = {
		
	}
	
	
}

