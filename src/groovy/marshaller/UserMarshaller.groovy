package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.security.User

/**
 * Encargado de proveer una representacion adecuada de un sector en formato JSON.
 * @author sebastian
 *
 */
class UserMarshaller {
	void register() {
		JSON.registerObjectMarshaller(User) { User user ->
			return [
				id: user.id,
				username: user.username,
				email: user.email,
				dni: user.dni,
				nombreYApellido: user.nombreYApellido,
				telefono: user.telefono
			]
		}
	}
}
