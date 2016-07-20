package ar.fiuba.tpProfesional.estadoPaciente.command

import grails.validation.ValidationErrors
import groovy.transform.ToString
import ar.fiuba.tpProfesional.DateUtils
import ar.fiuba.tpProfesional.paciente.Paciente;
import ar.fiuba.tpProfesional.usuario.Medico

@ToString
@grails.validation.Validateable
class InterconsultaCommand {

	String idPaciente
	String idMedicoSolicitante
	String idMedicoConsultado
	String fecha
	String decripcion
	String observaciones
	String resultado
	String indicaciones
	
	static constraints = {
		fecha blank:false, nullable:false, validator: {
			value ,object ->
				if (DateUtils.isDate(value, DateUtils.DD_MM_YYYY)) {
					return true
				} else {
					return "Fecha invalida: " + value + ". Se espera una fecha con formato: " + DateUtils.DD_MM_YYYY
				}
		}
		decripcion  blank:false, nullable:false
		resultado  blank:false, nullable:false
		indicaciones  blank:false, nullable:false
		idPaciente blank:false, nullable:false, validator: { value, object ->
			def paciente = Paciente.findById(value)
			if (paciente!=null) {
				return true
			} else {
				return "El paciente con id " + value + " no existe"
			}
		}
		idMedicoSolicitante blank:false, nullable:false, validator: {
			value, object ->
				def medico = Medico.findById(value)
				if (medico!=null) {
					return true
				} else {
					return "El medico solicitante con id " + value + " no existe"
				}
			}
		idMedicoConsultado blank:false, nullable:false, validator: {
			value, object ->
				def medico = Medico.findById(value)
				if (medico!=null) {
					return true
				} else {
					return "El medico consultado con id " + value + " no existe"
				}
			}
	}
	
	
}

