package memory.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Konfigurationsparameter für eine neue Memory-Partie (Spieleranzahl, Feldgröße, Motiv).
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
@Getter
@Setter
public class GameConfig {

   /** Spieleranzahl (1 oder 2). */
   private int playerCount;

   /** Anzahl der Zeilen auf dem Spielfeld. */
   private int rows;

   /** Anzahl der Spalten auf dem Spielfeld. */
   private int cols;

   /** Das gewählte Kartenmotiv. */
   private Theme theme;

   /** Anzeigename von Spieler 1. */
   private String player1Name;

   /** Anzeigename von Spieler 2. */
   private String player2Name;

   /** Erstellt eine Konfiguration mit Standardwerten: 1 Spieler, 4×4, Motiv TIERE. */
   public GameConfig() {
      this.playerCount = 1;
      this.rows = 4;
      this.cols = 4;
      this.theme = Theme.TIERE;
      this.player1Name = "Spieler 1";
      this.player2Name = "Spieler 2";
   }
}
