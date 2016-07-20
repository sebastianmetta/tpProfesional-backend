package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.DateUtils
import ar.fiuba.tpProfesional.paciente.estado.EstudioRetirado

/**
 * Encargado de proveer una representacion adecuada en formato JSON.
 * @author sebastian
 *
 */
class EstudioRetiradoMarshaller {
	void register() {
		JSON.registerObjectMarshaller(EstudioRetirado) { EstudioRetirado estado ->
			return [
				estado: "EstudioRetirado",
				fecha : DateUtils.dateToString(estado.getFecha(),DateUtils.DD_MM_YYYY),
				descripcion : estado.getDescripcion(),
				observaciones : estado.getObservaciones(),
				retiradoPor : estado.retiradoPor

			]
		}
	}
}
