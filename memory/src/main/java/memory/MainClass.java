package memory;

import javafx.application.Application;

/**
 * Einstiegspunkt der Memory-Applikation.
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
public class MainClass {

   /**
    * Startet die JavaFX-Applikation.
    *
    * @param args Kommandozeilenargumente (werden nicht verwendet)
    */
   public static void main(String[] args) {
      Application.launch(MemoryApp.class, args);
   }
}
