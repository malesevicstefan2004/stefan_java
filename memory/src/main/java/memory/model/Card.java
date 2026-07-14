package memory.model;

import lombok.Getter;
import lombok.Setter;

/**
 * A playing card with a unique ID, pair ID, symbol, and current state.
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
@Getter
public class Card {

   /** Unique card ID. */
   private final int id;

   /** Pair ID – two cards with the same pair ID form a matching pair. */
   private final int pairId;

   /** The Unicode symbol displayed on the card face. */
   private final String symbol;

   /** Current display state of the card. */
   @Setter
   private CardState state;

   /**
    * Creates a new card; initial state is {@link CardState#FACE_DOWN}.
    *
    * @param id     unique card ID
    * @param pairId pair ID shared with the matching card
    * @param symbol the symbol shown on the card face
    */
   public Card(int id, int pairId, String symbol) {
      this.id = id;
      this.pairId = pairId;
      this.symbol = symbol;
      this.state = CardState.FACE_DOWN;
   }
}
