package memory.model;

/**
 * Verfügbare Kartenmotive für das Memory-Spiel.
 * Jedes Motiv enthält mindestens 18 einzigartige Symbole (für 6×6 = 18 Paare).
 *
 * @author Stefan Malesevic
 * @version 1.0
 */
public enum Theme {

   /** Natur- und Himmels-Symbole. */
   TIERE("Natur ☀", new String[]{
         "☀", "☁", "☂", "☃",
         "☄", "★", "☆", "☽",
         "⚡", "❄", "✿", "☮",
         "✈", "☸", "⚓", "✦",
         "✧", "☾"
   }),

   /** Tierkreiszeichen und Schachfiguren. */
   FRUECHTE("Zodiak ♈", new String[]{
         "♈", "♉", "♊", "♋",
         "♌", "♍", "♎", "♏",
         "♐", "♑", "♒", "♓",
         "♔", "♕", "♖", "♗",
         "♘", "♙"
   }),

   /** Klassische Karten- und Spielsymbole. */
   KARTEN("Karten ♠", new String[]{
         "♠", "♥", "♦", "♣",
         "★", "✶", "☀", "☽",
         "⚡", "❄", "✿", "☮",
         "♞", "♟", "⚜", "⚓",
         "⚽", "♪"
   });

   /** Der im Menü angezeigte Name des Motivs. */
   private final String displayName;

   /** Die Symbole dieses Motivs. */
   private final String[] symbols;

   /**
    * Erstellt ein Motiv mit Anzeigename und Symbolarray.
    *
    * @param displayName der im Startmenü angezeigte Name
    * @param symbols     die Symbole dieses Motivs
    */
   Theme(String displayName, String[] symbols) {
      this.displayName = displayName;
      this.symbols = symbols;
   }

   /** Gibt den Anzeigenamen des Motivs zurück. */
   public String getDisplayName() {
      return displayName;
   }

   /** Gibt das Symbolarray dieses Motivs zurück. */
   public String[] getSymbols() {
      return symbols;
   }

   @Override
   public String toString() {
      return displayName;
   }
}
