/**
 * Hilfsklasse für das Solar-Quiz.
 * Enthält alle Fragen, Antworten und Hilfsmethoden.
 *
 * @author Keron
 * @version 1.0
 */
public class GameHelper {

    /** Liste aller Spielfragen */
    private String[] fragen = {
            "Wie viel Watt hat ein typisches Solarmodul?",
            "Was bedeutet die Abkürzung kWp?",
            "Welche Farbe haben die meisten Solarzellen?",
            "Was wandelt ein Wechselrichter um?",
            "Wie viele Stunden scheint die Sonne in Deutschland durchschnittlich pro Jahr?"
    };

    /** Richtige Antworten zu den Fragen */
    private String[] antworten = {
            "300",
            "Kilowatt Peak",
            "Dunkelblau",
            "Gleichstrom in Wechselstrom",
            "1600"
    };

    /**
     * Gibt eine Frage anhand des Index zurück.
     *
     * @param index Index der Frage (0-basiert)
     * @return Die Frage als Text
     */
    public String getFrage(int index) {
        return fragen[index];
    }

    /**
     * Gibt die richtige Antwort einer Frage zurück.
     *
     * @param index Index der Frage
     * @return Die richtige Antwort
     */
    public String getAntwort(int index) {
        return antworten[index];
    }

    /**
     * Prüft ob die gegebene Antwort korrekt ist.
     *
     * @param index   Index der Frage
     * @param antwort Die vom Spieler eingegebene Antwort
     * @return true wenn die Antwort richtig ist
     */
    public boolean pruefeAntwort(int index, String antwort) {
        return antworten[index].equalsIgnoreCase(antwort.trim());
    }

    /**
     * Gibt die Gesamtanzahl der Fragen zurück.
     *
     * @return Anzahl der Fragen
     */
    public int getAnzahlFragen() {
        return fragen.length;
    }
}