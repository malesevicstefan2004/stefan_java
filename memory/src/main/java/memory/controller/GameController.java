package memory.controller;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import memory.model.Card;
import memory.model.CardState;
import memory.model.GameConfig;
import memory.model.GameModel;
import memory.model.GameModelListener;
import memory.model.Player;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the game view ({@code GameView.fxml}), implements {@link GameModelListener}.
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
public class GameController implements GameModelListener {

   @FXML private GridPane boardGrid;
   @FXML private Label    activePlayerLabel;
   @FXML private Label    player1ScoreLabel;
   @FXML private Label    player2ScoreLabel;
   @FXML private Label    movesLabel;
   @FXML private HBox     statusBar;

   /** The observed game model. */
   private GameModel model;

   /** Button array mirroring the positions in {@code model.getBoard()}. */
   private Button[][] cardButtons;

   /** Delay in milliseconds before mismatched cards are flipped back. */
   private static final int FLIP_BACK_DELAY_MS = 1500;

   /**
    * Initialises the game view with the given configuration.
    *
    * @param config the configuration chosen in the start menu
    */
   public void initGame(GameConfig config) {
      model = new GameModel(config);
      model.addListener(this);
      buildBoard();
      updateStatus();
      if (config.getPlayerCount() == 1) {
         player2ScoreLabel.setVisible(false);
         player2ScoreLabel.setManaged(false);
      }
   }

   /** Creates a button for every card position and adds it to the GridPane. */
   private void buildBoard() {
      boardGrid.getChildren().clear();
      int rows = model.getConfig().getRows();
      int cols = model.getConfig().getCols();
      cardButtons = new Button[rows][cols];

      int cardSize = computeCardSize(rows, cols);

      for (int r = 0; r < rows; r++) {
         for (int c = 0; c < cols; c++) {
            Button btn = createCardButton(r, c, cardSize);
            cardButtons[r][c] = btn;
            boardGrid.add(btn, c, r);
         }
      }
      Platform.runLater(() -> {
         Stage stage = (Stage) boardGrid.getScene().getWindow();
         stage.sizeToScene();
         stage.centerOnScreen();
      });
   }

   /**
    * Returns an appropriate card size in pixels based on the board dimensions.
    *
    * @param rows number of rows
    * @param cols number of columns
    * @return card size in pixels
    */
   private int computeCardSize(int rows, int cols) {
      if (rows == 6 && cols == 6) return 75;
      if (rows == 6 || cols == 6) return 80;
      return 90;
   }

   /**
    * Creates a card button and wires it to {@link GameModel#flipCard}.
    *
    * @param row      row index of the card
    * @param col      column index of the card
    * @param cardSize button size in pixels
    * @return the configured button
    */
   private Button createCardButton(int row, int col, int cardSize) {
      Button btn = new Button("?");
      btn.getStyleClass().add("card-back");
      btn.setMinSize(cardSize, cardSize);
      btn.setMaxSize(cardSize, cardSize);
      btn.setAlignment(Pos.CENTER);
      btn.setOnAction(e -> model.flipCard(row, col));
      return btn;
   }

   /** Refreshes all card buttons to reflect the current model state. */
   private void refreshBoard() {
      int rows = model.getConfig().getRows();
      int cols = model.getConfig().getCols();
      for (int r = 0; r < rows; r++) {
         for (int c = 0; c < cols; c++) {
            updateCardButton(r, c);
         }
      }
   }

   /**
    * Updates the style and text of the card button at the given position.
    *
    * @param row row index of the card
    * @param col column index of the card
    */
   private void updateCardButton(int row, int col) {
      Card card = model.getBoard()[row][col];
      Button btn = cardButtons[row][col];
      btn.getStyleClass().removeAll("card-back", "card-front", "card-matched");

      switch (card.getState()) {
         case FACE_DOWN -> {
            btn.getStyleClass().add("card-back");
            btn.setText("?");
            btn.setDisable(false);
         }
         case FACE_UP -> {
            btn.getStyleClass().add("card-front");
            btn.setText(card.getSymbol());
            btn.setDisable(true);
         }
         case MATCHED -> {
            btn.getStyleClass().add("card-matched");
            btn.setText(card.getSymbol());
            btn.setDisable(true);
         }
      }
   }

   /** Updates the status bar with the active player, scores, and move count. */
   private void updateStatus() {
      List<Player> players = model.getPlayers();
      Player active = players.get(model.getCurrentPlayerIndex());
      activePlayerLabel.setText("Turn: " + active.getName());

      Player p1 = players.get(0);
      player1ScoreLabel.setText(p1.getName() + ":  " + p1.getScore() + " pts.");

      if (players.size() > 1) {
         Player p2 = players.get(1);
         player2ScoreLabel.setText(p2.getName() + ":  " + p2.getScore() + " pts.");
      }

      movesLabel.setText("Moves: " + model.getTotalMoves());
   }

   @Override
   public void onCardFlipped() {
      refreshBoard();
      updateStatus();
   }

   @Override
   public void onPairMatched(int row1, int col1, int row2, int col2) {
      refreshBoard();
      updateStatus();
   }

   @Override
   public void onPairMismatched(int row1, int col1, int row2, int col2) {
      refreshBoard();
      updateStatus();
      PauseTransition pause = new PauseTransition(Duration.millis(FLIP_BACK_DELAY_MS));
      pause.setOnFinished(e -> model.hideCards());
      pause.play();
   }

   @Override
   public void onCardsHidden() {
      refreshBoard();
      updateStatus();
   }

   @Override
   public void onGameOver() {
      refreshBoard();
      updateStatus();
      showGameOverDialog();
   }

   /** Shows an information dialog with the final game result. */
   private void showGameOverDialog() {
      String message = buildResultMessage();
      Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
      alert.setTitle("Game Over");
      alert.setHeaderText("All pairs found!");
      alert.getDialogPane().getStylesheets()
           .add(getClass().getResource("/memory/style.css").toExternalForm());
      alert.showAndWait();
   }

   /** Builds the result message shown in the game-over dialog. */
   private String buildResultMessage() {
      List<Player> players = model.getPlayers();
      int moves = model.getTotalMoves();

      if (players.size() == 1) {
         Player p = players.get(0);
         return "Congratulations, " + p.getName() + "!\n"
               + "You found all " + model.getTotalPairs()
               + " pairs in " + moves + " moves!";
      }

      Player p1 = players.get(0);
      Player p2 = players.get(1);
      String score = p1.getScore() + " : " + p2.getScore();

      if (p1.getScore() > p2.getScore()) {
         return "Winner: " + p1.getName() + "\n" + score + "\nMoves: " + moves;
      } else if (p2.getScore() > p1.getScore()) {
         return "Winner: " + p2.getName() + "\n" + score + "\nMoves: " + moves;
      } else {
         return "Draw!\n" + score + "\nMoves: " + moves;
      }
   }

   /**
    * Returns to the start menu.
    *
    * @param event the ActionEvent from the "New Game" button
    * @throws IOException if {@code MenuView.fxml} cannot be loaded
    */
   @FXML
   public void newGame(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("/memory/MenuView.fxml"));
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      Scene scene = new Scene(root, 520, 420);
      scene.getStylesheets().add(getClass().getResource("/memory/style.css").toExternalForm());
      stage.setResizable(false);
      stage.setScene(scene);
      stage.centerOnScreen();
   }
}
