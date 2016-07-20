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
				id: internacionPaciente.getId(),
				fechaInternacion: DateUtils.dateToString(internacionPaciente.getFechaInternacion(), DateUtils.DD_MM_YYYY),
				origenInternacion: internacionPaciente.getOrigenInternacion().toString(),
				cama: internacionPaciente.getCamas()?.first(),
				patologia: internacionPaciente.getPatologia(),
				diagnosticos: internacionPaciente.getDiagnosticos(),
				estadosActuales: internacionPaciente.getEstadosActuales(),
				estadosAnteriores: internacionPaciente.getEstadosAnteriores(),
				altaPaciente: internacionPaciente.getAltaPaciente()
			]
		}
	}
}
