package grails.rest.example

class Flight {

    Long id
    String arrivalTime
    String date
    String departureTime
    String destination
    String flightNo
    String name
    String origin
    Long price

    static mapping = {
        version false
        table 'orders'
        id column: 'id', generator:'native', params:[sequence:'order_seq']
    }

    static constraints = {
        arrivalTime blank:false
        date blank:false
        departureTime blank:false
        destination blank:false
        flightNo blank:false
        name blank:false
        origin blank:false
        price blank:false
    }
}
