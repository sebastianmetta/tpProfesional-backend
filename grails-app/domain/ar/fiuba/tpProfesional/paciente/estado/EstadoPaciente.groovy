package ar.fiuba.tpProfesional.paciente.estado

import ar.fiuba.tpProfesional.paciente.InternacionPaciente;

class EstadoPaciente {

	Date fecha = new Date()
	String descripcion
	String observaciones
	
	//Entidad débil.
	static belongsTo = InternacionPaciente
	
    static constraints = {
		fecha blank:false, nullable:false
    }
}
