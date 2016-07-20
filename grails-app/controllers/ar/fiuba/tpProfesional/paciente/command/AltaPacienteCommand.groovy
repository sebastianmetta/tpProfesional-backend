package ar.fiuba.tpProfesional.paciente.command

import java.util.Date;

import ar.fiuba.tpProfesional.DateUtils;
import ar.fiuba.tpProfesional.paciente.MotivoAlta;
import ar.fiuba.tpProfesional.paciente.Paciente;
import ar.fiuba.tpProfesional.usuario.Administrativo;
import grails.validation.ValidationErrors
import groovy.transform.ToString

@ToString
@grails.validation.Validateable
class AltaPacienteCommand {
	
	String idPaciente
	String fechaAlta
	String idAdministrativo
	String motivoAlta
	String indicaciones
	String observaciones
	
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
		idPaciente blank:false, nullable:false, validator: {
			value, object ->
				def paciente = Administrativo.findById(value)
				if (paciente!=null) {
					return true
				} else {
					return "El administrativo con id " + value + " no existe"
				}
			}
		fechaAlta blank:false, nullable:false, validator: {
			value ,object ->
				if (DateUtils.isDate(value, DateUtils.DD_MM_YYYY)) {
					return true
				} else {
					return "Fecha de alta invalida: " + value + ". Se espera una fecha con formato: " + DateUtils.DD_MM_YYYY
				}
		}
		motivoAlta (inList: [MotivoAlta.ALTA_NORMAL.toString(), MotivoAlta.FALLECIMIENTO.toString()])
		indicaciones blank: false, nullable:false
	}
	
	
}

