package model;

/**
 * Person Record
 *
 * <p>Immutable data class representing a person. Accessor methods, {@code equals},
 * {@code hashCode}, and {@code toString} are generated automatically by the record declaration.
 *
 * @param name the person's name
 * @param age  the person's age
 * @param dna  a single-character DNA marker
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public record PersonRecord(String name, int age, char dna) {

}
