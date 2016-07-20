package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.DateUtils;
import ar.fiuba.tpProfesional.paciente.estado.PostQuirurjico

/**
 * Encargado de proveer una representacion adecuada en formato JSON.
 * @author sebastian
 *
 */
class PostQuirurjicoMarshaller {
	void register() {
		JSON.registerObjectMarshaller(PostQuirurjico) { PostQuirurjico estado ->
			return [
				estado: "PostQuirurjico",
				fecha : DateUtils.dateToString(estado.getFecha(),DateUtils.DD_MM_YYYY),
				descripcion : estado.getDescripcion(),
				observaciones : estado.getObservaciones(),
				resultadoOperacion: estado.getResultadoOperacion(),
				medicoResponsable: estado.getMedicoResponsable()
			]
		}
	}
}
