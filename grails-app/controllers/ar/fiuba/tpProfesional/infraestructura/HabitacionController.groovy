package ar.fiuba.tpProfesional.infraestructura

import static org.springframework.http.HttpStatus.*

import org.grails.datastore.gorm.finders.MethodExpression.InList;

import ar.fiuba.tpProfesional.paciente.Paciente;
import grails.rest.RestfulController;
import grails.transaction.Transactional
import grails.converters.JSON;

import java.util.Map;
import java.util.HashMap;

class HabitacionController extends RestfulController {
	static responseFormats = ['json', 'xml']
	
	HabitacionController() {
		super(Habitacion)
	}

	@Override
	def save() {
		notAllowed()
	}
	
	@Override
	def delete() {
		notAllowed()
	}
	
	@Override
	def update() {
		notAllowed()
	}
	
	def notAllowed() {
		render status: METHOD_NOT_ALLOWED
	}
	
	def find() {
		log.info("Buscando habitaciones. Filtros: " + request.JSON)
		def command
		try {
			command = new HabitacionFiltroCommand(request.JSON)
		} catch (Throwable e) {
			response.status = 422
			renderErrorMessage('JSON invalido.')
			return
		}

		//Descomentar si se quiere asignar un paciente a una cama como para probar el servicio.
		//Debera crearse un paciente con id = 1
//		Cama cama = Cama.findById(7)
//		Paciente paciente = Paciente.findById(1)
//		cama.setPaciente(paciente)
//		cama.save(flush:true)
		
		//Se buscan todas las camas que cumplan el criterio del filtro.
		def camasMatch = Cama.createCriteria().list{
			if (command.getEstadoCama() != null) {
				if (command.getEstadoCama().equalsIgnoreCase("libre")) {
					isNull('paciente')
				} else if(command.getEstadoCama().equalsIgnoreCase("ocupada")) {
					isNotNull('paciente')
				}
			}
		}
		def camasMatchIdList = camasMatch.id*.asType(Long)
		
		def allHabitaciones = Habitacion.createCriteria().list {
			
		}
		
		//Se iteran todas las camas, viendo cuales no cumplen con el criterio y se eliminan del resultado. 
		Map<String, Habitacion> habMap = new HashMap<String, Habitacion>()
		allHabitaciones.each { eachHabitacion ->
			Habitacion hab = habMap.get(eachHabitacion.id)
			if (hab==null) {
				hab = new Habitacion()
				hab.setNumero(eachHabitacion.numero)
				hab.setSector(eachHabitacion.sector)
				hab.camas.clear()
				habMap.put(eachHabitacion.id,hab)
			}
			eachHabitacion.camas.each { eachCama ->
				if (camasMatchIdList.contains(eachCama.id)) {
					hab.camas.add(eachCama)
				} 
			}
		}
		
		//Solo se devuelven habitaciones que tengan resultados que cumplen el criterio
		def result = new ArrayList<>()
		habMap.values().each { Habitacion eachHab -> 
			if (eachHab.camas.size()>0) {
				result.add(eachHab)
			}
		}
		
		response.status = 200
		withFormat {
			json { render result as JSON }
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
}
