import React from 'react';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import { DetailLabel } from './../detail-label/detail-label';
import { PriceInfo } from './../price-info/price-info';
import nonStopFlightLogo from './../../assets/nonstop.png';
import { getTimeDifferece } from './../../lib/utils';
import { bookFlight } from './../../actions';

import './flight-info.css';

const FlightLogo = (props) => {
  return <img src={nonStopFlightLogo} width="32" height="32"></img>
}



export const FlightInfo = (props) => {
  const { id, name, flightNo, departureTime, origin, arrivalTime, destination, price, date  } = props.data;
  const isMultiMode = props.isMultiMode;
  const bookFlight = props.bookFlight;
  const flightRequest = props.flightRequest;
  const timeDiff = new Date(`${date} ${arrivalTime}`) - new Date(`${date} ${departureTime}`);

  const handleBook = () => {
    console.log("GOT HERE")
    bookFlight(id)
    console.log(flightRequest)
  } 

  return (
    <Card >
      <section className={`Flight-info ${isMultiMode ? 'gray-background' : ''}`}>
        <FlightLogo></FlightLogo>
        <DetailLabel mainText={name} subText={flightNo} ></DetailLabel>
        <DetailLabel mainText={departureTime} subText={origin} ></DetailLabel>
        <DetailLabel mainText={arrivalTime} subText={destination} ></DetailLabel>
        <DetailLabel mainText={getTimeDifferece(timeDiff)} subText={isMultiMode ? '' : 'Non stop'} ></DetailLabel>
        {isMultiMode ? null : <PriceInfo amount={price} />}
        {isMultiMode ? null : <Button variant="outline-danger" type="submit" disabled={flightRequest? true:false} onClick={handleBook}>
          {flightRequest? (flightRequest.id == id && flightRequest.isBooked? 'Booked':'Book'):'Book'}
          </Button>}
      </section>
    </Card>
  )
}