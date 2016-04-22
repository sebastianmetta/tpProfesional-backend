package ar.fiuba.tpProfesional.paciente

import ar.fiuba.tpProfesional.usuario.Administrativo;

class AltaPaciente {

	Date fechaAlta
	Administrativo administrativo
	MotivoAlta motivoAlta
	String indicaciones
	String observaciones
	//TODO: Modelar conformidad de alta.
	
	//Entidad débil.
	static belongsTo = InternacionPaciente
	
    static constraints = {
    }
}
