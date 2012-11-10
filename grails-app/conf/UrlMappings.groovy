class UrlMappings {

	static mappings = {
    "/api/developer/$id?"(resource:"developer")

		"/$controller/$action?/$id?" {
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:'ticket', action:'create')

		"500"(view:'/error')
	}
}
