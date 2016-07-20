package ar.fiuba.tpProfesional.paciente.command

import groovy.transform.ToString
import ar.fiuba.tpProfesional.paciente.Paciente;
import ar.fiuba.tpProfesional.usuario.Medico

@ToString
@grails.validation.Validateable
class SolicitudEstudioCommand {

	String idPaciente
	String idMedicoSolicitante
	String estudio
	String observaciones

	static constraints = {
		idPaciente blank:false, nullable:false, validator: { value, object ->
			def paciente = Paciente.findById(value)
			if (paciente!=null) {
				return true
			} else {
				return "El paciente con id " + value + " no existe"
			}
		}
		idMedicoSolicitante blank:false, nullable:false, validator: { value, object ->
			def medico = Medico.findById(value)
			if (medico!=null) {
				return true
			} else {
				return "El medico con id " + value + " no existe"
			}
		}
		estudio blank: false, nullable:false
	}
}
