package ar.fiuba.tpProfesional.paciente

import grails.rest.Resource
import ar.fiuba.tpProfesional.infraestructura.Cama
import ar.fiuba.tpProfesional.paciente.estado.EstadoPaciente

@Resource(formats=['json', 'xml'])
class InternacionPaciente {

	Date fechaInternacion
	OrigenInternacion origenInternacion
	String patologia
	List estadosActuales = new ArrayList()
	List estadosAnteriores = new ArrayList()
	Diagnostico diagnostico
	AltaPaciente altaPaciente
	List<Cama> camas = new ArrayList()
	
	//Entidad débil.
	static belongsTo = Paciente
	
	static hasMany = [
		estadosActuales: EstadoPaciente,
		estadosAnteriores: EstadoPaciente,
		camas: Cama
		]
	
    static constraints = {
		fechaInternacion blank:false, nullable:false
		origenInternacion blank:false, nullable:false
		patologia nullable:true
		diagnostico nullable:true
		altaPaciente nullable:true
    }
}
