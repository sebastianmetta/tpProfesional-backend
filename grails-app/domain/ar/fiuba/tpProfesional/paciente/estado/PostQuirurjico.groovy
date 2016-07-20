package ar.fiuba.tpProfesional.paciente.estado

import ar.fiuba.tpProfesional.usuario.Medico;

class PostQuirurjico extends EstadoPaciente {

	Medico medicoResponsable
	String resultadoOperacion
	
    static constraints = {
		medicoResponsable nullable:false, blank:false
		resultadoOperacion blank:false, nullable:false 
    }
}
