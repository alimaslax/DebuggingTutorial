package grails.rest.example

import grails.converters.JSON
import grails.rest.*
import grails.validation.Validateable

class SingleFlightCommand implements Validateable {

    Long id
    String user
    String departure
    String arrival
    String date

    static mapping = {
        version false
        table 'orders'
        id column: 'id', generator:'native', params:[sequence:'order_seq']
    }

    static constraints = {
        user blank:false
        departure blank:false
        arrival blank:false
        date nullable:false, validator: { val, obj, errors -> obj.validateDate(val,obj,errors) }
    }

    boolean validateDate(String val, obj, errors){
        try{
            Date.parse("MM/YYYY",val)
        } catch(Exception e){
            errors.rejectValue("date", "WRONG VALUE")
        }
        return true;
    }

}