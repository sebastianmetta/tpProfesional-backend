package ar.fiuba.tpProfesional

import grails.transaction.Transactional
import ar.fiuba.tpProfesional.infraestructura.Cama
import ar.fiuba.tpProfesional.paciente.AltaPaciente;
import ar.fiuba.tpProfesional.paciente.InternacionPaciente
import ar.fiuba.tpProfesional.paciente.MotivoAlta;
import ar.fiuba.tpProfesional.paciente.OrigenInternacion
import ar.fiuba.tpProfesional.paciente.Paciente
import ar.fiuba.tpProfesional.paciente.command.AltaPacienteCommand
import ar.fiuba.tpProfesional.paciente.command.InternacionPacienteCommand
import ar.fiuba.tpProfesional.usuario.Administrativo

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
		if (cama.getPaciente()!=null) {
			throw new Exception("La cama " + cama.toString() + " ya se encuentra ocupada.")
		}
		cama.setPaciente(paciente)
		cama.save(flush:true)
		internacionPaciente.getCamas().add(cama)
		Date fechaInternacion = DateUtils.stringToDate(command.getFechaInternacion(),DateUtils.DD_MM_YYYY)
		internacionPaciente.setFechaInternacion(fechaInternacion)
		internacionPaciente.setOrigenInternacion(OrigenInternacion.valueOf(command.getOrigenInternacion()))
		internacionPaciente.setPatologia(command.getPatologia())
		internacionPaciente.save(flush:true, failOnError: true)

		paciente.getInternaciones().add(internacionPaciente)
		paciente.save(flush:true, failOnError: true)

		Administrativo administrativo = Administrativo.findById(command.getIdAdministrativo())
		administrativo.internarPaciente(paciente)

		return internacionPaciente
	}
	
	/**
	 * Da de alta un paciente
	 */
	@Transactional
	AltaPaciente darDeAltaPaciente(AltaPacienteCommand command) {
		Paciente paciente = Paciente.findById(Integer.valueOf(command.getIdPaciente()))
		Date fechaAlta = DateUtils.stringToDate(command.getFechaAlta(),DateUtils.DD_MM_YYYY)
		MotivoAlta motivoAlta = MotivoAlta.valueOf(command.getMotivoAlta())
		Administrativo administrativo = Administrativo.findById(command.getIdAdministrativo())
		
		return administrativo.darDeAltaPaciente(
			paciente, 
			motivoAlta, 
			fechaAlta,
			command.getIndicaciones(),
			command.getObservaciones())
	}
}
