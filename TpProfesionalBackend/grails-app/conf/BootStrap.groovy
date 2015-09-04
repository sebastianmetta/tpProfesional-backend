import ar.fiuba.tpProfesional.Persona

class BootStrap {

    def init = { servletContext ->

	new Persona(nombre:"Javier",apellido:"Rodriguez Vázquez").save()
        new Persona(nombre:"Maximiliano",apellido:"Roitman").save()
	new Persona(nombre:"Sebastian",apellido:"Metta").save()

    }
    def destroy = {
    }
}
