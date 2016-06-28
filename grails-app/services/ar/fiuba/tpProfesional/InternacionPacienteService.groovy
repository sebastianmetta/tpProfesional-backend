package ar.fiuba.tpProfesional

import grails.transaction.Transactional
import ar.fiuba.tpProfesional.infraestructura.Cama
import ar.fiuba.tpProfesional.paciente.InternacionPaciente
import ar.fiuba.tpProfesional.paciente.InternacionPacienteCommand
import ar.fiuba.tpProfesional.paciente.OrigenInternacion
import ar.fiuba.tpProfesional.paciente.Paciente

class InternacionPacienteService {

	/**
	 * Crea y almacena una nueva Internacion de paciente.
	 * @param command
	 * @return
	 */
	@Transactional
	InternacionPaciente createInternacionPaciente(InternacionPacienteCommand command) {
		Paciente paciente = Paciente.findById(Integer.valueOf(command.getIdPaciente()))
		paciente.getInternaciones().each { InternacionPaciente eachInternacion ->
			if (DateUtils.dateEqualsWithoutTime(
				eachInternacion.getFechaInternacion(), 
				DateUtils.stringToDate(command.getFechaInternacion(), DateUtils.DD_MM_YYYY))) {
				throw new Exception("La fecha de internacion corresponde a una internación previa. No puede crearse una internación con una misma fecha que una existente.")
				}
		}
		
		InternacionPaciente internacionPaciente = new InternacionPaciente()
		Cama cama = Cama.findById(Integer.valueOf(command.getIdCama()))
		internacionPaciente.getCamas().add(cama)
		Date fechaInternacion = DateUtils.stringToDate(command.getFechaInternacion(),DateUtils.DD_MM_YYYY)
		internacionPaciente.setFechaInternacion(fechaInternacion)
		internacionPaciente.setOrigenInternacion(OrigenInternacion.valueOf(command.getOrigenInternacion()))
		internacionPaciente.save(flush:true, failOnError: true)
		
		paciente.getInternaciones().add(internacionPaciente)
		paciente.save(flush:true, failOnError: true)
		
		return internacionPaciente
	}	
}
