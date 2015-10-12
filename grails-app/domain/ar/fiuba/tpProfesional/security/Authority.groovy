package ar.fiuba.tpProfesional.security

class Authority {

	public enum AuthorityType{
		R1,
		R2,
		R3,
		R4,
		ENFERMERO,
		ADMINISTRATIVO,
		JEFE_RESIDENTE,
		MEDICO_CONSULTOR
	}
	
	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false
	}
}
