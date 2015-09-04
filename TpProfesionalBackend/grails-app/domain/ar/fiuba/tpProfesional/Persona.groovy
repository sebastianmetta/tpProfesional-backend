package ar.fiuba.tpProfesional

import grails.rest.*

@Resource(uri='/persona', formats=['json', 'xml'])
class Persona {

    String nombre
    String apellido

    static constraints = {
	nombre blanck:false
    }

}
