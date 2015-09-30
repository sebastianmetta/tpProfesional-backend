import ar.fiuba.tpProfesional.Book
import ar.fiuba.tpProfesional.Persona
import ar.fiuba.tpProfesional.security.Authority
import ar.fiuba.tpProfesional.security.Person
import ar.fiuba.tpProfesional.security.PersonAuthority

class BootStrap {

    def init = { servletContext ->

	new Persona(nombre:"Javier",apellido:"Rodriguez Vázquez").save()
        new Persona(nombre:"Maximiliano",apellido:"Roitman").save()
	new Persona(nombre:"Sebastian",apellido:"Metta").save()

	new Book(title:"Java Persistence with Hibernate", author:"Gavin King", price:99.00).save()
	new Book(title:"Spring Live", author:"Matt Raible", price:29.00).save()
	
	def person =new Person(username:"test", password:"test123")
	person.save()
	
	def roleUser=new Authority(authority:"ROLE_USER")
	roleUser.save()
	
	new PersonAuthority(person:person, authority:roleUser).save()
	
    }
    def destroy = {
    }
}
