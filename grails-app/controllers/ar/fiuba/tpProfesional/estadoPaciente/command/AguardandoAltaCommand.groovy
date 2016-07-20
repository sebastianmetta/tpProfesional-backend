package ar.fiuba.tpProfesional.estadoPaciente.command

import grails.validation.ValidationErrors
import groovy.transform.ToString
import ar.fiuba.tpProfesional.DateUtils
import ar.fiuba.tpProfesional.paciente.Paciente;
import ar.fiuba.tpProfesional.usuario.Medico

@ToString
@grails.validation.Validateable
class AguardandoAltaCommand {

	String idPaciente
	String idMedicoResponsable
	String fecha
	String decripcion
	String observaciones
	
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
		idPaciente blank:false, nullable:false, validator: { value, object ->
			def paciente = Paciente.findById(value)
			if (paciente!=null) {
				return true
			} else {
				return "El paciente con id " + value + " no existe"
			}
		}
		idMedicoResponsable blank:false, nullable:false, validator: {
			value, object ->
				def medico = Medico.findById(value)
				if (medico!=null) {
					return true
				} else {
					return "El medico responsable con id " + value + " no existe"
				}
			}
	}
	
	
}

