# Skyjo

## Organisatorischer Rahmen
Die Bearbeitung dieser Aufgabe erfolgt in einem Team von drei bis vier Studierenden. 
Bei Teamarbeit ist darauf zu achten, dass die Beiträge aller Partner im Code (z. B. durch Autoren-Tags in der JavaDoc) ersichtlich sind.
Eine gleichmäßige Verteilung der Arbeitslast wird vorausgesetzt.

## Projektauftrag
Entwickle eine digitale Umsetzung des Kartenspiels **Skyjo** für 2 bis 4 Spieler.
Das System muss die Logik strikt von der Anzeige trennen (**MVC-Pattern**).
Zusätzlich zur ausführbaren Anwendung muss ein **UML-Klassendiagramm** sowie eine aussagekräftige **JavaDoc-Dokumentation** erstellt und abgegeben werden.

## Funktionale Anforderungen

### Konfiguration & Setup
* **#FANF01:** Das System muss die Erfassung von 2 bis 4 Spielern (Namen) ermöglichen
* **#FANF02:** Das System muss ein Kartendeck mit Werten von -2 bis 12 (entsprechend der Skyjo-Verteilung) generieren und mischen
* **#FANF03:** Das System muss jedem Spieler ein verdecktes 3x4-Raster (12 Karten) zuweisen
* **#FANF04:** Zu Spielbeginn muss das System jedem Spieler ermöglichen, genau zwei seiner Karten aufzudecken. Der Spieler mit der höchsten Summe beginnt

### Spielmechanik & Logik
* **#FANF05:** Das System muss den rundenbasierten Ablauf steuern: Ziehen vom verdeckten Stapel oder vom offenen Ablagestapel
* **#FANF06:** Bei Ziehen vom verdeckten Stapel muss der Spieler entscheiden können, die Karte gegen eine (offene oder verdeckte) Karte seines Rasters zu tauschen oder sie direkt auf den Ablagestapel zu werfen (und dann eine eigene Karte aufzudecken)
* **#FANF07:** **Sonderregel "Spalte löschen":** Wenn in einer vertikalen Spalte drei identische Zahlen liegen, muss das System diese Spalte sofort entfernen und auf den Ablagestapel legen
* **#FANF08:** Das System muss die Interaktion so gestalten, dass verdeckte Karten erst durch eine bewusste Aktion (Tausch/Aufdecken) sichtbar werden

### Punkte & Rundenverwaltung
* **#FANF09:** Eine Runde endet sofort, sobald ein Spieler alle seine Karten aufgedeckt hat. Alle anderen Spieler dürfen noch einen letzten Zug machen
* **#FANF10:** Das System muss die Punkte nach jeder Runde summieren. **Regel-Check:** Hat der Spieler, der die Runde beendet hat, nicht die niedrigste Punktzahl, zählen seine Punkte in dieser Runde doppelt
* **#FANF11:** Das System muss den aktuellen Spielstand aller Spieler tabellarisch während und zwischen den Runden anzeigen

### Spielende
* **#FANF12:** Das Spiel endet insgesamt, sobald ein Spieler die Grenze von **100 Punkten** erreicht oder überschreitet
* **#FANF13:** Das System muss nach Spielende den Spieler mit der niedrigsten Gesamtpunktzahl als Sieger küren

## Bewertungsmatrix

| Kategorie | Kriterien | Punkte |
| :--- | :--- | :--- |
| **GUI** | Übersichtliche Darstellung von bis zu 4 Rastern; intuitive Drag-&-Drop oder Klick-Steuerung | 20 |
| **Spiellogik** | Korrekte Implementierung der Spalten-Lösch-Regel und der Punkte-Verdopplung | 30 |
| **Architektur** | Saubere MVC-Trennung, Kapselung und Einhaltung von Java-Konventionen | 20 |
| **Entwurf** | UML-Klassendiagramm (Struktur und Beziehungen korrekt dargestellt) | 10 |
| **Dokumentation** | Vollständigkeit der JavaDoc für alle Klassen und öffentlichen Methoden | 20 |
| **Gesamt** | | **100** |
