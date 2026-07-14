package model;

import java.time.LocalDate;

/**
 * Flight
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public class Flight {

   /* Attribute */
   /** The flight connection this flight operates on. */
   public FlightConnection flightConnection;
   /** The date on which this flight departs. */
   public LocalDate flightDate;
   /** The ticket price for this flight in Euro. */
   public double flightPriceInEuro;

}
