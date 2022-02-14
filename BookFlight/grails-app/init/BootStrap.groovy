import grails.rest.example.SingleFlightCommand


class BootStrap {

    def init = { servletContext ->
        new SingleFlightCommand(id:1, date:"12/2022", user:"mali", departure:"KCMS",arrival:"KBOS")
    }
    def destroy = {
    }
}
