package ar.fiuba.tpProfesional.paciente.estado

import ar.fiuba.tpProfesional.usuario.Medico


class EstudioRetirado extends EstadoPaciente{

	Medico retiradoPor
	//TODO: Ver como se transmiten los archivos por la api. Alternativa: links a drive.
	//TODO: Revisar por que no compila esto.
	//List<File> archivosEstudio = new ArrayList()
	
	//static hasMany = [archivosEstudio: File] 
	
    static constraints = {
		retiradoPor blank:false, nullable:false 
    }
}
