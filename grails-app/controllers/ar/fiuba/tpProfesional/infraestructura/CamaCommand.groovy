package ar.fiuba.tpProfesional.infraestructura

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
