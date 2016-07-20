package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.infraestructura.Cama

/**
 * Encargado de proveer una representacion adecuada de una cama en formato JSON.
 * @author sebastian
 *
 */
class CamaMarshaller {
	void register() {
		JSON.registerObjectMarshaller( Cama) { Cama cama ->
			return [
				id : cama.id,
				numero: cama.numero,
				paciente: cama.paciente?.id,
				descripcion: cama.descripcion,
				observaciones: cama.observaciones
			]
		}
	}
}
