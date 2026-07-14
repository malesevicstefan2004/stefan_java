package memory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX application class for the Memory game.
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
public class MemoryApp extends Application {

   /** Width of the start-menu window in pixels. */
   private static final int MENU_WIDTH = 520;

   /** Height of the start-menu window in pixels. */
   private static final int MENU_HEIGHT = 420;

   /**
    * Starts the application and loads the start menu.
    *
    * @param primaryStage the primary stage provided by the JavaFX runtime
    * @throws Exception if the FXML file cannot be loaded
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
