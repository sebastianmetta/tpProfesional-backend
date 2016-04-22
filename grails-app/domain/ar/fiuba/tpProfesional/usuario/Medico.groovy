package ar.fiuba.tpProfesional.usuario

import ar.fiuba.tpProfesional.infraestructura.Cama;
import ar.fiuba.tpProfesional.infraestructura.Servicio;
import ar.fiuba.tpProfesional.paciente.Paciente;
import ar.fiuba.tpProfesional.security.User;

class Medico extends User {

	List<Cama> camas = new ArrayList()
	ClasificacionMedico clasificacion
	Servicio servicio
	
	static hasMany = [camas: Cama]
	
	static constraints = {
		clasificacion blank:false, nullable:false
		servicio blank:false, nullable:false
		}
	
	def diagnosticarPaciente(Paciente paciente){
		
	}
	
	def modificarEstadoPaciente(Paciente paciente) {
		
	}
	
	def darDeAltaPaciente(Paciente paciente){
		
	}
	
	def solicitarInterconsulta(Paciente paciente) {
		
	}
	
	def registrarPaseDeGuardia() {
		
	}
	
	def registrarInterconsulta(Paciente paciente) {
		
	}
}
