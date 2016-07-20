package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.usuario.Medico

/**
 * Encargado de proveer una representacion adecuada en formato JSON.
 * @author sebastian
 *
 */
class MedicoMarshaller {
	void register() {
		JSON.registerObjectMarshaller(Medico) { Medico medico ->
			return [
				rol : medico.getAuthority(),
				username : medico.username,
				email : medico.email,
				dni : medico.dni,
				nombreYApellido : medico.nombreYApellido,
				telefono : medico.telefono,
				camas : medico.camas,
				clasificacion : medico.clasificacion,
				servicio : medico.servicio
			]
		}
	}
}
