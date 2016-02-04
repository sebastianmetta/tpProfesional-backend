package ar.fiuba.tpProfesional.paciente

import grails.validation.ValidationErrors;

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
	}
}

