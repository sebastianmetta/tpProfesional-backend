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
		
		"/api/paciente"(controller:"paciente", parseRequest:true) {
			action = [GET:"list", POST:"save", DELETE:"notAllowed", PUT:"update"]
		}
		"/api/paciente/$id?"(controller: "paciente",parseRequest:true) {
			action = [GET:"show", PUT:"update", DELETE:"delete"]
		}
		"/api/paciente/filtro"(controller:"paciente", parseRequest:true) {
			action = [GET:"notAllowed", POST:"find", DELETE:"notAllowed", PUT:"notAllowed"]
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
