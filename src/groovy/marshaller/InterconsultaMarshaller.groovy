package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.DateUtils
import ar.fiuba.tpProfesional.paciente.estado.Interconsulta


/**
 * Encargado de proveer una representacion adecuada en formato JSON.
 * @author sebastian
 *
 */
class InterconsultaMarshaller {
	void register() {
		JSON.registerObjectMarshaller(Interconsulta) { Interconsulta estado ->
			return [
				estado: "Interconsulta",
				fecha : DateUtils.dateToString(estado.getFecha(),DateUtils.DD_MM_YYYY),
				descripcion : estado.getDescripcion(),
				observaciones : estado.getObservaciones(),
				medicoSolicitante : estado.medicoSolicitante,
				medicoConsultado : estado.medicoConsultado,
				resultado : estado.resultado,
				indicaciones : estado.indicaciones,
			]
		}
	}
}
