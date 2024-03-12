# DebuggingTutorial
Learn to debug in Java and Node.js

and using git
# Running Both Applications
Learn to debug in Java and Node.js

## BOOKFLIGHT
Grails Version: 3.0.10
grails run-app --debug-jvm

## REACT-FRONTEND
yarn install && yarn start


## Problems and Solutions
### RAPM-1052: owner cannot A book flight
Description: After logging in, an owner cannot book a flight. Their flight order is correct, but the system is not processing their flight request
Given:
    From: Boston To: LA
    Date: 01/04/2023
    Passengers: any
Acceptance Criteria:
    An Owner (mariá) books a flight
    The Flight Request gets sent to our systems
    The Flight Gets into processing state
    200 Status OK Returned
    Flight is in booked state

Solution: 
Problem: ID is not being passed through to the grails Backend
Pass ID to grails backend
    sagas/index.js
        line 47 body: JSON.stringify({id: payload}) -> body: JSON.stringify({id: payload.payload})


### RAPM-1053: Flight Center is having troubles searching Flights
Description: Since release cadence R2023.6: flight center is no longer able to search flights by date.

Given:
StartDate: 01/2023
EndDate: 04/2023
Acceptance Criteria:
Given a start and end Date
All Flights within that date range should return
200 Status OK Returned


Solution: 
Problem: Date parsing is wrong
fix Date parsing for data from DB
    indexController.internalSearch()
     line 116:   Date flightDate = Date.parse("yyyy/MM/dd",it.date)
