package model;

import java.util.Comparator;

/**
 * Movies By Rating Descending Comparator
 *
 * <p>Orders {@link Movie} instances by rating in descending order (highest rating first).
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public class MoviesByRatingDescendingComparator implements Comparator<Movie> {

   /**
    * Compares two movies by their rating in descending order.
    *
    * @param movie1 the first movie
    * @param movie2 the second movie
    * @return a negative integer if {@code movie1} has a higher rating, zero if equal, or a positive
    *         integer if {@code movie2} has a higher rating
    */
   @Override
   public int compare(Movie movie1, Movie movie2) {
      // Double rating1 = movie1.rating();
      // Double rating2 = movie2.rating();
      //
      // return rating2.compareTo(rating1);
      return Double.compare(movie2.rating(), movie1.rating());
   }

}
