class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        // Map to HTTP methods.
        "/show"(controller: 'index') {
            params = "Order"
            action = [GET: 'show']
        }

        "/"(controller: 'index')
        "500"(controller: 'InternalServerError')
        "404"(controller: 'NotFound')
    }
}