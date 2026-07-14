package main;

import java.time.LocalDate;

import model.AlreadyPluggedInException;
import model.Flight;
import model.FlightConnection;
import model.LightBulb;
import model.PersonClass;
import model.PersonRecord;
import model.StringArray;
import model.TableLight;
import utility.StringArrayHelper;

/**
 * Demo 2: Object Oriented Programming
 *
 * <p>Demonstrates classes with public fields, static utility helpers vs. instance methods,
 * encapsulation via getters/setters, and data classes (plain class, record, Lombok).
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public class D02_ObjectOrientedProgramming {

   /**
    * Entry point — runs all OOP demos sequentially.
    *
    * @param args command-line arguments (not used)
    */
   public static void main(String[] args) {
      /* Attribute */
      FlightConnection connection1 = new FlightConnection();
      connection1.airline = "LH";
      connection1.connectionId = "0400";
      connection1.departureAirport = "FRA";
      connection1.arrivalAirport = "JFK";

      Flight flight1 = new Flight();
      flight1.flightConnection = connection1;
      flight1.flightDate = LocalDate.of(2026, 4, 25);
      flight1.flightPriceInEuro = 489.12;

      Flight flight2 = new Flight();
      flight2.flightConnection = connection1;
      flight2.flightDate = LocalDate.of(2026, 5, 18);
      flight2.flightPriceInEuro = 788.88;

      System.out.println(connection1);
      System.out.println(flight1);
      System.out.println(flight2);

      /* Methoden */
      String[] names = new String[2];

      names = StringArrayHelper.add(names, "Hans");
      names = StringArrayHelper.add(names, "Peter");
      names = StringArrayHelper.add(names, "Lisa");
      StringArrayHelper.print(names);

      StringArray names2 = new StringArray();
      names2.array = names;
      names2.add("Heidi");
      names2.add("Max");
      names2.print();

      /* Objekte */
      LightBulb redLightBulb = new LightBulb("Rot");

      System.out.println(TableLight.DESCRIPTION);

      TableLight light1 = new TableLight();
      System.out.println(light1.isShining() ? light1.getLightBulb().getColor() : "dunkel");
      light1.changeLightBulb(redLightBulb);

      try {
         light1.plugIn();
         light1.plugIn();
      } catch (AlreadyPluggedInException e) {
         System.err.println(e.getMessage());
      }

      light1.switchOn();
      System.out.println(light1.isShining() ? light1.getLightBulb().getColor() : "dunkel");

      /* Die Object-Methoden */
      LightBulb blueLightBulb1 = new LightBulb("Blau");
      LightBulb blueLightBulb2 = new LightBulb("Blau");

      System.out.println(blueLightBulb1 == blueLightBulb2);
      System.out.println(blueLightBulb1.equals(blueLightBulb2));

      System.out.println(blueLightBulb1);

      System.out.println(blueLightBulb1.hashCode());
      System.out.println(blueLightBulb2.hashCode());

      /* Datenklassen */
      PersonClass person1 = new PersonClass("Hans", 23, 'x');
      PersonClass person2 = new PersonClass("Hans", 23, 'x');
      System.out.println(person1.equals(person2));
      System.out.println(person1);
      System.out.println(person1.hashCode());
      System.out.println(person2.hashCode());
      person1.setAge(person1.getAge() + 1);

      PersonRecord person3 = new PersonRecord("Hans", 23, 'x');
      PersonRecord person4 = new PersonRecord("Hans", 23, 'x');
      System.out.println(person3.equals(person4));
      System.out.println(person3);
      System.out.println(person3.hashCode());
      System.out.println(person4.hashCode());
      // person3.setAge(person3.age() + 1);
   }

}
