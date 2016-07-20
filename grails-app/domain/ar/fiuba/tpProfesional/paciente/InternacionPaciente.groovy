package ar.fiuba.tpProfesional.paciente

import grails.rest.Resource
import ar.fiuba.tpProfesional.infraestructura.Cama
import ar.fiuba.tpProfesional.paciente.estado.EstadoPaciente

@Resource(formats=['json', 'xml'])
class InternacionPaciente {

	Date fechaInternacion = new Date()
	OrigenInternacion origenInternacion
	String patologia
	List<EstadoPaciente> estadosActuales = new ArrayList()
	List<EstadoPaciente> estadosAnteriores = new ArrayList()
	List<Diagnostico> diagnosticos = new ArrayList()
	AltaPaciente altaPaciente
	List<Cama> camas = new ArrayList()
	
	//Entidad débil.
	static belongsTo = Paciente
	
	static hasMany = [
		estadosActuales: EstadoPaciente,
		estadosAnteriores: EstadoPaciente,
		diagnosticos: Diagnostico, 
		camas: Cama
		]
	
    static constraints = {
		fechaInternacion blank:false, nullable:false
		origenInternacion blank:false, nullable:false
		patologia nullable:true
		altaPaciente nullable:true
    }
}
