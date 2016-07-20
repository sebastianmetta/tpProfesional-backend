package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.usuario.Administrativo

/**
 * Encargado de proveer una representacion adecuada en formato JSON.
 * @author sebastian
 *
 */
class AdministrativoMarshaller {
	void register() {
		JSON.registerObjectMarshaller(Administrativo) { Administrativo administrativo ->
			return [
				rol : administrativo.getAuthority(),
				username : administrativo.username,
				email : administrativo.email,
				dni : administrativo.dni,
				nombreYApellido : administrativo.nombreYApellido,
				telefono : administrativo.telefono
			]
		}
	}
}
