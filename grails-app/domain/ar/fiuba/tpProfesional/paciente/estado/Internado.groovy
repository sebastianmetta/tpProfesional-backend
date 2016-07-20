package ar.fiuba.tpProfesional.paciente.estado

import ar.fiuba.tpProfesional.usuario.Administrativo


class Internado extends EstadoPaciente {

	Administrativo internadoPor
	
    static constraints = {
		internadoPor blank:false, nullable:false
    }
}
