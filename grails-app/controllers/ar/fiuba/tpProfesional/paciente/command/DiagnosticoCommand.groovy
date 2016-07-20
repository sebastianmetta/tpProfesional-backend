package ar.fiuba.tpProfesional.paciente.command

import grails.validation.ValidationErrors
import groovy.transform.ToString
import ar.fiuba.tpProfesional.DateUtils
import ar.fiuba.tpProfesional.paciente.Paciente;
import ar.fiuba.tpProfesional.usuario.Medico

@ToString
@grails.validation.Validateable
class DiagnosticoCommand {

	String fechaDiagnostico
	String idPaciente
	String idMedico
	String diagnostico
	String observaciones
	
	static constraints = {
		fechaDiagnostico blank:false, nullable:false, validator: {
			value ,object ->
				if (DateUtils.isDate(value, DateUtils.DD_MM_YYYY)) {
					return true
				} else {
					return "Fecha de diagnostico invalida: " + value + ". Se espera una fecha con formato: " + DateUtils.DD_MM_YYYY
				}
		}
		idPaciente blank:false, nullable:false, validator: {
			value, object ->
				def paciente = Paciente.findById(value)
				if (paciente!=null) {
					return true
				} else {
					return "El paciente con id " + value + " no existe"
				}
			}
		idMedico blank:false, nullable:false, validator: {
			value, object ->
				def medico = Medico.findById(value)
				if (medico!=null) {
					return true
				} else {
					return "El medico con id " + value + " no existe"
				}
			}
		diagnostico blank:false, nullable:false
	}
	
	
}

