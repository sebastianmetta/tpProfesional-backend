
import ar.fiuba.tpProfesional.estadoPaciente.command.AguardandoAltaCommand;
import ar.fiuba.tpProfesional.estadoPaciente.command.InterconsultaCommand;
import ar.fiuba.tpProfesional.estadoPaciente.command.PostQuirurjicoCommand;
import ar.fiuba.tpProfesional.estadoPaciente.command.RegistroEstudioPendienteDeRetiroCommand;
import ar.fiuba.tpProfesional.estadoPaciente.command.RetirarEstudioCommand;
import ar.fiuba.tpProfesional.infraestructura.Cama
import ar.fiuba.tpProfesional.infraestructura.Habitacion
import ar.fiuba.tpProfesional.infraestructura.Sector
import ar.fiuba.tpProfesional.infraestructura.Servicio;
import ar.fiuba.tpProfesional.paciente.Diagnostico
import ar.fiuba.tpProfesional.paciente.InternacionPaciente
import ar.fiuba.tpProfesional.paciente.MotivoAlta;
import ar.fiuba.tpProfesional.paciente.OrigenInternacion
import ar.fiuba.tpProfesional.paciente.Paciente
import ar.fiuba.tpProfesional.paciente.command.AltaPacienteCommand;
import ar.fiuba.tpProfesional.paciente.command.DiagnosticoCommand;
import ar.fiuba.tpProfesional.paciente.command.InternacionPacienteCommand;
import ar.fiuba.tpProfesional.paciente.command.SolicitudEstudioCommand;
import ar.fiuba.tpProfesional.paciente.estado.EstadoPaciente
import ar.fiuba.tpProfesional.paciente.estado.EstudioSolicitado
import ar.fiuba.tpProfesional.paciente.estado.Internado
import ar.fiuba.tpProfesional.security.Authority
import ar.fiuba.tpProfesional.security.User
import ar.fiuba.tpProfesional.security.UserAuthority
import ar.fiuba.tpProfesional.security.Authority.AuthorityType
import ar.fiuba.tpProfesional.usuario.Administrativo;
import ar.fiuba.tpProfesional.usuario.ClasificacionMedico;
import ar.fiuba.tpProfesional.usuario.Enfermero;
import ar.fiuba.tpProfesional.usuario.Medico;


class BootStrap {
	
	def internacionPacienteService

	def medicoService
	
