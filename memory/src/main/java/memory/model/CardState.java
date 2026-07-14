package memory.model;

/**
 * Mögliche Anzeigestati einer Spielkarte.
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
public enum CardState {

   /** Karte ist verdeckt. */
   FACE_DOWN,

   /** Karte ist aufgedeckt. */
   FACE_UP,

   /** Karte ist dauerhaft sichtbar (Paar gefunden). */
   MATCHED
}
