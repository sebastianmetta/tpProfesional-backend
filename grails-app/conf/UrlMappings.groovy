class UrlMappings {

	static mappings = {
		"/api/status"(controller:"status", action:"index", method:"GET")

		// RESTService api
		"/registro"(controller:"registro",parseRequest:true) {
			action = [POST:"save"]
		}
		"/rol"(controller:"rol",parseRequest:true) {
			action = [GET:"list"]
		}
		"/usuario"(controller:"usuario",parseRequest:true) {
			action = [GET:"list"]
		}
		"/api/personas"(resources:"persona")
				
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }
		
        "/"(view:"/index")
        "500"(view:'/error')
	}
}
