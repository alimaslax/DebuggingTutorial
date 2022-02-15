package grails.rest.example

import grails.validation.Validateable

class SearchFlightCommand implements Validateable {

    String dateRange
    String startDate
    String endDate

    static constraints = {
        dateRange nullable: true, validator: { val, obj, errors -> obj.validateDateRange(val,obj,errors) }
        startDate nullable: true, validator: { val, obj, errors -> obj.validateDate(val,obj,errors) }
        endDate nullable: true, validator: { val, obj, errors -> obj.validateDate(val,obj,errors) }
    }

    boolean validateDateRange(val, obj, errors){
        try{
            boolean isValid = true;
            if(!val)
                return isValid
            ArrayList<String> dateRanges = new ArrayList<>(Arrays.asList(val.split("-")));
            assert Date.parse("MM/yyyy",dateRanges[0]) < Date.parse("MM/yyyy",dateRanges[1])
        } catch(Exception e){
            errors.rejectValue("date", "Invalid VALUE")
            return false
        }
    }


    boolean validateDate(val, obj, errors){
        try{
            boolean isValid = true
            if(val instanceof Date)
                return isValid
            Date.parse("MM/yyyy",val)
            return isValid
        } catch(Exception e){
            errors.rejectValue("id", "Invalid VALUE")
            return false
        }
    }
}