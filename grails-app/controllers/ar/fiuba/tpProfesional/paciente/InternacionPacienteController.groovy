package ar.fiuba.tpProfesional.paciente



import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.rest.RestfulController
import grails.transaction.Transactional
import ar.fiuba.tpProfesional.InternacionPacienteService


class InternacionPacienteController extends RestfulController {
	static responseFormats = ['json', 'xml']

	InternacionPacienteService internacionPacienteService

	InternacionPacienteController() {
		super(InternacionPaciente)
	}

	@Override
	@Transactional
	def save(){
		log.info("Procesando peticion para crear una internacion de paciente: " + request.JSON)
		def command
		try {
			command = new InternacionPacienteCommand(request.JSON)
		} catch (Throwable e) {
			def messageToRender = [error: "JSON invalido"]
			response.status = 422
			render messageToRender as JSON
			return
		}
		command.validate()
		if (command.hasErrors()) {
			response.status = 422
			render command.errors as JSON
			return
		}

		log.info("Creando internacion paciente: " + command.toString())
		InternacionPaciente internacionPaciente
		try {
			internacionPaciente = internacionPacienteService.createInternacionPaciente(command)
		} catch (Throwable e) {
			def messageToRender = ["error:" + e.getMessage()]
			response.status = 422
			render messageToRender as JSON
			return
		}

		response.status = 200
		render internacionPaciente as JSON
	}

	def find() {
	}

	def renderErrorMessage(String message){
		def messageToRender = [error: "$message"]
		render messageToRender as JSON
	}

	def renderMessage(String message){
		def messageToRender = [descripcion: "$message"]
		render messageToRender as JSON
	}
}
