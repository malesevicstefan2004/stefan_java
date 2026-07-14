package utility;

/**
 * String Array Helper
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public class StringArrayHelper {

   /**
    * Adds a value to the first empty slot of the given array. If the array is full, a new array
    * twice the size is created, the existing elements are copied, and the value is appended.
    *
    * @param array the current string array
    * @param value the string to add
    * @return the (possibly new, larger) array containing the added value
    */
   public static String[] add(String[] array, String value) {
      for (int i = 0; i < array.length; i++) {
         String element = array[i];
         if (element == null) {
            array[i] = value;
            return array;
         }
      }

      String[] tmp = new String[array.length * 2];
      for (int i = 0; i < array.length; i++) {
         tmp[i] = array[i];
      }
      tmp[array.length] = value;
      return tmp;
   }

   /**
    * Prints all elements of the given array to standard output, one per line.
    *
    * @param array the array to print
    */
   public static void print(String[] array) {
      for (int i = 0; i < array.length; i++) {
         System.out.println(array[i]);
      }
   }

}
