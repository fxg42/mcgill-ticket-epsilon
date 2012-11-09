class UrlMappings {

	static mappings = {
    "/api/developer/$id?"(resource:"developer")

		"/$controller/$action?/$id?" {
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
