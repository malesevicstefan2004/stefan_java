package main;

import java.util.Optional;
import java.util.Random;

/**
 * Demo 7: Optionals
 *
 * <p>Contrasts the null-return approach (prone to {@link NullPointerException}) with the
 * {@link java.util.Optional} approach for methods that may not return a value.
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public class D07_Optionals {

   /** Shared random-number generator used to simulate non-deterministic results. */
   private final static Random RANDOM = new Random();

   /**
    * Entry point — runs the without-Optional and with-Optional demos sequentially.
    *
    * @param args command-line arguments (not used)
    */
   public static void main(String[] args) {
      /* Ansatz ohne Optionals */
      String text = getText();
      System.out.println(text.toUpperCase());

      /* Ansatz mit Optionals */
      Optional<String> optionalText = getOptionalText();
      if (optionalText.isPresent()) {
         System.out.println(optionalText.get().toUpperCase());
      }
   }

   /**
    * Returns a text string or {@code null} with equal probability.
    *
    * @return {@code "Hello World"} or {@code null}
    */
   private static String getText() {
      String text = null;
      if (RANDOM.nextBoolean()) {
         text = "Hello World";
      }

      return text;
   }

   /**
    * Returns an {@link java.util.Optional} containing a text string, or an empty Optional, with
    * equal probability.
    *
    * @return an Optional wrapping {@code "Hello World"}, or {@link java.util.Optional#empty()}
    */
   private static Optional<String> getOptionalText() {
      Optional<String> text = Optional.empty();
      if (RANDOM.nextBoolean()) {
         text = Optional.of("Hello World");
      }

      return text;
   }

}
