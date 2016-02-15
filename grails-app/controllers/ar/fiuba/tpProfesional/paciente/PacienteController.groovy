package ar.fiuba.tpProfesional.paciente



import static org.springframework.http.HttpStatus.*
import ar.fiuba.tpProfesional.security.RegistroCommand;
import grails.converters.JSON;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PacienteController {
	
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
		def command = new PacienteCommand(request.JSON)
		command.validate()
		if (command.hasErrors()) {
			response.status = 422
			render command.errors as JSON
			return
		} else {
			
			Paciente pacienteToSave = new Paciente()
			
			pacienteToSave.setDni(command.getDni())
			pacienteToSave.setNombreYApellido(command.getNombreYApellido())
			pacienteToSave.setDireccion(command.getDireccion())
			pacienteToSave.setTelefono(command.getTelefono())
			pacienteToSave.setAntecedentesFamiliares(command.getAntecedentesFamiliares())
			pacienteToSave.setObservaciones(command.getObservaciones())
			
			if (pacienteToSave.hasErrors()){
				response.status = 422
				render pacienteToSave.errors
				return
			}
			
			log.info("Creando paciente: " + pacienteToSave.toString())
			
			pacienteToSave.save(flush: true)

			response.status = 200
			render pacienteToSave as JSON
		}
	}
	
	
    def edit(Paciente pacienteInstance) {
        respond pacienteInstance
    }

    @Transactional
    def update(Paciente pacienteInstance) {
        if (pacienteInstance == null) {
            notFound()
            return
        }

        def command = new PacienteCommand(request.JSON)
		command.validate()
		if (command.hasErrors()) {
			response.status = 422
			render command.errors as JSON
			return
		}
		
		Paciente pacienteToUpdate = Paciente.get(pacienteInstance.id)
		
		pacienteToUpdate.setAntecedentesFamiliares(command.getAntecedentesFamiliares())
		pacienteToUpdate.setDireccion(command.getDireccion())
		pacienteToUpdate.setNombreYApellido(command.getNombreYApellido())
		pacienteToUpdate.setObservaciones(command.getObservaciones())
		pacienteToUpdate.setTelefono(command.getTelefono())
		 
		log.info("Actualizando paciente " + pacienteInstance.id + ": " + pacienteToUpdate.toString())
		
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
		//TODO: Obtener de properties.
		render "Paciente eliminado"
    }

	def find() {
		def command = new PacienteCommand(request.JSON)
		
		log.debug("Buscando pacientes. Filtros: " + command.toString())
		
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
	
	def notAllowed() {
		render status: METHOD_NOT_ALLOWED
	}
}
