package ar.fiuba.tpProfesional.paciente

import grails.rest.Resource
import groovy.transform.ToString;

@ToString
@Resource(formats=['json', 'xml'])
class Paciente {

	String dni
	String nombreYApellido
	String direccion
	String telefono
	String antecedentesFamiliares
	String observaciones
	
	//PacienteMarshaller -> Indica que atributos del paciente se renderizan en JSON.
	
	//TODO: Agregar internaciones.
	
    static constraints = {
		dni blank:false, unique:true
		nombreYApellido blank:false
    }
	
	def firmarConformidadAlta(){
		//TODO: A implementar.
	}
}
