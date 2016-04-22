package ar.fiuba.tpProfesional.paciente.estado

import ar.fiuba.tpProfesional.usuario.Medico;

class EstudioSolicitado extends EstadoPaciente {

	Medico medicoSolicitante
	String estudio
	
    static constraints = {
		medicoSolicitante blank:false, nullable:false
		estudio blank:false, nullable:false
    }
}
