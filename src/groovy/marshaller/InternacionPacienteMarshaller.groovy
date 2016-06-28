package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.DateUtils
import ar.fiuba.tpProfesional.paciente.InternacionPaciente

/**
 * Encargado de proveer una representacion adecuada de una internacion de un paciente en formato JSON.
 * @author sebastian
 *
 */
class InternacionPacienteMarshaller {
	void register() {
		JSON.registerObjectMarshaller(InternacionPaciente) { InternacionPaciente internacionPaciente ->
			return [
					id: internacionPaciente.id,
					fechaInternacion: DateUtils.dateToString(internacionPaciente.fechaInternacion, DateUtils.DD_MM_YYYY),
					origenInternacion: internacionPaciente.origenInternacion.toString(),
					patologia: internacionPaciente.patologia,
					diagnostico: internacionPaciente.diagnostico,
					//Se devuelve la ultima cama asociada (la actual)
					idCama: internacionPaciente.camas.first()?.id,
			]
		}
	}
}
