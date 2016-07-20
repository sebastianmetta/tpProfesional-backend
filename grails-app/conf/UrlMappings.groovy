class UrlMappings {

	static mappings = {
		"/rol"(controller:"rol") {
			action = [GET:"notAllowed", POST:"notAllowed", DELETE:"notAllowed", PUT:"notAllowed"]
		}
		"/registro"(controller:"registro") {
			action = [GET:"notAllowed", POST:"notAllowed", DELETE:"notAllowed", PUT:"notAllowed"]
		}
		
		"/api/status"(controller:"status", action:"index", method:"GET")

		"/api/registro"(controller:"registro") {
			action = [POST:"save", DELETE:"notAllowed", PUT:"notAllowed", GET:"notAllowed"]
		}
		"/api/rol"(controller:"rol",parseRequest:true) {
			action = [GET:"list", POST:"notAllowed", DELETE:"notAllowed", PUT:"notAllowed"]
		}
		"/api/usuario"(controller:"usuario",parseRequest:true) {
			action = [GET:"list"]
		}
		
		//PACIENTES
		"/api/paciente"(controller:"paciente", parseRequest:true) {
			action = [GET:"list", POST:"save", DELETE:"notAllowed", PUT:"update"]
		}
		"/api/paciente/$id?"(controller: "paciente",parseRequest:true) {
			action = [GET:"show", PUT:"update", DELETE:"delete"]
		}
		"/api/paciente/filtro"(controller:"paciente", parseRequest:true) {
			action = [GET:"notAllowed", POST:"find", DELETE:"notAllowed", PUT:"notAllowed"]
		}
		"/api/paciente/internacion/filtro"(controller:"paciente", parseRequest:true) {
			action = [GET:"notAllowed", POST:"findByInternacion", DELETE:"notAllowed", PUT:"notAllowed"]
		}
		
		
		//CAMAS
		"/api/cama"(controller:"cama", parseRequest:true) {
			action = [GET:"list", POST:"save", DELETE:"notAllowed", PUT:"update"]
		}
		"/api/cama/$id?"(controller: "cama",parseRequest:true) {
			action = [GET:"show", PUT:"update", DELETE:"delete"]
		}
		"/api/cama/filtro"(controller:"cama", parseRequest:true) {
			action = [GET:"notAllowed", POST:"find", DELETE:"notAllowed", PUT:"notAllowed"]
		}
		
		//HABITACION
		"/api/habitacion"(resources:"habitacion")
		"/api/habitacion/filtro"(controller: "habitacion",parseRequest:true) {
			action = [GET:"notAllowed", POST:"find", DELETE:"notAllowed", PUT:"notAllowed"]
		}
		
		//INTERNACION PACIENTE
		"/api/internacionpaciente"(controller:"internacionPaciente",parseRequest:true) {
			action = [GET:"index", POST:"save", DELETE:"delete", PUT:"update"]
		}
		"/api/internacionpaciente/$id"(controller:"internacionPaciente",parseRequest:true) {
			action = [GET:"show"]
		} 
		
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }
		"500"(controller:"error", action:"index")
		
        "/"(view:"/index")
        
	}
}
