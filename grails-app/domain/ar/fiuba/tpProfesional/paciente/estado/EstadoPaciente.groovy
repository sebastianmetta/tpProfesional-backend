package ar.fiuba.tpProfesional.paciente.estado

abstract class EstadoPaciente {

	Date fecha
	String descripcion
	String observaciones
	
    static constraints = {
		fecha blank:false, nullable:false
    }
}
