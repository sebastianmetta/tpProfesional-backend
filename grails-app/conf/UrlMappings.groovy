class UrlMappings {

	static mappings = {
		"/api/status"(controller:"status", action:"index", method:"GET")

		// RESTService api
		"/registrar"(controller:"registro",parseRequest:true) {
			action = [GET:"list", POST:"save"]
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
