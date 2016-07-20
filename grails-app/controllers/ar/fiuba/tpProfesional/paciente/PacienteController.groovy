package ar.fiuba.tpProfesional.paciente



import static org.springframework.http.HttpStatus.*

import org.codehaus.groovy.util.StringUtil;
import org.springframework.util.StringUtils;

import ar.fiuba.tpProfesional.DateUtils;
import ar.fiuba.tpProfesional.paciente.command.InternacionPacienteFiltroCommand;
import ar.fiuba.tpProfesional.paciente.command.PacienteCommand;
import ar.fiuba.tpProfesional.paciente.command.PacienteFiltroCommand;
import grails.converters.JSON
import grails.rest.RestfulController
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
		log.info("Creando paciente: " + pacienteToCreate.toString())
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
			renderErrorMessage("El Dni enviado no existe.") 
			return
		}
		pacienteToUpdate.setFechaNacimiento(DateUtils.stringToDate(command.getFechaNacimiento(),DateUtils.DD_MM_YYYY))
		pacienteToUpdate.setSexo(command.getSexo())
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
	
	def findByInternacion() {
		log.info("Buscando internaciones de pacientes. Filtros: " + request.JSON)
		def command
		try {
			command = new InternacionPacienteFiltroCommand(request.JSON)
		} catch (Exception e) {
			response.status = 422
			renderErrorMessage('JSON invalido.')
			return
		}
		
		//TODO: 1. Ver porque en el json de internaciones no estan los estados.
		//TODO: 2. Pendiente revisar que esto funcione adecuadamente. Ver primero Bootstrap.
		def paciente = Paciente.findById(command.getIdPaciente())
		def internaciones = null
		if (paciente) {			
			if (command.getConsultaTodas().equalsIgnoreCase("true")) {
				log.info("Buscando todas las internaciones del paciente " + paciente.toString())
				internaciones = paciente.getInternaciones()
			} else if (command.getConsultaUltima().equalsIgnoreCase("true")) {
				log.info("Buscando ultima internacion del paciente " + paciente.toString())
				def sortedInternaciones = paciente.getInternaciones().sort{it.fechaInternacion}
				internaciones = sortedInternaciones.last()
			} else if (!StringUtils.isEmpty(command.getFechaInternacion())) {
				log.info("Buscando internacion del paciente " + paciente.toString() + " para la fecha " + command.getFechaInternacion())
				internaciones = paciente.getInternaciones().find { it -> it.fechaInternacion.equals(DateUtils.stringToDate(command.getFechaInternacion(),DateUtils.DD_MM_YYYY)) }
			}
		} else {
			response.status = 422
			renderErrorMessage('Id de paciente inexistente.')
			return
		}
		
		response.status = 200
		withFormat {
			json { render internaciones as JSON }
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
