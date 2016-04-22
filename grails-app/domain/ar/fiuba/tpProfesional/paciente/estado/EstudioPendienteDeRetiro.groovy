package ar.fiuba.tpProfesional.paciente.estado

import ar.fiuba.tpProfesional.usuario.Medico

class EstudioPendienteDeRetiro extends EstadoPaciente {

	//Medico responsable de retirar el estudio. Generalmente R1.
	Medico responsable

	static constraints = {
		responsable blank:false,nullable:false
	}
}
