package memory.model;

import lombok.Getter;

/**
 * Represents a player with a name and current score.
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
@Getter
public class Player {

   /** Display name of the player. */
   private final String name;

   /** Number of pairs found in the current game session. */
   private int score;

   /**
    * Creates a new player with a score of 0.
    *
    * @param name display name of the player
    */
   public Player(String name) {
      this.name = name;
      this.score = 0;
   }

   /** Increases the score by one found pair. */
   public void addScore() {
      score++;
   }
}
