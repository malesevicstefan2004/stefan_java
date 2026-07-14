package memory.model;

import lombok.Getter;

/**
 * Repräsentiert einen Spieler mit Name und aktuellem Punktestand.
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
@Getter
public class Player {

   /** Anzeigename des Spielers. */
   private final String name;

   /** Anzahl der gefundenen Paare in der laufenden Partie. */
   private int score;

   /**
    * Erzeugt einen neuen Spieler mit Punktestand 0.
    *
    * @param name Anzeigename des Spielers
    */
   public Player(String name) {
      this.name = name;
      this.score = 0;
   }

   /** Erhöht den Punktestand um ein gefundenes Paar. */
   public void addScore() {
      score++;
   }
}
