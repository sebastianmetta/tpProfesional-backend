import ar.fiuba.tpProfesional.Persona
import ar.fiuba.tpProfesional.security.Authority
import ar.fiuba.tpProfesional.security.Authority.AuthorityType;
import ar.fiuba.tpProfesional.security.User
import ar.fiuba.tpProfesional.security.UserAuthority

class BootStrap {

	def init = { servletContext ->

		new Persona(nombre:"Juan",apellido:"Perez").save()
		new Persona(nombre:"Carlos",apellido:"La mona Gimenez").save()
		new Persona(nombre:"Roberto",apellido:"Gomez Bolaños").save()

		//Roles disponibles
		def autJefeResidente = new Authority(authority:AuthorityType.JEFE_RESIDENTE)
		autJefeResidente.save()
		def autAdministrativo = new Authority(authority:AuthorityType.ADMINISTRATIVO)
		autAdministrativo.save()
		def autEnfermero = new Authority(authority:AuthorityType.ENFERMERO)
		autEnfermero.save()
		def autMedicoConsultor = new Authority(authority:AuthorityType.MEDICO_CONSULTOR)
		autMedicoConsultor.save()
		def autR1 = new Authority(authority:AuthorityType.R1)
		autR1.save()
		def autR2 = new Authority(authority:AuthorityType.R2)
		autR2.save()
		def autR3 = new Authority(authority:AuthorityType.R3)
		autR3.save()
		def autR4 = new Authority(authority:AuthorityType.R4)
		autR4.save()
		
		//Usuarios disponibles
		def userJefeResidente = new User(username:"jeferesidente", password:"jeferesidente999", direccion:"Direccion", email:"mail@gmail.com")
		userJefeResidente.save()
		def userAdministrativo = new User(username:"administrativo", password:"administrativo999", direccion:"Direccion", email:"mail@gmail.com")
		userAdministrativo.save()
		def userEnfermero = new User(username:"enfermero", password:"enfermero999", direccion:"Direccion", email:"mail@gmail.com")
		userEnfermero.save()
		def userMedicoConsultor = new User(username:"medicoconsultor", password:"medicoconsultor999", direccion:"Direccion", email:"mail@gmail.com")
		userMedicoConsultor.save()
		def userR1 = new User(username:"residente1", password:"residente1999", direccion:"Direccion", email:"mail@gmail.com")
		userR1.save()
		def userR2 = new User(username:"residente2", password:"residente2999", direccion:"Direccion", email:"mail@gmail.com")
		userR2.save()
		def userR3 = new User(username:"residente3", password:"residente3999", direccion:"Direccion", email:"mail@gmail.com")
		userR3.save()
		def userR4 = new User(username:"residente4", password:"residente4999", direccion:"Direccion", email:"mail@gmail.com")
		userR4.save()
		
		//Le asignamos roles a los usuarios.
		new UserAuthority(user:userJefeResidente, authority:autJefeResidente).save(flush: true)
		new UserAuthority(user:userAdministrativo, authority:autAdministrativo).save(flush: true)
		new UserAuthority(user:userEnfermero, authority:autEnfermero).save(flush: true)
		new UserAuthority(user:userMedicoConsultor, authority:autMedicoConsultor).save(flush: true)
		new UserAuthority(user:userR1, authority:autR1).save(flush: true)
		new UserAuthority(user:userR2, authority:autR2).save(flush: true)
		new UserAuthority(user:userR3, authority:autR3).save(flush: true)
		new UserAuthority(user:userR4, authority:autR4).save(flush: true)
		
		
		
	}
	def destroy = {
	}
}
