package ar.fiuba.tpProfesional

import grails.rest.*

@Resource()
class Persona {

    String nombre
    String apellido

    static constraints = {
	nombre blanck:false
    }

}
