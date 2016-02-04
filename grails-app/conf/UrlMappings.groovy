class UrlMappings {

	static mappings = {
		"/rol"(controller:"rol") {
			action = [GET:"notAllowed", POST:"notAllowed", DELETE:"notAllowed", PUT:"notAllowed", OPTIONS:"notAllowed", HEAD:"notAllowed", TRACE:"notAllowed", CONNECT:"notAllowed"]
		}
		"/registro"(controller:"registro") {
			action = [GET:"notAllowed", POST:"notAllowed", DELETE:"notAllowed", PUT:"notAllowed", OPTIONS:"notAllowed", HEAD:"notAllowed", TRACE:"notAllowed", CONNECT:"notAllowed"]
		}
		
		// RESTService api
		"/api/status"(controller:"status", action:"index", method:"GET")

		"/api/registro"(controller:"registro") {
			action = [POST:"save", DELETE:"notAllowed", PUT:"notAllowed", GET:"notAllowed", OPTIONS:"notAllowed", HEAD:"notAllowed", TRACE:"notAllowed", CONNECT:"notAllowed"]
		}
		"/api/rol"(controller:"rol",parseRequest:true) {
			action = [GET:"list", POST:"notAllowed", DELETE:"notAllowed", PUT:"notAllowed", OPTIONS:"notAllowed", HEAD:"notAllowed", TRACE:"notAllowed", CONNECT:"notAllowed"]
		}
		"/api/usuario"(controller:"usuario",parseRequest:true) {
			action = [GET:"list"]
		}
		
		"/api/paciente"(controller:"paciente", parseRequest:true) {
			action = [GET:"list", POST:"save", DELETE:"notAllowed", PUT:"update", OPTIONS:"notAllowed", HEAD:"notAllowed", TRACE:"notAllowed", CONNECT:"notAllowed"]
		}
		"/api/paciente/$id?"(controller: "paciente",parseRequest:true) {
			action = [GET:"show", PUT:"update", DELETE:"delete"]
		}
		
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }
		
        "/"(view:"/index")
        "500"(view:'/error')
	}
}
