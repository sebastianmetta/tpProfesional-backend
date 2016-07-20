package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.DateUtils
import ar.fiuba.tpProfesional.paciente.estado.AguardandoAlta

/**
 * Encargado de proveer una representacion adecuada en formato JSON.
 * @author sebastian
 *
 */
class AguardandoAltaMarshaller {
	void register() {
		JSON.registerObjectMarshaller(AguardandoAlta) { AguardandoAlta estado ->
			return [
				estado: "AguardandoAlta",
				fecha : DateUtils.dateToString(estado.getFecha(),DateUtils.DD_MM_YYYY),
				descripcion : estado.getDescripcion(),
				observaciones : estado.getObservaciones()
				//TODO: Agregar campos restantes.

			]
		}
	}
}
