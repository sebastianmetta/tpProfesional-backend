package marshaller

import java.util.List;

import grails.converters.JSON;
import ar.fiuba.tpProfesional.infraestructura.Cama;
import ar.fiuba.tpProfesional.infraestructura.Habitacion;
import ar.fiuba.tpProfesional.infraestructura.Sector;

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
