package todoapp

class UrlMappings {


    static mappings = {
        "/soap/$action/$id?"(controller: "dashapi")
        "/api/task/$action/$id?"(controller: "task")
        "/api/graph/$action/$id?"(controller: "graph")
        "/api/label/$action/$id?"(controller: "label")
        "/api/settings/$action/$id?"(controller: "settings")
        "/api/user/$action/$id?"(controller: "user")

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
