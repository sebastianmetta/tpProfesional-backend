package marshaller

import ar.fiuba.tpProfesional.paciente.Paciente;
import grails.converters.JSON

/**
 * Encargado de proveer una representacion adecuada de un paciente en formato JSON.
 * @author sebastian
 *
 */
class PacienteMarshaller {
	
	void register() {
		JSON.registerObjectMarshaller( Paciente) { Paciente paciente ->
			return [
					id : paciente.id,
					dni: paciente.dni,
					sexo: paciente.sexo,
					nombreYApellido: paciente.nombreYApellido,
					direccion: paciente.direccion,
					telefono: paciente.telefono,
					antecedentesFamiliares: paciente.antecedentesFamiliares,
					observaciones: paciente.observaciones,
					internaciones: paciente.internaciones
			]
		}
	}
}
