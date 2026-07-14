package memory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX-Applikation für das Memory-Spiel.
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
public class MemoryApp extends Application {

   /** Breite des Startmenü-Fensters in Pixel. */
   private static final int MENU_WIDTH = 520;

   /** Höhe des Startmenü-Fensters in Pixel. */
   private static final int MENU_HEIGHT = 420;

   /**
    * Startet die Applikation und lädt das Startmenü.
    *
    * @param primaryStage die primäre Stage der JavaFX-Laufzeit
    * @throws Exception falls die FXML-Datei nicht geladen werden kann
    */
   @Override
   public void start(Stage primaryStage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("/memory/MenuView.fxml"));
      Scene scene = new Scene(root, MENU_WIDTH, MENU_HEIGHT);
      scene.getStylesheets().add(getClass().getResource("/memory/style.css").toExternalForm());
      primaryStage.setTitle("Memory");
      primaryStage.setResizable(false);
      primaryStage.setScene(scene);
      primaryStage.show();
   }
}
