class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        // Map to HTTP methods.
        "/showAll"(controller: 'index') {
            action = [GET: 'show']
        }
        // Map to HTTP methods.
        "/search"(controller: 'index') {
            action = [GET: 'search']
        }
        "/save"(controller: 'index') {
            action = [POST: 'save']
        }

        "/"(controller: 'index')
        "500"(controller: 'InternalServerError')
        "404"(controller: 'NotFound')
    }
}