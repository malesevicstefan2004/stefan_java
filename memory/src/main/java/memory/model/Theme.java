package memory.model;

/**
 * Available card themes for the Memory game.
 * Each theme contains at least 18 unique symbols (for a 6×6 board = 18 pairs).
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
public enum Theme {

   /** Geometric shapes from Unicode block U+25A0–U+25FF. */
   GEOMETRY("Geometry ■", new String[]{
         "■", "□", "▲", "▼",
         "◆", "◇", "●", "○",
         "◐", "◑", "▶", "◀",
         "★", "☆", "◉", "◎",
         "▪", "▫"
   }),

   /** Chess pieces, playing card suits, and musical notes. */
   CHESS("Chess ♔", new String[]{
         "♔", "♕", "♖", "♗",
         "♘", "♙", "♚", "♛",
         "♜", "♝", "♞", "♟",
         "♠", "♥", "♦", "♣",
         "♩", "♪"
   }),

   /** Arrows and mathematical operators. */
   ARROWS("Arrows ←", new String[]{
         "←", "→", "↑", "↓",
         "↔", "↕", "↖", "↗",
         "↘", "↙", "∞", "≠",
         "±", "×", "÷", "∑",
         "√", "≤"
   });

   /** The display name shown in the menu. */
   private final String displayName;

   /** The symbols belonging to this theme. */
   private final String[] symbols;

   /**
    * Creates a theme with the given display name and symbol array.
    *
    * @param displayName the name shown in the start menu
    * @param symbols     the symbols of this theme
    */
   Theme(String displayName, String[] symbols) {
      this.displayName = displayName;
      this.symbols = symbols;
   }

   /** Returns the display name of this theme. */
   public String getDisplayName() {
      return displayName;
   }

   /** Returns the symbol array of this theme. */
   public String[] getSymbols() {
      return symbols;
   }

   @Override
   public String toString() {
      return displayName;
   }
}
