package model;

/**
 * Flight Connection
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public class FlightConnection {

   /* Attribute */
   /** The name of the airline operating this connection. */
   public String airline;
   /** The unique identifier of this connection (e.g. flight number). */
   public String connectionId;
   /** The IATA code of the departure airport. */
   public String departureAirport;
   /** The IATA code of the arrival airport. */
   public String arrivalAirport;

}
