# Memory

## Organisatorischer Rahmen
Die Bearbeitung dieser Aufgabe kann wahlweise einzeln oder in einem Zweier-Team erfolgen.
Bei Teamarbeit ist darauf zu achten, dass die Beiträge beider Partner im Code (z. B. durch Autoren-Tags in der JavaDoc) ersichtlich sind.

## Projektauftrag
Entwickle ein Java-basiertes Memory-Spiel für maximal zwei Spieler.
Das System muss die Logik strikt von der Anzeige trennen (**MVC-Pattern**).
Zusätzlich zur ausführbaren Anwendung muss ein **UML-Klassendiagramm** sowie eine aussagekräftige **JavaDoc-Dokumentation** erstellt und abgegeben werden.

## Funktionale Anforderungen

### Konfiguration & Start
* **#FANF01:** Das System muss dem Benutzer vor Spielbeginn die Auswahl zwischen einem **Ein-Spieler-Modus** und einem **Zwei-Spieler-Modus** ermöglichen
* **#FANF02:** Das System muss dem Benutzer ermöglichen, die Spielfeldgröße zwischen **4x4** und **6x6** Karten zu wählen
* **#FANF03:** Das System muss dem Benutzer ermöglichen, zwischen mindestens zwei verschiedenen **Bildmotiven** (Themengruppen) zu wählen
* **#FANF04:** Das System muss die Kartenpaare bei jedem Spielstart **zufällig** auf dem Spielfeld verteilen

### Spielmechanik
* **#FANF05:** Das System muss alle Karten zu Beginn im **verdeckten Zustand** (einheitliches Rückseiten-Bild) anzeigen
* **#FANF06:** Das System muss das Motiv einer Karte anzeigen, sobald der aktive Benutzer die entsprechende Schaltfläche anklickt
* **#FANF07:** Sobald zwei Karten aufgedeckt wurden, muss das System die Identität der beiden Motive prüfen
* **#FANF08:** Wenn zwei aufgedeckte Karten nicht übereinstimmen, muss das System beide Karten nach einer Verzögerung von **1 bis 2 Sekunden** automatisch wieder verdecken
* **#FANF09:** Das System muss sicherstellen, dass die grafische Benutzeroberfläche während der Wartezeit (siehe Anforderung 8) **reaktionsfähig** bleibt (kein Blockieren des Threads)

### Punkte & Spielstatus
* **#FANF10:** Wenn ein Benutzer ein korrektes Paar findet, muss das System diesem Benutzer **einen Punkt** gutschreiben und ihm einen **weiteren Zug** gewähren
* **#FANF11:** Das System muss den aktuellen Punktestand beider Spieler, den Namen des aktiven Spielers und die Anzahl der Gesamtzüge permanent in der GUI anzeigen

### Spielende
* **#FANF12:** Das System muss das Spiel beenden, sobald alle Kartenpaare aufgedeckt wurden
* **#FANF13:** Nach Spielende muss das System den Sieger (höchste Punktzahl) oder bei Punktgleichheit ein **Unentschieden** anzeigen

## Bewertungsmatrix

| Kategorie | Kriterien | Punkte |
| :--- | :--- | :--- |
| **GUI** | Umsetzung der dynamischen Spielfeldgröße und permanente Statusanzeige | 20 |
| **Interaktion** | Korrekte Timer-Logik, Zufallsalgorithmus und fehlerfreie Paar-Prüfung | 20 |
| **Konfiguration** | Vollständiges Startmenü (Spieleranzahl, Größe, Motive) | 10 |
| **Architektur** | Saubere MVC-Trennung, Kapselung und Einhaltung von Java-Konventionen | 20 |
| **Entwurf** | UML-Klassendiagramm (Struktur und Beziehungen korrekt dargestellt) | 10 |
| **Dokumentation** | Vollständigkeit der JavaDoc für alle Klassen und öffentlichen Methoden | 20 |
| **Gesamt** | | **100** |
