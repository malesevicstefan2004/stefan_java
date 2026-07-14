package memory.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kernmodell des Memory-Spiels – verwaltet Spielfeld, Spieler und Spielregeln.
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
@Getter
public class GameModel {

   /** Zweidimensionales Kartenfeld des Spielbretts. */
   private Card[][] board;

   /** Liste der Spieler (1 oder 2). */
   private List<Player> players;

   /** Index des Spielers, der gerade am Zug ist. */
   private int currentPlayerIndex;

   /** Gesamtanzahl der bisher gespielten Züge. */
   private int totalMoves;

   /** Anzahl der bisher gefundenen Paare. */
   private int matchedPairs;

   /** Gibt an, ob der Spieler eine Karte aufdecken darf. */
   private boolean canFlip;

   /** Die Konfiguration dieser Partie. */
   private final GameConfig config;

   /** Registrierte Observer für Spielereignisse. */
   private final List<GameModelListener> listeners = new ArrayList<>();

   /** Anzahl der im aktuellen Zug bereits aufgedeckten Karten (0 oder 1). */
   private int flippedCount;

   /** Zeile der zuerst aufgedeckten Karte im aktuellen Zug. */
   private int firstRow;

   /** Spalte der zuerst aufgedeckten Karte im aktuellen Zug. */
   private int firstCol;

   /** Zeile der zweiten aufgedeckten Karte im aktuellen Zug. */
   private int secondRow;

   /** Spalte der zweiten aufgedeckten Karte im aktuellen Zug. */
   private int secondCol;

   /**
    * Erzeugt und initialisiert ein neues Spiel anhand der gegebenen Konfiguration.
    *
    * @param config die im Startmenü gewählte Konfiguration
    */
   public GameModel(GameConfig config) {
      this.config = config;
      initGame();
   }

   /** Initialisiert alle Spielzustandsvariablen und verteilt die Karten zufällig. */
   private void initGame() {
      players = new ArrayList<>();
      players.add(new Player(config.getPlayer1Name()));
      if (config.getPlayerCount() == 2) {
         players.add(new Player(config.getPlayer2Name()));
      }

      int rows = config.getRows();
      int cols = config.getCols();
      int totalPairs = (rows * cols) / 2;
      String[] symbols = config.getTheme().getSymbols();

      List<Card> cards = new ArrayList<>();
      for (int i = 0; i < totalPairs; i++) {
         String symbol = symbols[i % symbols.length];
         cards.add(new Card(i * 2, i, symbol));
         cards.add(new Card(i * 2 + 1, i, symbol));
      }
      Collections.shuffle(cards);

      board = new Card[rows][cols];
      int idx = 0;
      for (int r = 0; r < rows; r++) {
         for (int c = 0; c < cols; c++) {
            board[r][c] = cards.get(idx++);
         }
      }

      currentPlayerIndex = 0;
      totalMoves = 0;
      matchedPairs = 0;
      flippedCount = 0;
      canFlip = true;
   }

   /**
    * Registriert einen Listener, der über Spielereignisse informiert wird.
    *
    * @param listener der hinzuzufügende Observer
    */
   public void addListener(GameModelListener listener) {
      listeners.add(listener);
   }

   /**
    * Deckt die Karte an der angegebenen Position auf.
    * Wird ignoriert, wenn die Eingabe gesperrt ist oder die Karte nicht verdeckt ist.
    *
    * @param row Zeile der aufzudeckenden Karte (0-basiert)
    * @param col Spalte der aufzudeckenden Karte (0-basiert)
    */
   public void flipCard(int row, int col) {
      if (!canFlip) return;
      Card card = board[row][col];
      if (card.getState() != CardState.FACE_DOWN) return;

      card.setState(CardState.FACE_UP);

      if (flippedCount == 0) {
         firstRow = row;
         firstCol = col;
         flippedCount = 1;
         listeners.forEach(GameModelListener::onCardFlipped);
      } else {
         secondRow = row;
         secondCol = col;
         flippedCount = 0;
         totalMoves++;
         canFlip = false;
         listeners.forEach(GameModelListener::onCardFlipped);
         evaluatePair();
      }
   }

   /** Prüft, ob die beiden aufgedeckten Karten ein Paar bilden, und benachrichtigt die Listener. */
   private void evaluatePair() {
      Card first = board[firstRow][firstCol];
      Card second = board[secondRow][secondCol];
      final int r1 = firstRow, c1 = firstCol;
      final int r2 = secondRow, c2 = secondCol;

      if (first.getPairId() == second.getPairId()) {
         first.setState(CardState.MATCHED);
         second.setState(CardState.MATCHED);
         matchedPairs++;
         players.get(currentPlayerIndex).addScore();
         canFlip = true;
         listeners.forEach(l -> l.onPairMatched(r1, c1, r2, c2));
         if (matchedPairs == (config.getRows() * config.getCols()) / 2) {
            listeners.forEach(GameModelListener::onGameOver);
         }
      } else {
         listeners.forEach(l -> l.onPairMismatched(r1, c1, r2, c2));
      }
   }

   /** Verdeckt die beiden zuletzt nicht passenden Karten und wechselt den aktiven Spieler. */
   public void hideCards() {
      board[firstRow][firstCol].setState(CardState.FACE_DOWN);
      board[secondRow][secondCol].setState(CardState.FACE_DOWN);
      currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
      canFlip = true;
      listeners.forEach(GameModelListener::onCardsHidden);
   }

   /** Gibt die Gesamtanzahl der Paare auf dem Spielfeld zurück. */
   public int getTotalPairs() {
      return (config.getRows() * config.getCols()) / 2;
   }
}
