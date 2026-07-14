package model;

import java.util.Objects;

/**
 * Person
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public class PersonClass {

   /** The person's name. */
   private String name;
   /** The person's age. */
   private int age;
   /** A single-character DNA marker. */
   private final char dna;

   /**
    * Creates a new person with the given name, age, and DNA marker.
    *
    * @param name the person's name
    * @param age  the person's age
    * @param dna  a single-character DNA marker
    */
   public PersonClass(String name, int age, char dna) {
      this.name = name;
      this.age = age;
      this.dna = dna;
   }

   /**
    * Returns the person's name.
    *
    * @return the name
    */
   public String getName() {
      return name;
   }

   /**
    * Sets the person's name.
    *
    * @param name the new name
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * Returns the person's age.
    *
    * @return the age
    */
   public int getAge() {
      return age;
   }

   /**
    * Sets the person's age.
    *
    * @param age the new age
    */
   public void setAge(int age) {
      this.age = age;
   }

   /**
    * Returns the person's DNA marker.
    *
    * @return the DNA character
    */
   public char dna() {
      return dna;
   }

   @Override
   public int hashCode() {
      return Objects.hash(age, dna, name);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      PersonClass other = (PersonClass) obj;
      return age == other.age && dna == other.dna && Objects.equals(name, other.name);
   }

   @Override
   public String toString() {
      return "PersonClass [name=" + name + ", age=" + age + ", dna=" + dna + "]";
   }

}
