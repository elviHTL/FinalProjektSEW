/**
 * Verwaltet den Spielzustand des Solar-Quiz.
 * Speichert Punkte, Spielername und Fortschritt.
 *
 * @author Keron
 * @version 1.0
 */
public class SolarGame {

    /** Name des Spielers */
    private String spielerName;

    /** Aktuelle Punktzahl */
    private int punkte;

    /** Index der aktuellen Frage */
    private int aktuelleFrageIndex;

    /**
     * Erstellt ein neues Spiel für den angegebenen Spieler.
     *
     * @param spielerName Name des Spielers
     */
    public SolarGame(String spielerName) {
        this.spielerName = spielerName;
        this.punkte = 0;
        this.aktuelleFrageIndex = 0;
    }

    /**
     * Fügt Punkte zur aktuellen Punktzahl hinzu.
     *
     * @param anzahl Anzahl der hinzuzufügenden Punkte
     */
    public void punkteHinzufuegen(int anzahl) {
        punkte += anzahl;
    }

    /**
     * Geht zur nächsten Frage weiter.
     */
    public void naechsteFrage() {
        aktuelleFrageIndex++;
    }

    /**
     * Gibt den Namen des Spielers zurück.
     *
     * @return Spielername
     */
    public String getSpielerName() {
        return spielerName;
    }

    /**
     * Gibt die aktuelle Punktzahl zurück.
     *
     * @return Aktuelle Punkte
     */
    public int getPunkte() {
        return punkte;
    }

    /**
     * Gibt den Index der aktuellen Frage zurück.
     *
     * @return Index der aktuellen Frage
     */
    public int getAktuelleFrageIndex() {
        return aktuelleFrageIndex;
    }

    /**
     * Prüft ob das Spiel noch läuft.
     *
     * @param gesamtFragen Gesamtanzahl der Fragen
     * @return true wenn noch Fragen übrig sind
     */
    public boolean laeuftNoch(int gesamtFragen) {
        return aktuelleFrageIndex < gesamtFragen;
    }
}