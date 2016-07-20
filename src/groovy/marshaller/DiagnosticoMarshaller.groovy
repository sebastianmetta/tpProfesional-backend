package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.DateUtils
import ar.fiuba.tpProfesional.paciente.Diagnostico

/**
 * Encargado de proveer una representacion adecuada en formato JSON.
 * @author sebastian
 *
 */
class DiagnosticoMarshaller {

	void register() {
		JSON.registerObjectMarshaller(Diagnostico) { Diagnostico diagnostico ->
			return [
				fecha : DateUtils.dateToString(diagnostico.getFecha(),DateUtils.DD_MM_YYYY),
				diagnostico : diagnostico.diagnostico,
				medico : diagnostico.medico,
				observaciones : diagnostico.observaciones
			]
		}
	}
}
