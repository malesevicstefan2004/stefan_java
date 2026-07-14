package memory.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Eine Spielkarte mit eindeutiger ID, Paar-ID, Symbol und aktuellem Zustand.
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
@Getter
public class Card {

   /** Eindeutige Karten-ID. */
   private final int id;

   /** Paar-ID – zwei Karten mit gleicher Paar-ID bilden ein Paar. */
   private final int pairId;

   /** Das auf der Karte angezeigte Unicode-Symbol. */
   private final String symbol;

   /** Aktueller Anzeigestatus der Karte. */
   @Setter
   private CardState state;

   /**
    * Erzeugt eine neue Karte; Anfangsstatus ist {@link CardState#FACE_DOWN}.
    *
    * @param id     eindeutige Karten-ID
    * @param pairId Paar-ID (geteilt mit der passenden Karte)
    * @param symbol das Symbol auf der Kartenrückseite
    */
   public Card(int id, int pairId, String symbol) {
      this.id = id;
      this.pairId = pairId;
      this.symbol = symbol;
      this.state = CardState.FACE_DOWN;
   }
}
