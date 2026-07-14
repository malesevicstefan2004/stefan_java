package memory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import memory.model.GameConfig;
import memory.model.Theme;

import java.io.IOException;

/**
 * Controller des Startmenüs ({@code MenuView.fxml}).
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
public class MenuController {

   @FXML private ComboBox<String> playerCountCombo;
   @FXML private ComboBox<String> boardSizeCombo;
   @FXML private ComboBox<Theme>  themeCombo;
   @FXML private TextField        player1NameField;
   @FXML private TextField        player2NameField;
   @FXML private Label            player2NameLabel;

   /** Befüllt alle Auswahlfelder und richtet den Spielerzahl-Listener ein. */
   @FXML
   public void initialize() {
      playerCountCombo.getItems().addAll("1 Spieler", "2 Spieler");
      playerCountCombo.getSelectionModel().selectFirst();

      boardSizeCombo.getItems().addAll("4 × 4", "4 × 6", "6 × 6");
      boardSizeCombo.getSelectionModel().selectFirst();

      themeCombo.getItems().addAll(Theme.values());
      themeCombo.getSelectionModel().selectFirst();

      playerCountCombo.setOnAction(e -> updatePlayer2Visibility());
      updatePlayer2Visibility();
   }

   /** Zeigt oder versteckt die Eingabezeile für Spieler 2 je nach Spieleranzahl. */
   private void updatePlayer2Visibility() {
      boolean twoPlayers = playerCountCombo.getSelectionModel().getSelectedIndex() == 1;
      player2NameLabel.setVisible(twoPlayers);
      player2NameLabel.setManaged(twoPlayers);
      player2NameField.setVisible(twoPlayers);
      player2NameField.setManaged(twoPlayers);
   }

   /**
    * Liest die Einstellungen aus, erstellt eine {@link GameConfig} und öffnet die Spielansicht.
    *
    * @param event das ActionEvent des Buttons „Spiel starten"
    * @throws IOException falls {@code GameView.fxml} nicht geladen werden kann
    */
   @FXML
   public void startGame(ActionEvent event) throws IOException {
      GameConfig config = buildConfig();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/memory/GameView.fxml"));
      Parent root = loader.load();

      GameController gameController = loader.getController();
      gameController.initGame(config);

      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/memory/style.css").toExternalForm());
      stage.setScene(scene);
      stage.setResizable(true);
      stage.sizeToScene();
      stage.centerOnScreen();
   }

   /** Erstellt eine {@link GameConfig} anhand der aktuellen UI-Auswahl. */
   private GameConfig buildConfig() {
      GameConfig config = new GameConfig();

      config.setPlayerCount(playerCountCombo.getSelectionModel().getSelectedIndex() + 1);

      int[][] sizes = {{4, 4}, {4, 6}, {6, 6}};
      int sizeIndex = boardSizeCombo.getSelectionModel().getSelectedIndex();
      config.setRows(sizes[sizeIndex][0]);
      config.setCols(sizes[sizeIndex][1]);

      config.setTheme(themeCombo.getSelectionModel().getSelectedItem());

      String p1 = player1NameField.getText().trim();
      config.setPlayer1Name(p1.isEmpty() ? "Spieler 1" : p1);

      String p2 = player2NameField.getText().trim();
      config.setPlayer2Name(p2.isEmpty() ? "Spieler 2" : p2);

      return config;
   }
}
