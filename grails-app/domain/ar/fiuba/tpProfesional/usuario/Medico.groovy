package ar.fiuba.tpProfesional.usuario

import ar.fiuba.tpProfesional.infraestructura.Cama
import ar.fiuba.tpProfesional.infraestructura.Servicio
import ar.fiuba.tpProfesional.paciente.Diagnostico
import ar.fiuba.tpProfesional.paciente.InternacionPaciente
import ar.fiuba.tpProfesional.paciente.Paciente
import ar.fiuba.tpProfesional.paciente.estado.AguardandoAlta;
import ar.fiuba.tpProfesional.paciente.estado.EstudioPendienteDeRetiro
import ar.fiuba.tpProfesional.paciente.estado.EstudioRetirado
import ar.fiuba.tpProfesional.paciente.estado.EstudioSolicitado
import ar.fiuba.tpProfesional.paciente.estado.Interconsulta
import ar.fiuba.tpProfesional.paciente.estado.PostQuirurjico;
import ar.fiuba.tpProfesional.security.User

class Medico extends User {

	List<Cama> camas = new ArrayList()
	ClasificacionMedico clasificacion
	Servicio servicio

	static hasMany = [camas: Cama]

	static constraints = {
		clasificacion blank:false, nullable:false
		servicio blank:false, nullable:false
	}

	Diagnostico diagnosticarPaciente(Paciente paciente, Date fechaDiagnostico, String diagnostico, String observaciones){
		Diagnostico diagnosticoPaciente = new Diagnostico(
				fecha: fechaDiagnostico,
				diagnostico: diagnostico,
				observaciones: observaciones)
		diagnosticoPaciente.setMedico(this)
		diagnosticoPaciente.save(flush: true)

		InternacionPaciente internacion = paciente.getLastInternacion()
		internacion.getDiagnosticos().add(diagnosticoPaciente)

		internacion.save(flush:true)
		paciente.save(flush:true)
		
		return diagnosticoPaciente
	}

	EstudioSolicitado solicitarEstudioPaciente(Paciente paciente, String estudio, String observaciones) {
		EstudioSolicitado estadoEstudioSolicitado = new EstudioSolicitado(
				fecha: new Date(),
				descripcion: estudio,
				observaciones: observaciones,
				medicoSolicitante: this,
				estudio: estudio).save(flush:true)

		InternacionPaciente internacion = paciente.getLastInternacion()
		internacion.getEstadosActuales().add(estadoEstudioSolicitado)

		internacion.save(flush:true)
		paciente.save(flush:true)
		
		return estadoEstudioSolicitado
	}

	EstudioPendienteDeRetiro registrarEstudioPendienteDeRetiroPaciente(Paciente paciente, Date fecha, String descripcion, String observaciones, String lugarDeRetiro) {
		EstudioPendienteDeRetiro estudioPendienteDeRetiro = new EstudioPendienteDeRetiro(
				fecha: fecha,
				descripcion:descripcion,
				observaciones:observaciones,   
				responsable: this,
				lugarDeRetiro: lugarDeRetiro).save(flush:true)

		InternacionPaciente internacion = paciente.getLastInternacion()
		internacion.getEstadosActuales().add(estudioPendienteDeRetiro)

		internacion.save(flush:true)
		paciente.save(flush:true)
		
		return estudioPendienteDeRetiro
	}

	EstudioRetirado retirarEstudioPaciente(Paciente paciente, Date fecha, String descripcion, String observaciones) {
		EstudioRetirado estudioRetirado = new EstudioRetirado(
			fecha: fecha,
			descripcion:descripcion,
			observaciones:observaciones,
			retiradoPor: this).save(flush:true)
		
		InternacionPaciente internacion = paciente.getLastInternacion()
		internacion.getEstadosActuales().add(estudioRetirado)

		internacion.save(flush:true)
		paciente.save(flush:true)
		
		return estudioRetirado
	}
	
	
	Interconsulta registrarInterconsulta(Paciente paciente, Medico medicoConsultado, Date fecha, String descripcion, String observaciones, String resultado, String indicaciones) {
		Interconsulta estadoInterconsulta = new Interconsulta(
				fecha: fecha,
				descripcion: descripcion,
				observaciones: observaciones,
				medicoSolicitante: this,
				medicoConsultado : medicoConsultado,
				resultado: resultado,
				indicaciones: indicaciones).save(flush:true)

		InternacionPaciente internacion = paciente.getLastInternacion()
		internacion.getEstadosActuales().add(estadoInterconsulta)

		internacion.save(flush:true)
		paciente.save(flush:true)
		
		return estadoInterconsulta
	}

	AguardandoAlta registrarAguardandoAltaPaciente(Paciente paciente, Date fecha, String descripcion, String observaciones) {
		AguardandoAlta aguardandoAlta = new AguardandoAlta(
				fecha: fecha,
				descripcion:descripcion,
				observaciones:observaciones,
				responsable: this).save(flush:true)

		InternacionPaciente internacion = paciente.getLastInternacion()
		internacion.getEstadosActuales().add(aguardandoAlta)

		internacion.save(flush:true)
		paciente.save(flush:true)
		
		return aguardandoAlta
	}
	
	PostQuirurjico registrarPostQuirurjico(Paciente paciente, Date fecha, String descripcion, String observaciones, String resultadoOperacion) {
		PostQuirurjico postQuirurjico = new PostQuirurjico(
				fecha: fecha,
				descripcion:descripcion,
				observaciones:observaciones,
				medicoResponsable: this,
				resultadoOperacion: resultadoOperacion).save(flush:true)
	
		InternacionPaciente internacion = paciente.getLastInternacion()
		internacion.getEstadosActuales().add(postQuirurjico)
	
		internacion.save(flush:true)
		paciente.save(flush:true)
	
		return postQuirurjico
	}
	
	def registrarPaseDeGuardia() {
		//TODO: Pases de guardia a definir.
	}
}
