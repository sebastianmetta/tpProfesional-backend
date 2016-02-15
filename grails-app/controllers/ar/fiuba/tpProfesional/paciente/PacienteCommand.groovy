package ar.fiuba.tpProfesional.paciente

import grails.validation.ValidationErrors
import groovy.transform.ToString

@ToString
@grails.validation.Validateable
class PacienteCommand {
	
	String dni
	String nombreYApellido
	String direccion
	String telefono
	String antecedentesFamiliares
	String observaciones
	
	static constraints = {
		dni blank:false, unique:true
		nombreYApellido blank:false
		direccion blank:true, nullable:true
		telefono blank:true, nullable:true
		antecedentesFamiliares blank:true, nullable:true
		observaciones blank:true, nullable:true
	}
	
	
}