	def init = { servletContext ->

		Locale defaultLocale = new Locale("es","ES")
		java.util.Locale.setDefault(defaultLocale)
		
		log.info('Inicializando datos de la aplicación...')

		//Infraestructura
		buildInfraestructura()
		
		Authority.findAll().each { it.delete(flush:true, failOnError:true) }
		
		//Roles disponibles
		def autConsultorExterno = new Authority(authority:AuthorityType.CONSULTOR_EXTERNO)
		autConsultorExterno.save(flush: true)
		def autJefeResidente = new Authority(authority:AuthorityType.JEFE_RESIDENTE)
		autJefeResidente.save(flush: true)
		def autAdministrativo = new Authority(authority:AuthorityType.ADMINISTRATIVO)
		autAdministrativo.save(flush: true)
		def autEnfermero = new Authority(authority:AuthorityType.ENFERMERO)
		autEnfermero.save(flush: true)
		def autJefePlanta = new Authority(authority:AuthorityType.JEFE_PLANTA)
		autJefePlanta.save(flush: true)
		def autR1 = new Authority(authority:AuthorityType.R1)
		autR1.save(flush: true)
		def autR2 = new Authority(authority:AuthorityType.R2)
		autR2.save(flush: true)
		def autR3 = new Authority(authority:AuthorityType.R3)
		autR3.save(flush: true)
		def autR4 = new Authority(authority:AuthorityType.R4)
		autR4.save(flush: true)

		//Usuarios disponibles
		def userConsultorExterno = new Medico(username:"anestesiologo", password:"anestesiologo999", dni:"00.000.000", email:"mail0@gmail.com", nombreYApellido:"Usuario Consultor Externo", telefono:"00-0000-0000", clasificacion: ClasificacionMedico.CONSULTOR_EXTERNO, servicio: Servicio.ANESTESIOLOGIA)
		userConsultorExterno.save(flush:true)
		def userJefeResidente = new Medico(username:"jefeEesidente", password:"jeferesidente999", dni:"11.111.111", email:"mail1@gmail.com", nombreYApellido:"Usuario Jefe de residentes", telefono:"11-1111-1111", clasificacion: ClasificacionMedico.JEFE_RESIDENTE, servicio: Servicio.CIRUGIA)
		userJefeResidente.save(flush:true)
		def userAdministrativo = new Administrativo(username:"administrativo", password:"administrativo999", dni:"22.222.222", email:"mail2@gmail.com", nombreYApellido:"Ususario Administrativo", telefono:"22-2222-2222")
		userAdministrativo.save(flush:true)
		def userEnfermero = new Enfermero(username:"enfermero", password:"enfermero999", dni:"33.333.333", email:"mail3@gmail.com", nombreYApellido:"Usuario Enfermero", telefono:"33-3333-3333")
		userEnfermero.save(flush:true)
		def userJefePlanta = new Medico(username:"jefePlanta", password:"jefeplanta999", dni:"44.444.444", email:"mail4@gmail.com", nombreYApellido:"Usuario Jefe de planta", telefono:"44-4444-4444", clasificacion: ClasificacionMedico.JEFE_PLANTA, servicio: Servicio.CIRUGIA)
		userJefePlanta.save(flush:true)
		def userR1 = new Medico(username:"residente1", password:"residente1999", dni:"55.555.555", email:"mail5@gmail.com", nombreYApellido:"Usuario Residente tipo R1", telefono:"55-5555-5555", clasificacion: ClasificacionMedico.R1, servicio: Servicio.CIRUGIA)
		userR1.save(flush:true)
		def userR2 = new Medico(username:"residente2", password:"residente2999", dni:"66.666.666", email:"mail6@gmail.com", nombreYApellido:"Usuario Residente tipo R2", telefono:"66-6666-6666", clasificacion: ClasificacionMedico.R2, servicio: Servicio.CIRUGIA)
		userR2.save(flush:true)
		def userR3 = new Medico(username:"residente3", password:"residente3999", dni:"77.777.777", email:"mail7@gmail.com", nombreYApellido:"Usuario Residente tipo R3", telefono:"77-7777-7777", clasificacion: ClasificacionMedico.R3, servicio: Servicio.CIRUGIA)
		userR3.save(flush:true)
		def userR4 = new Medico(username:"residente4", password:"residente4999", dni:"88.888.888", email:"mail8@gmail.com", nombreYApellido:"Usuario Residente tipo R4", telefono:"88-8888-8888", clasificacion: ClasificacionMedico.R4, servicio: Servicio.CIRUGIA)
		userR4.save(flush:true)

		//Le asignamos roles a los usuarios.
		new UserAuthority(user:userConsultorExterno, authority:autConsultorExterno).save(flush: true)
		new UserAuthority(user:userJefeResidente, authority:autJefeResidente).save(flush: true)
		new UserAuthority(user:userAdministrativo, authority:autAdministrativo).save(flush: true)
		new UserAuthority(user:userEnfermero, authority:autEnfermero).save(flush: true)
		new UserAuthority(user:userJefePlanta, authority:autJefePlanta).save(flush: true)
		new UserAuthority(user:userR1, authority:autR1).save(flush: true)
		new UserAuthority(user:userR2, authority:autR2).save(flush: true)
		new UserAuthority(user:userR3, authority:autR3).save(flush: true)
		new UserAuthority(user:userR4, authority:autR4).save(flush: true)

		buildPacienteConInternaciones()
		
	}
	
