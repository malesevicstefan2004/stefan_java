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
 * Controller der Spielansicht ({@code GameView.fxml}), implementiert {@link GameModelListener}.
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

   /** Das beobachtete Spielmodell. */
   private GameModel model;

   /** Schaltflächen-Array parallel zum {@code model.getBoard()}. */
   private Button[][] cardButtons;

   /** Wartezeit in Millisekunden, bevor nicht passende Karten zurückgekehrt werden. */
   private static final int FLIP_BACK_DELAY_MS = 1500;

   /**
    * Initialisiert die Spielansicht mit der gegebenen Konfiguration.
    *
    * @param config die im Startmenü gewählte Konfiguration
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

   /** Erstellt für jede Kartenpositon eine Schaltfläche und fügt sie dem GridPane hinzu. */
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
    * Berechnet eine passende Kartengröße je nach Spielfeldgröße.
    *
    * @param rows Anzahl der Zeilen
    * @param cols Anzahl der Spalten
    * @return Kartengröße in Pixel
    */
   private int computeCardSize(int rows, int cols) {
      if (rows == 6 || cols == 6) return 75;
      if (cols == 6) return 80;
      return 90;
   }

   /**
    * Erstellt eine Kartenschaltfläche und verknüpft sie mit {@link GameModel#flipCard}.
    *
    * @param row      Zeile der Karte
    * @param col      Spalte der Karte
    * @param cardSize Größe der Schaltfläche in Pixel
    * @return die konfigurierte Schaltfläche
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

   /** Aktualisiert alle Kartenschaltflächen entsprechend dem aktuellen Modellzustand. */
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
    * Aktualisiert Stil und Text der Kartenschaltfläche an der gegebenen Position.
    *
    * @param row Zeile der Karte
    * @param col Spalte der Karte
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

   /** Aktualisiert die Statusleiste mit aktivem Spieler, Punkteständen und Zugzahl. */
   private void updateStatus() {
      List<Player> players = model.getPlayers();
      Player active = players.get(model.getCurrentPlayerIndex());
      activePlayerLabel.setText("Am Zug: " + active.getName());

      Player p1 = players.get(0);
      player1ScoreLabel.setText(p1.getName() + ":  " + p1.getScore() + " Pkt.");

      if (players.size() > 1) {
         Player p2 = players.get(1);
         player2ScoreLabel.setText(p2.getName() + ":  " + p2.getScore() + " Pkt.");
      }

      movesLabel.setText("Züge: " + model.getTotalMoves());
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

   /** Zeigt einen Informationsdialog mit dem Spielergebnis. */
   private void showGameOverDialog() {
      String message = buildResultMessage();
      Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
      alert.setTitle("Spiel beendet");
      alert.setHeaderText("Alle Paare gefunden!");
      alert.getDialogPane().getStylesheets()
           .add(getClass().getResource("/memory/style.css").toExternalForm());
      alert.showAndWait();
   }

   /** Erstellt die Ergebnismeldung für den Abschlussdialog. */
   private String buildResultMessage() {
      List<Player> players = model.getPlayers();
      int moves = model.getTotalMoves();

      if (players.size() == 1) {
         Player p = players.get(0);
         return "Glückwunsch, " + p.getName() + "!\n"
               + "Du hast alle " + model.getTotalPairs()
               + " Paare in " + moves + " Zügen gefunden!";
      }

      Player p1 = players.get(0);
      Player p2 = players.get(1);
      String score = p1.getScore() + " : " + p2.getScore();

      if (p1.getScore() > p2.getScore()) {
         return "Gewinner: " + p1.getName() + "\n" + score + "\nZüge: " + moves;
      } else if (p2.getScore() > p1.getScore()) {
         return "Gewinner: " + p2.getName() + "\n" + score + "\nZüge: " + moves;
      } else {
         return "Unentschieden!\n" + score + "\nZüge: " + moves;
      }
   }

   /**
    * Kehrt ins Startmenü zurück.
    *
    * @param event das ActionEvent des Buttons „Neues Spiel"
    * @throws IOException falls {@code MenuView.fxml} nicht geladen werden kann
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
