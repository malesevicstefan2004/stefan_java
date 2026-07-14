package model;

/**
 * Movie
 *
 * <p>Immutable data class representing a movie. Natural ordering is by title (ascending).
 *
 * @param title  the movie title
 * @param year   the release year
 * @param rating the IMDb-style rating in the range {@code [0.0, 10.0]}
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public record Movie(String title, String year, double rating) implements Comparable<Movie> {

   /**
    * Compares this movie to another by title in ascending alphabetical order.
    *
    * @param otherMovie the movie to compare with
    * @return a negative integer, zero, or a positive integer as this movie's title is less than,
    *         equal to, or greater than the other movie's title
    */
   @Override
   public int compareTo(Movie otherMovie) {
      return title.compareTo(otherMovie.title);
   }

}
