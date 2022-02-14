package grails.rest.example

import grails.validation.Validateable
import java.util.regex.Matcher
import java.util.regex.Pattern

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
        user blank:false, validator: { val, obj, errors -> obj.validateUser(val,obj,errors) }
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

    boolean validateUser(String val, obj, errors){
        try{
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(val);
            boolean foundSpecialChar = m.find();
            if(foundSpecialChar){
                errors.rejectValue("user", "not valid user")
            }

        } catch(Exception e){
            errors.rejectValue("user", "not valid user")
        }
        return true;
    }

    SingleFlightCommand buildDB(Long id, String user, String departure, String arrival, String date){
        def flight = new SingleFlightCommand()
        flight.user = user
        flight.departure = departure
        flight.arrival = arrival
        flight.date = date
        flight.id = id
        return flight
    }
}