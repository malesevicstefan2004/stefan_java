package apps.goal;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/** JavaFX controller for the input view — handles goal tracking and scene transition. */
public class InputController {

   @FXML
   private Label goalsOfTeamA;

   @FXML
   private Label goalsOfTeamB;

   @FXML
   private TextField nameOfTeamA;

   @FXML
   private TextField nameOfTeamB;

   private Model model;

   /** Retrieves the shared model instance. */
   @FXML
   public void initialize() {
      model = Model.getInstance();
   }

   /** Increments team A's goal counter. */
   @FXML
   public void increaseGoalsOfTeamA() {
      model.increaseGoals(model.getTeamA());
      goalsOfTeamA.setText(String.valueOf(model.getTeamA().getGoals()));
   }

   /** Increments team B's goal counter. */
   @FXML
   public void increaseGoalsOfTeamB() {
      model.increaseGoals(model.getTeamB());
      goalsOfTeamB.setText(String.valueOf(model.getTeamB().getGoals()));
   }

   /**
    * Reads both team names, then switches to the output view.
    *
    * @param event the action event fired by the Finish button
    * @throws IOException if {@code OutputView.fxml} cannot be loaded
    */
   @FXML
   public void goToOutputView(ActionEvent event) throws IOException {
      model.getTeamA().setName(nameOfTeamA.getText());
      model.getTeamB().setName(nameOfTeamB.getText());

      Node node = (Node) event.getSource();
      Stage primaryStage = (Stage) node.getScene().getWindow();

      Parent root = new FXMLLoader(getClass().getResource("OutputView.fxml")).load();
      Scene outputScene = new Scene(root);
      primaryStage.setScene(outputScene);
      primaryStage.show();
   }

}
