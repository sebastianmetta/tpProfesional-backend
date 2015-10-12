package ar.fiuba.tpProfesional

import grails.rest.*


@Resource(formats=['json', 'xml'])
class Persona {

    String nombre
    String apellido

    static constraints = {
	nombre blank:false
    }

}
