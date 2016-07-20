package ar.fiuba.tpProfesional.infraestructura.command

import ar.fiuba.tpProfesional.paciente.Paciente;
import groovy.transform.ToString;

@ToString
@grails.validation.Validateable
class CamaCommand {
	
	String numero
	String paciente
	String descripcion
	String observaciones
	
	static constraints = {
	}
	
}
