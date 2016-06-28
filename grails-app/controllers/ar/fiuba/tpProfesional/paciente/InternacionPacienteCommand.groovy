package ar.fiuba.tpProfesional.paciente

import groovy.transform.ToString
import ar.fiuba.tpProfesional.DateUtils
import ar.fiuba.tpProfesional.infraestructura.Cama

@ToString
@grails.validation.Validateable
class InternacionPacienteCommand {

	String idPaciente
	String fechaInternacion
	String origenInternacion
	String idCama
	
	static constraints = {
		idPaciente blank:false, nullable:false, validator: {
			value, object ->
				def paciente = Paciente.findById(value)
				if (paciente!=null) {
					return true
				} else {
					return "El paciente con id " + value + " no existe"
				}
			}
		fechaInternacion blank:false, nullable:false, validator: {
			value ,object ->
				if (DateUtils.isDate(value, DateUtils.DD_MM_YYYY)) {
					return true
				} else {
					return "Fecha de internacion invalida: " + value + ". Se espera una fecha con formato: " + DateUtils.DD_MM_YYYY
				}
		}
		//TODO: Seguir desde aca.
		origenInternacion (inList: [OrigenInternacion.GUARDIA.toString(), OrigenInternacion.PROGRAMADA.toString()])
		idCama blank:false, nullable:false, validator: {
			value, object ->
				def paciente = Cama.findById(value)
				if (paciente!=null) {
					return true
				} else {
					return "La Cama con id " + value + " no existe"
				}
			}
		}
	
}
