package ar.fiuba.tpProfesional

import org.codehaus.groovy.grails.web.errors.GrailsWrappedRuntimeException;

import grails.converters.JSON;
import grails.rest.RestfulController;

class ErrorController extends RestfulController {

    def index() {

		def exception = request.getAttribute('exception')
		response.status = 500
		if (exception instanceof GrailsWrappedRuntimeException) {
			render exception.cause as JSON
		} else {
			def error = [description: 'El servidor ha experimentado una falla interna. Por favor intente nuevamente o contacte al administrador del sistema si el error persiste.']
			render error as JSON
		}
    }
}
