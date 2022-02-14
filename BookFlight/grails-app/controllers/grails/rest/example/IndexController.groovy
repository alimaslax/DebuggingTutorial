package grails.rest.example

import grails.converters.JSON
import grails.rest.example.Flight
import grails.core.GrailsApplication
import grails.rest.example.SingleFlightCommand
import grails.rest.example.SearchFlightCommand
import grails.util.Environment
import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray

class IndexController {

    GrailsApplication grailsApplication

    SearchFlightCommand cmd

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
    def show() {
        //fetch flights from db
        ArrayList<Flight> flights = new ArrayList<Flight>()
        def file = new File("./grails-app/controllers/grails/rest/example/flights.json")
        def db = new JsonSlurper().parse(file.newInputStream());

        //create in-memory data
        for(flight in db){
            def json = groovy.json.JsonOutput.toJson(flight)
            flights.add(new Flight(JSON.parse(json)))
        }

        render(contentType: 'application/json; charset=utf-8') {
            flights = flights as JSONArray;
        }

    }

    def save(SearchFlightCommand cmd){
        render(contentType: 'application/json; charset=utf-8') {
            id = cmd.id
            isBooked = true
        }
    }
    def search(SearchFlightCommand cmd) {
        //fetch flights from db
        ArrayList<Flight> flights = new ArrayList<Flight>()
        def file = new File("./grails-app/controllers/grails/rest/example/flights.json")
        def db = new JsonSlurper().parse(file.newInputStream());
        //create in-memory data
        for(flight in db){
            def json = groovy.json.JsonOutput.toJson(flight)
            flights.add(new Flight(JSON.parse(json)))
        }

        //filter through results
        flights = flights.findAll {
            Date flightDate = Date.parse("yyyy/MM/dd",it.date)
            flightDate > cmd.startDate && flightDate < cmd.endDate
        }
        //render response
        render(contentType: 'application/json; charset=utf-8') {
            flights = flights as JSONArray;
        }
    }

    private List<SingleFlightCommand> fetchFromDatabase(){
        [
                new SingleFlightCommand(id:1, date:"08/2021", user:"mali", departure:"KCMS",arrival:"KBOS"),
                new SingleFlightCommand(id:1, date:"09/2021", user:"mali", departure:"KCMS",arrival:"KBOS"),
                new SingleFlightCommand(id:1, date:"10/2021", user:"mali", departure:"KCMS",arrival:"KBOS"),
                new SingleFlightCommand(id:1, date:"11/2021", user:"mali", departure:"KCMS",arrival:"KBOS"),
                new SingleFlightCommand(id:1, date:"12/2021", user:"mali", departure:"KCMS",arrival:"KBOS"),
                new SingleFlightCommand(id:1, date:"01/2022", user:"mali", departure:"KCMS",arrival:"KBOS"),
                new SingleFlightCommand(id:1, date:"02/2022", user:"mali", departure:"KCMS",arrival:"KBOS")
        ]
    }
    private List<SingleFlightCommand> filterByDate(ArrayList<SingleFlightCommand> flts){
        try {
            return flts.findResults {
                true
            }
        }
        catch(Exception e){
            return []
        }
    }
}