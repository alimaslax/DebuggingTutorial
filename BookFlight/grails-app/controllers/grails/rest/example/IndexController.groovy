package grails.rest.example

import grails.core.GrailsApplication
import grails.rest.example.SingleFlightCommand
import grails.util.Environment

class IndexController {

    GrailsApplication grailsApplication

    SingleFlightCommand cmd

    def index() {
        render(contentType: 'application/json') {
            message = "Welcome to Grails!"
            environment = Environment.current.name
            appversion = grailsApplication.metadata['info.app.version']
            grailsversion = grailsApplication.metadata['info.app.grailsVersion']
            appprofile = grailsApplication.config.grails?.profile
            groovyversion = GroovySystem.getVersion()
            jvmversion = System.getProperty('java.version')
            controllers = array {
                for (c in grailsApplication.controllerClasses) {
                    controller([name: c.fullName])
                }
            }
            plugins = array {
                for (p in grailsApplication.mainContext.pluginManager.allPlugins) {
                    plugin([name: p.fullName])
                }
            }
        }
    }
    def show(SingleFlightCommand cmd) {
        def test = params
        int i = 0
        int j = 0
        if(cmd.hasErrors()){
            render(contentType: 'application/json'){
                message = "${cmd.errors.getAllErrors()[0].field} is invalid"
                environment = Environment.current.name
                appversion = grailsApplication.metadata['info.app.version']
                grailsversion = grailsApplication.metadata['info.app.grailsVersion']
            }
        }
        render(contentType: 'application/json') {
            message = "Flight is booked"

        }
    }
}