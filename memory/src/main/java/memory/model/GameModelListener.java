package memory.model;

/**
 * Observer-Schnittstelle für Spielzustandsänderungen des {@link GameModel}.
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
public interface GameModelListener {

   /** Wird aufgerufen, nachdem eine Karte aufgedeckt wurde. */
   void onCardFlipped();

   /**
    * Wird aufgerufen, wenn zwei aufgedeckte Karten ein Paar bilden.
    *
    * @param row1 Zeile der ersten Karte
    * @param col1 Spalte der ersten Karte
    * @param row2 Zeile der zweiten Karte
    * @param col2 Spalte der zweiten Karte
    */
   void onPairMatched(int row1, int col1, int row2, int col2);

   /**
    * Wird aufgerufen, wenn zwei aufgedeckte Karten kein Paar bilden.
    * Der Listener muss danach {@link GameModel#hideCards()} aufrufen.
    *
    * @param row1 Zeile der ersten Karte
    * @param col1 Spalte der ersten Karte
    * @param row2 Zeile der zweiten Karte
    * @param col2 Spalte der zweiten Karte
    */
   void onPairMismatched(int row1, int col1, int row2, int col2);

   /** Wird aufgerufen, nachdem nicht passende Karten wieder verdeckt wurden. */
   void onCardsHidden();

   /** Wird aufgerufen, sobald alle Paare gefunden wurden. */
   void onGameOver();
}
