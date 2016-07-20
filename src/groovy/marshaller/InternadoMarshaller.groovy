package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.DateUtils;
import ar.fiuba.tpProfesional.paciente.estado.Internado

/**
 * Encargado de proveer una representacion adecuada en formato JSON.
 * @author sebastian
 *
 */
class InternadoMarshaller {
	void register() {
		JSON.registerObjectMarshaller(Internado) { Internado estado ->
			return [
				estado: "Internado",
				fecha : DateUtils.dateToString(estado.getFecha(),DateUtils.DD_MM_YYYY),
				descripcion : estado.getDescripcion(),
				observaciones : estado.getObservaciones(),
				internadoPor : estado.getInternadoPor()
			]
		}
	}
}
