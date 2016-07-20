package ar.fiuba.tpProfesional.paciente.command

import groovy.transform.ToString
import ar.fiuba.tpProfesional.DateUtils
import ar.fiuba.tpProfesional.infraestructura.Cama
import ar.fiuba.tpProfesional.paciente.OrigenInternacion;
import ar.fiuba.tpProfesional.paciente.Paciente;
import ar.fiuba.tpProfesional.usuario.Administrativo;

@ToString
@grails.validation.Validateable
class InternacionPacienteCommand {

	String idPaciente
	String fechaInternacion
	String origenInternacion
	String idAdministrativo
	String patologia
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
		origenInternacion (inList: [OrigenInternacion.GUARDIA.toString(), OrigenInternacion.PROGRAMADA.toString()])
		idAdministrativo blank:false, nullable:false, validator: {
			value, object ->
				def administrativo = Administrativo.findById(value)
				if (administrativo!=null) {
					return true
				} else {
					return "El usuario administrativo con id " + value + " no existe"
				}
			}
		patologia blank: false, nullable:false
		idCama blank:false, nullable:false, validator: {
			value, object ->
				def cama = Cama.findById(Integer.valueOf(value))
				if (cama!=null) {
					return true
				} else {
					return "La Cama con id " + value + " no existe"
				}
			}
		}
	
}
