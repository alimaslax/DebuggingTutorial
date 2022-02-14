export const getFlights = () => ({
  type: 'GET_FLIGHTS',
});

export const findFlights = (payload) => ({
  type: 'GET_ROUTES',
  payload
});

export const bookFlight = (payload) => ({
  type: 'BOOK_FLIGHT',
  payload
});