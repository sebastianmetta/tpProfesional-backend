import ar.fiuba.tpProfesional.Persona
import ar.fiuba.tpProfesional.security.Authority
import ar.fiuba.tpProfesional.security.Person
import ar.fiuba.tpProfesional.security.PersonAuthority

class BootStrap {

    def init = { servletContext ->

	new Persona(nombre:"Juan",apellido:"Perez").save()
	new Persona(nombre:"Carlos",apellido:"La mona Gimenez").save()
	new Persona(nombre:"Roberto",apellido:"Gomez Bolaños").save()
	
	def person =new Person(username:"test", password:"test123")
	person.save()
	
	def roleUser=new Authority(authority:"ROLE_USER")
	roleUser.save()
	
	new PersonAuthority(person:person, authority:roleUser).save()
	
    }
    def destroy = {
    }
}
