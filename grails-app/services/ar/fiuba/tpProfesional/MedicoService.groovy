package ar.fiuba.tpProfesional

import grails.transaction.Transactional
import ar.fiuba.tpProfesional.estadoPaciente.command.AguardandoAltaCommand;
import ar.fiuba.tpProfesional.estadoPaciente.command.InterconsultaCommand
import ar.fiuba.tpProfesional.estadoPaciente.command.PostQuirurjicoCommand
import ar.fiuba.tpProfesional.estadoPaciente.command.RegistroEstudioPendienteDeRetiroCommand
import ar.fiuba.tpProfesional.estadoPaciente.command.RetirarEstudioCommand
import ar.fiuba.tpProfesional.paciente.Diagnostico
import ar.fiuba.tpProfesional.paciente.Paciente
import ar.fiuba.tpProfesional.paciente.command.DiagnosticoCommand
import ar.fiuba.tpProfesional.paciente.command.SolicitudEstudioCommand
import ar.fiuba.tpProfesional.paciente.estado.AguardandoAlta
import ar.fiuba.tpProfesional.paciente.estado.EstudioPendienteDeRetiro
import ar.fiuba.tpProfesional.paciente.estado.EstudioRetirado
import ar.fiuba.tpProfesional.paciente.estado.EstudioSolicitado
import ar.fiuba.tpProfesional.paciente.estado.Interconsulta
import ar.fiuba.tpProfesional.paciente.estado.PostQuirurjico;
import ar.fiuba.tpProfesional.usuario.Medico

class MedicoService {

	/**
	 * Crea y almacena un diagnostico.
	 */
	@Transactional
	Diagnostico createDiagnostico(DiagnosticoCommand command) {
		Medico medico = Medico.findById(command.getIdMedico())
		Paciente paciente = Paciente.findById(command.getIdPaciente())
		Date fechaDiagnostico = DateUtils.stringToDate(command.getFechaDiagnostico(),DateUtils.DD_MM_YYYY)

		return medico.diagnosticarPaciente(
				paciente,
				fechaDiagnostico,
				command.getDiagnostico(),
				command.getObservaciones())
	}

	/**
	 * Crea y almacena un estudio para un paciente.
	 */
	@Transactional
	EstudioSolicitado solicitarEstudioPaciente(SolicitudEstudioCommand command) {
		Medico medico = Medico.findById(command.getIdMedicoSolicitante())
		Paciente paciente = Paciente.findById(command.getIdPaciente())
		return  medico.solicitarEstudioPaciente(paciente, command.getEstudio(), command.getObservaciones())
	}

	/**
	 * Registra un estudio pendiente de retiro para un paciente.
	 */
	@Transactional
	EstudioPendienteDeRetiro registrarEstudioPendienteDeRetiroPaciente(RegistroEstudioPendienteDeRetiroCommand command) {
		Medico medico = Medico.findById(command.getIdMedicoResponsable())
		Paciente paciente = Paciente.findById(command.getIdPaciente())
		Date fecha = DateUtils.stringToDate(command.getFecha(),DateUtils.DD_MM_YYYY)

		return  medico.registrarEstudioPendienteDeRetiroPaciente(
				paciente, fecha, command.getDecripcion(), command.getObservaciones(), command.getLugarDeRetiro())
	}

	/**
	 * Registra el retiro de un estudio para un paciente.
	 */
	@Transactional
	EstudioRetirado retirarEstudioPaciente(RetirarEstudioCommand command) {

		Medico medico = Medico.findById(command.getIdMedicoResponsable())
		Paciente paciente = Paciente.findById(command.getIdPaciente())
		Date fecha = DateUtils.stringToDate(command.getFecha(),DateUtils.DD_MM_YYYY)

		return  medico.retirarEstudioPaciente(
				paciente, fecha, command.getDecripcion(), command.getObservaciones())
	}

	/**
	 * Registra una interconsulta para un paciente.
	 */
	@Transactional
	Interconsulta registrarInterconsulta(InterconsultaCommand command) {
		Paciente paciente = Paciente.findById(command.getIdPaciente())
		Medico medicoSolicitante = Medico.findById(command.getIdMedicoSolicitante())
		Medico medicoConsultado = Medico.findById(command.getIdMedicoConsultado())
		Date fecha = DateUtils.stringToDate(command.getFecha(),DateUtils.DD_MM_YYYY)

		return  medicoSolicitante.registrarInterconsulta(
				paciente,
				medicoConsultado,
				fecha,
				command.getDecripcion(),
				command.getObservaciones(),
				command.getResultado(),
				command.getIndicaciones())
	}

	/**
	 * Registra que un paciente esta listo para darlo de alta.
	 */
	@Transactional
	AguardandoAlta registrarAguardandoAlta(AguardandoAltaCommand command) {
		Medico medico = Medico.findById(command.getIdMedicoResponsable())
		Paciente paciente = Paciente.findById(command.getIdPaciente())
		Date fecha = DateUtils.stringToDate(command.getFecha(),DateUtils.DD_MM_YYYY)

		return  medico.registrarAguardandoAltaPaciente(
				paciente, fecha, command.getDecripcion(), command.getObservaciones())
	}

	/**
	 * Registra la operacion de un paciente.
	 */
	@Transactional
	PostQuirurjico registrarPostQuirurjico(PostQuirurjicoCommand command) {
		Medico medico = Medico.findById(command.getIdMedicoResponsable())
		Paciente paciente = Paciente.findById(command.getIdPaciente())
		Date fecha = DateUtils.stringToDate(command.getFecha(),DateUtils.DD_MM_YYYY)

		return  medico.registrarPostQuirurjico(
				paciente,
				fecha,
				command.getDecripcion(),
				command.getObservaciones(),
				command.getResultadoOperacion())
	}
}
