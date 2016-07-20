package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.DateUtils
import ar.fiuba.tpProfesional.paciente.estado.EstudioSolicitado

/**
 * Encargado de proveer una representacion adecuada en formato JSON.
 * @author sebastian
 *
 */
class EstudioSolicitadoMarshaller {
	void register() {
		JSON.registerObjectMarshaller(EstudioSolicitado) { EstudioSolicitado estado ->
			return [
				estado: "EstudioSolicitado",
				fecha : DateUtils.dateToString(estado.getFecha(),DateUtils.DD_MM_YYYY),
				descripcion : estado.getDescripcion(),
				observaciones : estado.getObservaciones(),
				medicoSolicitante : estado.medicoSolicitante,
				estudio : estado.estudio
			]
		}
	}
}
