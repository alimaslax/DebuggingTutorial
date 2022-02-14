package grails.rest.example

import grails.validation.Validateable

class SearchFlightCommand implements Validateable {

    String dateRange
    Long id
    Date startDate
    Date endDate

    static constraints = {
        dateRange nullable: true, validator: { val, obj, errors -> obj.validateDateRange(val,obj,errors) }
    }

    boolean validateDateRange(String val, obj, errors){
        try{
            if(!val)
                return true
            ArrayList<String> dateRanges = new ArrayList<>(Arrays.asList(val.split("-")));
            startDate = Date.parse("MM/yyyy",dateRanges[0])
            endDate = Date.parse("MM/yyyy",dateRanges[1])
            assert startDate < endDate
        } catch(Exception e){
            errors.rejectValue("date", "WRONG VALUE")
            return false
        }
    }

}