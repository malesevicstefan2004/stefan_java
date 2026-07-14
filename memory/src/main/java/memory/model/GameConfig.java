package memory.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Configuration parameters for a new Memory game session (player count, board size, theme).
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
@Getter
@Setter
public class GameConfig {

   /** Number of players (1 or 2). */
   private int playerCount;

   /** Number of rows on the board. */
   private int rows;

   /** Number of columns on the board. */
   private int cols;

   /** The selected card theme. */
   private Theme theme;

   /** Display name of Player 1. */
   private String player1Name;

   /** Display name of Player 2. */
   private String player2Name;

   /** Creates a configuration with default values: 1 player, 4×4 board, theme TIERE. */
   public GameConfig() {
      this.playerCount = 1;
      this.rows = 4;
      this.cols = 4;
      this.theme = Theme.TIERE;
      this.player1Name = "Player 1";
      this.player2Name = "Player 2";
   }
}
