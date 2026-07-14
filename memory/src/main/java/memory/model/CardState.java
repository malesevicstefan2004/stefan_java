package memory.model;

/**
 * Possible display states of a playing card.
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
public enum CardState {

   /** Card is face-down (hidden). */
   FACE_DOWN,

   /** Card is face-up (revealed). */
   FACE_UP,

   /** Card is permanently visible (pair has been found). */
   MATCHED
}
