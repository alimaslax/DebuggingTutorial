package grails.rest.example

import grails.validation.Validateable

class SingleFlightCommand implements Validateable {

    Long id

    static constraints = {
        id nullable: false, validator: { val, obj, errors -> obj.validateId(val,obj,errors) }
    }

    //START VALIDATION
    boolean validateId(val, obj, errors){
        try{
            boolean isValid = true;
            if(val == null)
                isValid = false;
            if(val == 0)
                isValid = false;
            isValid
        } catch(Exception e){
            errors.rejectValue("id", "Invalid VALUE")
            return false
        }
    }
}