package ar.fiuba.tpProfesional.usuario

import ar.fiuba.tpProfesional.paciente.AltaPaciente
import ar.fiuba.tpProfesional.paciente.InternacionPaciente
import ar.fiuba.tpProfesional.paciente.MotivoAlta
import ar.fiuba.tpProfesional.paciente.Paciente
import ar.fiuba.tpProfesional.paciente.estado.Internado
import ar.fiuba.tpProfesional.security.User


public class Administrativo extends User {

	def internarPaciente(Paciente paciente) {

		InternacionPaciente internacion = paciente.getLastInternacion()

		Internado estadoInternado = new Internado(internadoPor: this)
		estadoInternado.setFecha(new Date())
		estadoInternado.setDescripcion("Internacion de paciente DNI " + paciente.getDni())
		estadoInternado.setObservaciones("-")
		estadoInternado.save(flush:true)

		internacion.getEstadosActuales().add(estadoInternado)
		internacion.save(flush: true)

		paciente.save(flush: true)

	}

	def darDeAltaPaciente(Paciente paciente, MotivoAlta motivoAlta, Date fechaAlta, String indicaciones, String observaciones) {
		AltaPaciente altaPaciente = new AltaPaciente(
				fechaAlta: fechaAlta,
				administrativo: this,
				motivoAlta: motivoAlta,
				indicaciones: indicaciones,
				observaciones : observaciones).save(flush:true)

		InternacionPaciente internacion = paciente.getLastInternacion()
		internacion.setAltaPaciente(altaPaciente)

		internacion.save(flush:true)
		paciente.save(flush:true)
		
		return altaPaciente
	}
}
