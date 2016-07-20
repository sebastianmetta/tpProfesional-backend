package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.DateUtils
import ar.fiuba.tpProfesional.paciente.estado.EstudioPendienteDeRetiro

/**
 * Encargado de proveer una representacion adecuada en formato JSON.
 * @author sebastian
 *
 */
class EstudioPendienteDeRetiroMarshaller {
	void register() {
		JSON.registerObjectMarshaller(EstudioPendienteDeRetiro) { EstudioPendienteDeRetiro estado ->
			return [
				estado: "EstudioPendienteDeRetiro",
				fecha : DateUtils.dateToString(estado.getFecha(),DateUtils.DD_MM_YYYY),
				descripcion : estado.getDescripcion(),
				observaciones : estado.getObservaciones(),
				responsable : estado.getResponsable(),
				lugarDeRetiro: estado.getLugarDeRetiro()
			]
		}
	}
}
