package ar.fiuba.tpProfesional.infraestructura

import java.util.List;

class Habitacion {

	int numero
	Sector sector
	List<Cama> camas = new ArrayList() 
	
	static hasMany = [camas: Cama]
	
	static belongsTo = [ sector: Sector]
	
	static mapping = { camas cascade:"all-delete-orphan" }
	
    static constraints = {
		numero blank:false, nullable:false, unique:true
		sector nullable:false, blank:false
    }
}