	def buildInfraestructura() {
		
		Habitacion h1 = new Habitacion(numero: 1, sector:Sector.HOMBRES)
		Habitacion h2 = new Habitacion(numero: 2, sector:Sector.HOMBRES)
		Habitacion h3 = new Habitacion(numero: 3, sector:Sector.MUJERES)
		Habitacion h4 = new Habitacion(numero: 4, sector:Sector.MUJERES)
		
		Cama c1 = new Cama(numero: 1, descripcion:"Cama 1", observaciones:"Observaciones cama 1").save(flush: true)
		Cama c2 = new Cama(numero: 2, descripcion:"Cama 2", observaciones:"Observaciones cama 2").save(flush: true)
		Cama c3 = new Cama(numero: 3, descripcion:"Cama 3", observaciones:"Observaciones cama 3").save(flush: true)
		Cama c4 = new Cama(numero: 4, descripcion:"Cama 4", observaciones:"Observaciones cama 4").save(flush: true)
		Cama c5 = new Cama(numero: 5, descripcion:"Cama 5", observaciones:"Observaciones cama 5").save(flush: true)
		Cama c6 = new Cama(numero: 6, descripcion:"Cama 6", observaciones:"Observaciones cama 6").save(flush: true)
		Cama c7 = new Cama(numero: 7, descripcion:"Cama 7", observaciones:"Observaciones cama 7").save(flush: true)
		Cama c8 = new Cama(numero: 8, descripcion:"Cama 8", observaciones:"Observaciones cama 8").save(flush: true)
		
		h1.getCamas().add(c1)
		h1.getCamas().add(c2)
		h1.save(flush: true)
		
		h2.getCamas().add(c3)
		h2.getCamas().add(c4)
		h2.save(flush: true)
		
		h3.getCamas().add(c5)
		h3.getCamas().add(c6)
		h3.save(flush: true)
		
		h4.getCamas().add(c7)
		h4.getCamas().add(c8)
		h4.save(flush: true)
		
	}

