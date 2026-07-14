package main;

import java.util.Scanner;

/**
 * Demo 1: Java Basics
 *
 * <p>Demonstrates primitive data types, {@link java.util.Scanner} input, conditional statements,
 * switch expressions, loops, string comparison, and arrays.
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public class D01_JavaBasics {

   /**
    * Entry point — runs all Java-basics demos sequentially.
    *
    * @param args command-line arguments (not used)
    */
   @SuppressWarnings("resource")
   public static void main(String[] args) {
      /* Datentypen und Datenobjekte */
      int length = "Hello World".length();
      System.out.println(length);

      Scanner scanner = new Scanner(System.in);

      System.out.print("Please enter your name: ");
      String name = scanner.next();

      int age = 0;
      boolean loop;
      do {
         System.out.print("Please enter your age: ");
         age = scanner.nextInt();
         if (age > 0 && age < 150) {
            loop = false;
         } else {
            System.out.println("invalid value range");
            loop = true;
         }
      } while (loop);

      System.out.print("Please enter your height in meters: ");
      double sizeInM = scanner.nextDouble();

      System.out.print("Please enter your gender (m, f, d): ");
      char gender = scanner.next().charAt(0);

      System.out.print("Please enter whether you are happy (true, false): ");
      boolean isHappy = scanner.nextBoolean();

      /* Verzweigungen */
      // Vergleichsoperatoren: >, >=, <, <=, ==, !=
      // Logische Operatoren: &&, ||, !
      if ((gender == 'm' || gender == 'M') && age >= 18) {
         System.out.println("Hello Mr. " + name);
      } else if ((gender == 'f' || gender == 'F') && age >= 18) {
         System.out.println("Hello Ms. " + name);
      } else {
         System.out.println("Hello " + name);
      }

      if (age > 65) {
         System.out.println("Hello, you old-timer");
      } else {
         System.out.println("Hey there, youngster!");
      }

      System.out.println((age > 65) ? "Hello, you old-timer" : "Hey there, youngster!");

      System.out.println(name);
      System.out.println(age);
      System.out.println(sizeInM);
      System.out.println(gender);
      System.out.println(isHappy);

      /* Fallunterscheidungen */
      String genderText;
      genderText = switch (gender) {
         case 'm', 'M' -> "male";
         case 'f', 'F' -> "female";
         case 'd', 'D' -> "diverse";
         default       -> gender + "";
      };
      System.out.println(genderText);

      /* Schleifen */
      int x = 1;
      while (x <= 10) {
         System.out.println(x);
         x++;
      }
      System.out.println();

      int z = 1;
      do {
         System.out.println(z);
         z++;
      } while (z <= 10);
      System.out.println();

      for (int i = 1; i <= 10; i++) {
         System.out.println(i);
      }

      /* Vergleich von Zeichenketten */
      String text = "Hello";
      System.out.print("Please enter the string \"Hello\": ");
      String input = scanner.next();

      if (text.equals(input)) {
         System.out.println("Well done!");
      } else {
         System.out.println("Nope, that was not it");
         System.out.println(text);
         System.out.println(input);
      }

      /* Arrays */
      String[] names = new String[3];
      names[0] = "Hans";
      names[1] = "Peter";
      names[2] = "Lisa";
      System.out.println(names[0]);
      System.out.println(names[1]);
      System.out.println(names[2]);
      System.out.println(names.length);

      int[] numbers = {5, 1, 7, 2, 5, -6, 0};
      numbers[4] = 8;

      for (int i = 0; i < numbers.length; i++) {
         int number = numbers[i];
         System.out.println(number);
      }

      for (int number : numbers) {
         System.out.println(number);
      }
   }

}
