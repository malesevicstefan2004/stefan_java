package model;

/**
 * String Array
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public class StringArray {

   /* Attribute */
   /** The underlying array storing the string elements. */
   public String[] array;

   /* Methoden */
   /**
    * Adds a value to the first empty slot. If the array is full, it is doubled in size first.
    *
    * @param value the string to add
    */
   public void add(String value) {
      for (int i = 0; i < array.length; i++) {
         String element = array[i];
         if (element == null) {
            array[i] = value;
            return;
         }
      }

      String[] tmp = new String[array.length * 2];
      for (int i = 0; i < array.length; i++) {
         tmp[i] = array[i];
      }
      tmp[array.length] = value;
      array = tmp;
   }

   /** Prints all elements of the array to standard output, one per line. */
   public void print() {
      for (int i = 0; i < array.length; i++) {
         System.out.println(array[i]);
      }
   }

}
