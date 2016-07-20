package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.infraestructura.Habitacion

/**
 * Encargado de proveer una representacion adecuada de una habitacion en formato JSON.
 * @author sebastian
 *
 */
class HabitacionMarshaller {
	void register() {
		JSON.registerObjectMarshaller(Habitacion) { Habitacion habitacion ->
			return [
				numero: habitacion.numero,
				sector: habitacion.sector.toString(),
				camas: habitacion.camas,
			]
		}
	}
}
