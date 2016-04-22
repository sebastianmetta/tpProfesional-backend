package ar.fiuba.tpProfesional.infraestructura

import static org.springframework.http.HttpStatus.*
import ar.fiuba.tpProfesional.paciente.Paciente
import grails.converters.JSON;
import grails.rest.RestfulController;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CamaController {

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 50, 200)
		def list = Cama.list(params)
		log.info("Consultando camas. Respuesta: " + list)
		withFormat {
			json { render list as JSON }
		}
	}

	def show(Cama camaInstance) {
		respond camaInstance
	}

	def save() {
		log.info("Procesando peticion para crear cama: " + request.JSON)
		Cama camaToCreate = new Cama(request.JSON)
		camaToCreate.validate()
		if (camaToCreate.hasErrors()) {
			response.status = 422
			render camaToCreate.errors as JSON
			return
		}
		log.info("Creando cama " + camaToCreate.toString())
		camaToCreate.save flush:true

		response.status = 200
		render camaToCreate as JSON
	}

	def update() {
		log.info("Procesando peticion para actualizar cama: " + request.JSON)
		def command
		try {
			command = new CamaCommand(request.JSON)
		} catch (Throwable e) {
			def messageToRender = [error: "JSON invalido"]
			response.status = 422
			render messageToRender as JSON
			return
		}

		if ((command.getNumero()==null) || (!command?.getNumero().isInteger())) {
			def messageToRender = [error: "Numero de cama invalido"]
			response.status = 422
			render messageToRender as JSON
		}

		Cama camaToUpdate = Cama.findByNumero(command.getNumero())
		if (camaToUpdate == null) {
			response.status = 422
			renderErrorMessage("La cama no existe.")
			return
		}
		if ((command.getPaciente()!=null) && (command.getPaciente().isNumber())){
			Paciente paciente = Paciente.findById(command.getPaciente())
			camaToUpdate.setPaciente(paciente)
		}
		camaToUpdate.setDescripcion(command.getDescripcion())
		camaToUpdate.setObservaciones(command.getObservaciones())

		camaToUpdate.validate()
		if (camaToUpdate.hasErrors()) {
			response.status = 422
			render camaToUpdate.errors as JSON
			return
		}

		log.info("Actualizando cama " + camaToUpdate.id + ": " + camaToUpdate.toString())

		camaToUpdate.save flush:true

		response.status = 200
		render camaToUpdate as JSON


	}

	@Transactional
	def delete(Cama camaInstance) {
		if (camaInstance == null) {
			notFound()
			return
		}
		log.info("Eliminando cama " + camaInstance.id + ": " + camaInstance.toString())
		camaInstance.delete flush:true

		response.status = 200
		renderMessage("Cama eliminada.")
	}

	def find() {
		log.info("Buscando camas. Filtros: " + request.JSON)
		def command
		try {
			command = new CamaFiltroCommand(request.JSON)
		} catch (Throwable e) {
			response.status = 422
			renderErrorMessage('JSON invalido.')
			return
		}

		def result = Cama.createCriteria().list{
			if (command.getEstado() != null) {
				if (command.getEstado().equalsIgnoreCase("libres")) {
					isNull('paciente')
				} else if(command.getEstado().equalsIgnoreCase("ocupadas")) {
					isNotNull('paciente')
				}
			}
			if (command.getIdPaciente()!=null) {
				Paciente paciente = Paciente.findById(command.getIdPaciente())
				if (paciente!=null) {					
					eq('paciente',paciente)
				} else {
					//FIXME: Ver manera mas prolija de hacer esto. 
					eq('numero',-1)
				}
			}
		}

		response.status = 200
		withFormat {
			json { render result as JSON }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'cama.label', default: 'Cama'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}

	def renderErrorMessage(String message){
		def messageToRender = [error: "$message"]
		render messageToRender as JSON
	}

	def renderMessage(String message){
		def messageToRender = [descripcion: "$message"]
		render messageToRender as JSON
	}

	def notAllowed() {
		render status: METHOD_NOT_ALLOWED
	}

}
