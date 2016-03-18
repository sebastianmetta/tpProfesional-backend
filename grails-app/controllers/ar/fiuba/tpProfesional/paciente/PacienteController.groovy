package ar.fiuba.tpProfesional.paciente



import static org.springframework.http.HttpStatus.*
import ar.fiuba.tpProfesional.security.RegistroCommand;
import grails.converters.JSON;
import grails.rest.RestfulController;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PacienteController extends RestfulController {
	
	def list() {
		params.max = Math.min(params.max ? params.int('max') : 50, 200)
		def list = Paciente.list(params)
		withFormat {
			json { render list as JSON }
		}
	}

    def show(Paciente pacienteInstance) {
        respond pacienteInstance
    }
	
	def save(){
		log.info("Procesando peticion para crear paciente: " + request.JSON)
		
		Paciente pacienteToCreate = new Paciente(request.JSON)
		pacienteToCreate.validate()
		if (pacienteToCreate.hasErrors()) {
			response.status = 422
			render pacienteToCreate.errors as JSON
			return
		}
		log.info("Creando paciente " + pacienteToCreate.id + ": " + pacienteToCreate.toString())
		pacienteToCreate.save flush:true
		
		response.status = 200
		render pacienteToCreate as JSON
	}

    def update() {
		log.info("Procesando peticion para actualizar paciente: " + request.JSON)
		def command = new PacienteCommand(request.JSON)

		Paciente pacienteToUpdate = Paciente.findByDni(command.getDni())
		if (pacienteToUpdate == null) {
			response.status = 422
			renderErrorMessage("Dni no coincide.") 
			return
		}
		pacienteToUpdate.setAntecedentesFamiliares(command.getAntecedentesFamiliares())
		pacienteToUpdate.setDireccion(command.getDireccion())
		pacienteToUpdate.setNombreYApellido(command.getNombreYApellido())
		pacienteToUpdate.setObservaciones(command.getObservaciones())
		pacienteToUpdate.setTelefono(command.getTelefono())
			
		pacienteToUpdate.validate()
		if (pacienteToUpdate.hasErrors()) {
			response.status = 422
			render pacienteToUpdate.errors as JSON
			return
		}
		
		log.info("Actualizando paciente " + pacienteToUpdate.id + ": " + pacienteToUpdate.toString())
		
		pacienteToUpdate.save flush:true
		
		response.status = 200
		render pacienteToUpdate as JSON
		
		
    }

    @Transactional
    def delete(Paciente pacienteInstance) {
        if (pacienteInstance == null) {
            notFound()
            return
        }
		log.info("Eliminando paciente " + pacienteInstance.id + ": " + pacienteInstance.toString())
        pacienteInstance.delete flush:true

        response.status = 200
        renderMessage("Paciente eliminado.")
    }

	def find() {
		log.info("Buscando pacientes. Filtros: " + request.JSON)
		def command
		try {
			command = new PacienteFiltroCommand(request.JSON)
		} catch (Exception e) {
			response.status = 422
			renderErrorMessage('JSON invalido.')
			return
		}
		
		def result = Paciente.createCriteria().list{
			if (command.getDni() != null) {
				ilike('dni', "%".concat(command.getDni()).concat("%"))
			}
			if (command.getNombreYApellido()!=null) {
				ilike("nombreYApellido", "%".concat(command.getNombreYApellido().concat("%")))
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'paciente.label', default: 'Paciente'), params.id])
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
