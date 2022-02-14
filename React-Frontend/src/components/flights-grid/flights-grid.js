import React from 'react';
import { connect } from 'react-redux';
import './flight-grid.css';
import { FlightSearchInfo } from './../flight-search-info/flight-search-info';
import { FlightInfo } from './../flight-info/flight-info';
import { MultiFlightInfo } from './../multi-flight-info/multi-flight-info';
import { bookFlight } from './../../actions';


const FlightsGrid = (props) => {
  const flights = props.resultFlights || {};
  const flightsCount = (flights.nonStopFlights && flights.nonStopFlights.length) + (flights.multiStopFlights && flights.multiStopFlights.length)
  return (
    <div className="flights-info-container">
      {props.criteria && <FlightSearchInfo criteria={props.criteria} count={flightsCount || 0} />}
      {flights.nonStopFlights && flights.nonStopFlights.map(flight => <FlightInfo flightRequest={props.flightRequest} bookFlight={props.bookFlight}data={flight} />)}
      {flights.multiStopFlights && flights.multiStopFlights.map(flight => <MultiFlightInfo data={flight} />)}
    </div>
  );
}

const mapStateToProps = (state) => ({
  flights: state.flights,
  flightRequest: state.flightRequest
})

const mapDispatchToProps = {
  bookFlight
}

export default connect(mapStateToProps, mapDispatchToProps)(FlightsGrid);

