package apps.goal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/** JavaFX controller for the output view — displays the final score and match result. */
public class OutputController {

   @FXML
   private Label teamA;

   @FXML
   private Label teamB;

   @FXML
   private Label victoryMessage;

   private Model model;

   /** Populates the score labels and shows the winner or a draw message. */
   @FXML
   public void initialize() {
      model = Model.getInstance();

      Team a = model.getTeamA();
      Team b = model.getTeamB();

      teamA.setText(a.getName() + ": " + a.getGoals());
      teamB.setText(b.getName() + ": " + b.getGoals());

      if (a.getGoals() > b.getGoals()) {
         victoryMessage.setText(a.getName() + " gewinnt");
      } else if (b.getGoals() > a.getGoals()) {
         victoryMessage.setText(b.getName() + " gewinnt");
      } else {
         victoryMessage.setText("Unentschieden");
      }
   }

}
