
import grails.util.Environment;
import ar.fiuba.tpProfesional.Persona
import ar.fiuba.tpProfesional.security.Authority
import ar.fiuba.tpProfesional.security.Authority.AuthorityType;
import ar.fiuba.tpProfesional.security.User
import ar.fiuba.tpProfesional.security.UserAuthority

class BootStrap {

	def init = { servletContext ->

		Locale defaultLocale = new Locale("es","ES")
		java.util.Locale.setDefault(defaultLocale)
		
		log.info('Inicializando datos de la aplicación...')

		Authority.findAll().each { it.delete(flush:true, failOnError:true) }
		
		//Roles disponibles
		def autJefeResidente = new Authority(authority:AuthorityType.JEFE_RESIDENTE)
		autJefeResidente.save(flush: true)
		def autAdministrativo = new Authority(authority:AuthorityType.ADMINISTRATIVO)
		autAdministrativo.save(flush: true)
		def autEnfermero = new Authority(authority:AuthorityType.ENFERMERO)
		autEnfermero.save(flush: true)
		def autJefePlanta = new Authority(authority:AuthorityType.JEFE_PLANTA)
		autJefePlanta.save(flush: true)
		def autR1 = new Authority(authority:AuthorityType.R1)
		autR1.save(flush: true)
		def autR2 = new Authority(authority:AuthorityType.R2)
		autR2.save(flush: true)
		def autR3 = new Authority(authority:AuthorityType.R3)
		autR3.save(flush: true)
		def autR4 = new Authority(authority:AuthorityType.R4)
		autR4.save(flush: true)


		new Persona(nombre:"Juan",apellido:"Perez").save()
		new Persona(nombre:"Carlos",apellido:"La mona Gimenez").save()
		new Persona(nombre:"Roberto",apellido:"Gomez Bolaños").save()

		//Usuarios disponibles
		def userJefeResidente = new User(username:"jeferesidente", password:"jeferesidente999", dni:"11.111.111", email:"mail1@gmail.com", nombreYApellido:"Usuario Jefe de residentes", telefono:"11-1111-1111")
		userJefeResidente.save()
		def userAdministrativo = new User(username:"administrativo", password:"administrativo999", dni:"22.222.222", email:"mail2@gmail.com", nombreYApellido:"Ususario Administrativo", telefono:"22-2222-2222")
		userAdministrativo.save()
		def userEnfermero = new User(username:"enfermero", password:"enfermero999", dni:"33.333.333", email:"mail3@gmail.com", nombreYApellido:"Usuario Enfermero", telefono:"33-3333-3333")
		userEnfermero.save()
		def userJefePlanta = new User(username:"jefeplanta", password:"jefeplanta999", dni:"44.444.444", email:"mail4@gmail.com", nombreYApellido:"Usuario Jefe de planta", telefono:"44-4444-4444")
		userJefePlanta.save()
		def userR1 = new User(username:"residente1", password:"residente1999", dni:"55.555.555", email:"mail5@gmail.com", nombreYApellido:"Usuario Residente tipo R1", telefono:"55-5555-5555")
		userR1.save()
		def userR2 = new User(username:"residente2", password:"residente2999", dni:"66.666.666", email:"mail6@gmail.com", nombreYApellido:"Usuario Residente tipo R2", telefono:"66-6666-6666")
		userR2.save()
		def userR3 = new User(username:"residente3", password:"residente3999", dni:"77.777.777", email:"mail7@gmail.com", nombreYApellido:"Usuario Residente tipo R3", telefono:"77-7777-7777")
		userR3.save()
		def userR4 = new User(username:"residente4", password:"residente4999", dni:"88.888.888", email:"mail8@gmail.com", nombreYApellido:"Usuario Residente tipo R4", telefono:"88-8888-8888")
		userR4.save()

		//Le asignamos roles a los usuarios.
		new UserAuthority(user:userJefeResidente, authority:autJefeResidente).save(flush: true)
		new UserAuthority(user:userAdministrativo, authority:autAdministrativo).save(flush: true)
		new UserAuthority(user:userEnfermero, authority:autEnfermero).save(flush: true)
		new UserAuthority(user:userJefePlanta, authority:autJefePlanta).save(flush: true)
		new UserAuthority(user:userR1, authority:autR1).save(flush: true)
		new UserAuthority(user:userR2, authority:autR2).save(flush: true)
		new UserAuthority(user:userR3, authority:autR3).save(flush: true)
		new UserAuthority(user:userR4, authority:autR4).save(flush: true)



	}
	
	def destroy = {
	}
}
