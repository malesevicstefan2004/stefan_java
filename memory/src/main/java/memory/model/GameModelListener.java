package memory.model;

/**
 * Observer interface for game-state changes in {@link GameModel}.
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
public interface GameModelListener {

   /** Called after a card has been flipped. */
   void onCardFlipped();

   /**
    * Called when two revealed cards form a matching pair.
    *
    * @param row1 row of the first card
    * @param col1 column of the first card
    * @param row2 row of the second card
    * @param col2 column of the second card
    */
   void onPairMatched(int row1, int col1, int row2, int col2);

   /**
    * Called when two revealed cards do not match.
    * The listener must subsequently call {@link GameModel#hideCards()}.
    *
    * @param row1 row of the first card
    * @param col1 column of the first card
    * @param row2 row of the second card
    * @param col2 column of the second card
    */
   void onPairMismatched(int row1, int col1, int row2, int col2);

   /** Called after mismatched cards have been flipped back. */
   void onCardsHidden();

   /** Called when all pairs have been found. */
   void onGameOver();
}
