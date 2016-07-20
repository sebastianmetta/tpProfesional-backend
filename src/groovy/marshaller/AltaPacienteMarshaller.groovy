package marshaller

import grails.converters.JSON
import ar.fiuba.tpProfesional.DateUtils
import ar.fiuba.tpProfesional.paciente.AltaPaciente

/**
 * Encargado de proveer una representacion adecuada en formato JSON.
 * @author sebastian
 *
 */
class AltaPacienteMarshaller {
	void register() {
		JSON.registerObjectMarshaller(AltaPaciente) { AltaPaciente altaPaciente ->
			return [

				fechaAlta: DateUtils.dateToString(altaPaciente.getFechaAlta(),DateUtils.DD_MM_YYYY),
				administrativo: altaPaciente.getAdministrativo(),
				motivoAlta: altaPaciente.getMotivoAlta().toString(),
				indicaciones: altaPaciente.getIndicaciones(),
				observaciones: altaPaciente.getObservaciones(),

			]
		}
	}
}
