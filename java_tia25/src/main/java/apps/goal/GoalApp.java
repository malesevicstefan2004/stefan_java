package apps.goal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** JavaFX application that loads the initial input view. */
public class GoalApp extends Application {

   /** Loads {@code InputView.fxml} and shows the primary stage. */
   @Override
   public void start(Stage primaryStage) throws Exception {
      Parent root = new FXMLLoader(getClass().getResource("InputView.fxml")).load();
      Scene inputScene = new Scene(root);
      primaryStage.setScene(inputScene);
      primaryStage.setTitle("Gooooaaaaal");
      primaryStage.show();
   }

}
