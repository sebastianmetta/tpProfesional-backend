package ar.fiuba.tpProfesional.paciente.estado

import ar.fiuba.tpProfesional.usuario.Medico

class Interconsulta extends EstadoPaciente {

	Medico medicoSolicitante
	Medico medicoConsultado
	String resultado
	String indicaciones

    static constraints = {
		medicoSolicitante nullable:false, blank:false
		medicoConsultado nullable:false, blank:false
		resultado nullable:false, blank:false
		indicaciones nullable:false, blank:false
    }
}
