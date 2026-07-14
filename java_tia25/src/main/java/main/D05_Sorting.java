package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Movie;
import model.MoviesByRatingDescendingComparator;

/**
 * Demo 5: Sorting
 *
 * <p>Demonstrates natural ordering via {@link Comparable}, custom ordering via
 * {@link java.util.Comparator}, and {@link java.util.Collections#sort} for lists of integers,
 * strings, and domain objects.
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public class D05_Sorting {

   /**
    * Entry point — runs all sorting demos sequentially.
    *
    * @param args command-line arguments (not used)
    */
   public static void main(String[] args) {
      List<Integer> numbers = new ArrayList<>();
      numbers.add(5);
      numbers.add(2);
      numbers.add(7);
      numbers.add(5);
      numbers.add(9);
      numbers.add(-3);

      System.out.println(numbers);
      Collections.sort(numbers);
      System.out.println(numbers);

      List<String> names = new ArrayList<>();
      names.add("Hans");
      names.add("Anna");
      names.add("anna");
      names.add("Xaver");
      names.add("Lisa");

      System.out.println(names);
      Collections.sort(names);
      System.out.println(names);

      List<Movie> movies = new ArrayList<>();
      movies.add(new Movie("John Wick 4", "2023", 7.6));
      movies.add(new Movie("Disaster Movie", "2008", 1.9));
      movies.add(new Movie("The Godfather", "1972", 9.2));

      System.out.println(movies);
      Collections.sort(movies);
      System.out.println(movies);
      Collections.sort(movies, new MoviesByRatingDescendingComparator());
      System.out.println(movies);
      movies.sort(null);
      System.out.println(movies);
   }

}
