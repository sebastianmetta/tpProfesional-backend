package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.usuario.Enfermero

/**
 * Encargado de proveer una representacion adecuada en formato JSON.
 * @author sebastian
 *
 */
class EnfermeroMarshaller {
	void register() {
		JSON.registerObjectMarshaller(Enfermero) { Enfermero enfermero ->
			return [
				rol : enfermero.getAuthority(),
				username : enfermero.username,
				email : enfermero.email,
				dni : enfermero.dni,
				nombreYApellido : enfermero.nombreYApellido,
				telefono : enfermero.telefono
			]
		}
	}
}