	def buildPacienteConInternaciones() {
		//Se crea un paciente
		Paciente paciente = new Paciente(
			dni:"25.564.321", nombreYApellido:"Juan Perez",fechaNacimiento: new Date(), 
			sexo:"M", direccion: "Calle 45 nro 4657", telefono:"15-5879-5468", 
			antecedentesFamiliares: "Padre diabetico", observaciones: "Observaciones de Juan Perez")
		paciente.save(flush : true)
		
		//Un administrativo interna al paciente
		Administrativo administrativoUser = Administrativo.findByUsername("administrativo")
		InternacionPacienteCommand internacionCommand = new InternacionPacienteCommand(
			idPaciente: paciente.getId().toString(),
			fechaInternacion: "22/12/2015",
			origenInternacion: "GUARDIA",
			idAdministrativo: administrativoUser.getId(),
			patologia: "La patologia de la internación", 
			idCama: "1")	
		internacionPacienteService.createInternacionPaciente(internacionCommand)
		
		//Un medico lo diagnostica
		Medico medicoR1 = Medico.findByUsername("residente1")
		DiagnosticoCommand diagnosticoCommand = new DiagnosticoCommand(
			fechaDiagnostico: "23/12/2015",
			idPaciente: paciente.getId(),
			idMedico: medicoR1.getId(),
			diagnostico: "El paciente esta en el horno",
			observaciones: "Las observaciones del diagnostico")
		medicoService.createDiagnostico(diagnosticoCommand)
		
		//Se solicita un estudio para el paciente
		SolicitudEstudioCommand solicitudEstudioCommand = new SolicitudEstudioCommand(
			idPaciente: paciente.getId(),
			idMedicoSolicitante: medicoR1.getId(),
			estudio: "Solicitud de analisis de sangre y orina completo", 
			observaciones: "Se requiere en 48hs.")
		medicoService.solicitarEstudioPaciente(solicitudEstudioCommand)
		
		//Se indica que el estudio esta listo para ser retirado. 
		RegistroEstudioPendienteDeRetiroCommand estudioPendienteRetiroCommand = new RegistroEstudioPendienteDeRetiroCommand (
			idPaciente: paciente.getId(),
			idMedicoResponsable: medicoR1.getId(),
			fecha: "25/12/2015",
			decripcion: "Analisis de sangre y orina listos.",
			observaciones: "-",
			lugarDeRetiro: "Laboratorio piso 3"
			)
		medicoService.registrarEstudioPendienteDeRetiroPaciente(estudioPendienteRetiroCommand)
		
		// Se retira el estudio
		RetirarEstudioCommand retirarEstudioCommand = new RetirarEstudioCommand(
			idPaciente: paciente.getId(),
			idMedicoResponsable: medicoR1.getId(),
			fecha: "26/12/2015",
			decripcion: "Se retira estudio analisis de sangre y orina.",
			observaciones: "Los estudios dieron para atras :(")
		 medicoService.retirarEstudioPaciente(retirarEstudioCommand)
		 
		 //Se genera una interconsulta
		 Medico medicoAnestesiologo = Medico.findByUsername("anestesiologo")
		 InterconsultaCommand interconsultaCommand = new InterconsultaCommand(
			 idPaciente: paciente.getId(),
			 idMedicoSolicitante: medicoR1.getId(),
			 idMedicoConsultado: medicoAnestesiologo.getId(),
			 fecha: "28/12/2015",
			 decripcion: "Se realiza interconsulta con un anestesiologo.",
			 observaciones: "Sin observaciones.",
			 resultado: "El resultado de la interconsulta",
			 indicaciones: "Las indicaciones de la interconsulta")
		 medicoService.registrarInterconsulta(interconsultaCommand)
		 
		 // Se opera al paciente y se registra la operación.
		 PostQuirurjicoCommand postQuirurjicoCommand = new PostQuirurjicoCommand(
			 idPaciente: paciente.getId(),
			 idMedicoResponsable: medicoR1.getId(),
			 fecha: "01/01/2016",
			 decripcion: "Hay que operar!",
			 observaciones: "Le da impresion la aguja :S",
			 resultadoOperacion: "La operacion fue todo un exito. Tenia un pedazo de vidrio atroden.")
		  medicoService.registrarPostQuirurjico(postQuirurjicoCommand)
		 
		 // Se indica que el paciente esta listo para darle el alta.
		 AguardandoAltaCommand aguardandoAltaCommand = new AguardandoAltaCommand(
			 idPaciente: paciente.getId(),
			 idMedicoResponsable: medicoR1.getId(),
			 fecha: "05/01/2016",
			 decripcion: "Luego de operarlo con exito el paciente esta listo para darle el alta.",
			 observaciones: "Esperemos que zafe")
		  medicoService.registrarAguardandoAlta(aguardandoAltaCommand)
		 
		// Se le da el alta al paciente
		  AltaPacienteCommand altaPacienteCommand = new AltaPacienteCommand(
			  idPaciente: paciente.getId(),
			  fechaAlta: "07/01/2016",
			  idAdministrativo: administrativoUser.getId(),
			  motivoAlta: MotivoAlta.ALTA_NORMAL,
			  indicaciones: "Las indicaciones",
			  observaciones: "Las observaciones del alta")
		  internacionPacienteService.darDeAltaPaciente(altaPacienteCommand)
		  
		//TODO: 1. Crear helpers para la creacion de estados asi se reutilizan. 
		//TODO: 2. crear nueva internacion para ver el correcto funcionamiento del getLastinternacion
		//TODO: 3. Ver la maquina de estados para las restricciones de estados. Esa logica deberia estar en el servicio de medico tal vez.
		//TODO: 4. Crear api paciente/estado/XXX (uno para cada estado?) --> Se utilizan los commands ya creados.
		//TODO: 5. Documentar apis.
		//TODO: 6. Ver tema enfermeros. Definir api.
		
		  
	}
	
	
	def destroy = {
	}
}
