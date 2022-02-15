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

    def save(SingleFlightCommand cmd){
        if(cmd.hasErrors()){
            def allErrors = cmd.errors.allErrors
            throw new Exception("ERROR 505: flight could not be booked")
        }
        render(contentType: 'application/json; charset=utf-8') {
            id = cmd.id
            isBooked = true
        }
    }
    def search(SearchFlightCommand cmd) {
        if(cmd.hasErrors()){
            def allErrors = cmd.errors.allErrors
            throw new Exception("ERROR 505: search could not be completed")
        }
        //create Date objects
        ArrayList<String> dateRange = new ArrayList<>(Arrays.asList(cmd.dateRange.split("-")));
        Date startDate = Date.parse("MM/yyyy",dateRange[0])
        Date endDate = Date.parse("MM/yyyy",dateRange[1])

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
            flightDate > startDate && flightDate < endDate
        }
        //render response
        render(contentType: 'application/json; charset=utf-8') {
            flights = flights as JSONArray;
        }
    }
    def internalSearch(SearchFlightCommand cmd) {
        if(cmd.hasErrors()){
            def allErrors = cmd.errors.allErrors
            throw new Exception("ERROR 505: search could not be completed")
        }
        //create Date object
        Date startDate = Date.parse("MM/yyyy",cmd.startDate)
        Date endDate = Date.parse("MM/yyyy",cmd.endDate)

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
            Date flightDate = Date.parse("yyyy/MM",it.date)
            flightDate > startDate && flightDate < endDate
        }
        //render response
        render(contentType: 'application/json; charset=utf-8') {
            flights = flights as JSONArray;
        }
    }
}