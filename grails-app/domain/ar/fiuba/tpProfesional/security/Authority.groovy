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
		JEFE_PLANTA,
		CONSULTOR_EXTERNO
	}
	
	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false
	}
}
