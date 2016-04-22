package ar.fiuba.tpProfesional.paciente

import grails.rest.Resource
import groovy.transform.ToString;

@ToString
@Resource(formats=['json', 'xml']) //PacienteMarshaller -> Indica que atributos del paciente se renderizan en JSON.
class Paciente {

	String dni
	String nombreYApellido
	String sexo
	String direccion
	String telefono
	String antecedentesFamiliares
	String observaciones
	List<InternacionPaciente> internaciones = new ArrayList()
	
	static hasMany = [ internaciones : InternacionPaciente	]
	
    static constraints = {
		dni blank:false, unique:true
		nombreYApellido blank:false
		sexo blank:false, nullable:false
		direccion blank:true, nullable:true
		telefono blank:true, nullable:true
		antecedentesFamiliares blank:true, nullable:true
		observaciones blank:true, nullable:true
    }
	
	def firmarConformidadAlta(){
		//TODO:  implementar.
	}
}
