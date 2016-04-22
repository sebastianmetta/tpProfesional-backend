package ar.fiuba.tpProfesional.paciente

import ar.fiuba.tpProfesional.usuario.Medico

class Diagnostico {

	Date fecha
	String diagnostico
	Medico medico
	String observaciones
	
	//Entidad débil.
	static belongsTo = InternacionPaciente
	
    static constraints = {
		fecha blank:false, nullable:false
		diagnostico blank:false, nullable:false
		medico blank:false, nullable:false
    }
}
