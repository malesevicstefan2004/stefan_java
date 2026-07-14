package memory.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Core model of the Memory game – manages the board, players, and game rules.
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
@Getter
public class GameModel {

   /** Two-dimensional card array representing the game board. */
   private Card[][] board;

   /** List of players (1 or 2). */
   private List<Player> players;

   /** Index of the player whose turn it currently is. */
   private int currentPlayerIndex;

   /** Total number of moves played so far. */
   private int totalMoves;

   /** Number of pairs found so far. */
   private int matchedPairs;

   /** Whether the active player is allowed to flip a card. */
   private boolean canFlip;

   /** Configuration for this game session. */
   private final GameConfig config;

   /** Registered observers for game events. */
   private final List<GameModelListener> listeners = new ArrayList<>();

   /** Number of cards already flipped in the current turn (0 or 1). */
   private int flippedCount;

   /** Row of the first card flipped in the current turn. */
   private int firstRow;

   /** Column of the first card flipped in the current turn. */
   private int firstCol;

   /** Row of the second card flipped in the current turn. */
   private int secondRow;

   /** Column of the second card flipped in the current turn. */
   private int secondCol;

   /**
    * Creates and initialises a new game from the given configuration.
    *
    * @param config the configuration chosen in the start menu
    */
   public GameModel(GameConfig config) {
      this.config = config;
      initGame();
   }

   /** Initialises all game-state variables and randomly distributes the cards. */
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
    * Registers a listener to be notified of game events.
    *
    * @param listener the observer to add
    */
   public void addListener(GameModelListener listener) {
      listeners.add(listener);
   }

   /**
    * Flips the card at the given position.
    * Ignored if input is locked or the card is not face-down.
    *
    * @param row row index of the card to flip (0-based)
    * @param col column index of the card to flip (0-based)
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

   /** Checks whether the two flipped cards form a pair and notifies listeners. */
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

   /** Hides the two last mismatched cards and switches the active player. */
   public void hideCards() {
      board[firstRow][firstCol].setState(CardState.FACE_DOWN);
      board[secondRow][secondCol].setState(CardState.FACE_DOWN);
      currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
      canFlip = true;
      listeners.forEach(GameModelListener::onCardsHidden);
   }

   /** Returns the total number of pairs on the board. */
   public int getTotalPairs() {
      return (config.getRows() * config.getCols()) / 2;
   }
}
