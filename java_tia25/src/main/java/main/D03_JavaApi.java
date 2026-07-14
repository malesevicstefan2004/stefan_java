package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import model.LightBulb;
import model.PersonRecord;
import model.PlugType;
import model.TableLight;

/**
 * Demo 3: Java API
 *
 * <p>Demonstrates arithmetic and type casting, {@link java.util.Random} pseudo-random numbers,
 * {@link java.util.LinkedList} and {@link java.util.ArrayList} collections, the date/time API
 * ({@link java.time.LocalDate}, {@link java.time.LocalTime}, {@link java.time.LocalDateTime}),
 * system properties, reading data from a file with {@link java.util.Scanner}, and enumerations.
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public class D03_JavaApi {

   /**
    * Entry point — runs all Java-API demos sequentially.
    *
    * @param args command-line arguments (not used)
    * @throws java.io.FileNotFoundException if the persons resource file cannot be found
    */
   public static void main(String[] args) throws FileNotFoundException {
      /* Mathematische Berechnungen */
      int a = 5, b = 3;
      System.out.println(a + b);
      System.out.println(a - b);
      System.out.println(a * b);
      System.out.println((a / b) + " Rest " + (a % b));
      System.out.println((double) a / b);

      int int1 = 5;
      double double1 = 5.8;

      int1 = (int) double1;
      double1 = int1;
      System.out.println(int1);
      System.out.println(double1);

      System.out.println(Math.sqrt(64));

      /* Pseudozufallszahlen */
      Random random = new Random();

      for (int i = 0; i < 100; i++) {
         int randomNumber = random.nextInt(1, 7);
         System.out.println(randomNumber);
      }

      /* Listen */
      LinkedList<String> names = new LinkedList<>();

      names.add("Peter");
      names.add("Hans");
      names.add(1, "Lisa");
      names.addFirst("Heidi");

      System.out.println(names);

      names.remove("Hans");

      for (int i = 0; i < names.size(); i++) {
         String name = names.get(i);
         System.out.println(name.toLowerCase());
      }

      for (String name : names) {
         System.out.println(name.toUpperCase());
      }

      /* Datums- und Zeitangaben */
      ArrayList<Integer> numbers = new ArrayList<>();
      long start = System.currentTimeMillis();
      for (int i = 0; i < 100; i++) {
         int randomNumber = random.nextInt(1, 7);
         numbers.add(0, randomNumber);
      }
      long end = System.currentTimeMillis();
      System.out.println(end - start);

      start = System.currentTimeMillis();
      for (int i = 0; i < 100; i++) {
         numbers.get(i);
      }
      end = System.currentTimeMillis();
      System.out.println(end - start);

      LocalDate date = LocalDate.of(2026, 12, 7);
      LocalTime time = LocalTime.of(9, 48, 20);
      LocalDateTime dateTime = LocalDateTime.now();

      System.out.println(date.getDayOfWeek());
      System.out.println(time.plusMinutes(66));
      System.out.println(dateTime.getDayOfYear());

      System.out.println(date);
      System.out.println(time);
      System.out.println(dateTime);

      /* Systemeigenschaften */
      System.out.println(System.getProperty("file.separator"));
      System.out.println(System.getProperty("user.dir"));
      System.out.println(System.getProperties());

      /* Lesen von Dateien */
      String path = "src/main/resources/persons.txt";
      File file = new File(path);
      Scanner scanner = new Scanner(file);
      ArrayList<PersonRecord> persons = new ArrayList<>();

      while (scanner.hasNextLine()) {
         String line = scanner.nextLine();
         String[] tokens = line.split(";");

         String name = tokens[0];
         int age = Integer.parseInt(tokens[1]);
         char dna = tokens[2].charAt(0);
         PersonRecord person = new PersonRecord(name, age, dna);
         persons.add(person);
      }
      scanner.close();

      System.out.println(persons);

      /* Aufzählungen (Enumerations) */
      ArrayList<TableLight> lights = new ArrayList<>();

      lights.add(new TableLight());
      lights.add(new TableLight(new LightBulb("red")));
      lights.add(new TableLight("green", PlugType.TYPE_F));

      int total = 0;
      for (TableLight light : lights) {
         if (light.getPlugType().equals(PlugType.TYPE_F)) {
            total++;
         }
      }
      System.out.println(total);
   }

}
