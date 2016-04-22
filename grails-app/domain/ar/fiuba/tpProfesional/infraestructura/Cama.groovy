package ar.fiuba.tpProfesional.infraestructura

import grails.rest.Resource;
import groovy.transform.ToString;
import ar.fiuba.tpProfesional.paciente.Paciente;

@ToString
@Resource(formats=['json', 'xml'])
class Cama {

	int numero
	Paciente paciente
	String descripcion
	String observaciones
	
	//Entidad débil.
	static belongsTo = Habitacion
	
    static constraints = {
		numero blank:false, nullable:false, unique:true
		paciente blank:false, nullable:true
    }
}
