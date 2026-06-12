/**
 * Repräsentiert ein Photovoltaik-Modul mit technischen Eigenschaften.
 *
 * @author Elvi
 * @version 1.0
 */
public class PVModule {

    /** Name des PV-Moduls */
    private String name;

    /** Nennleistung in Watt */
    private double leistungWatt;

    /** Spannung in Volt */
    private double spannungVolt;

    /**
     * Erstellt ein neues PV-Modul.
     *
     * @param name         Name des Moduls
     * @param leistungWatt Leistung in Watt
     * @param spannungVolt Spannung in Volt
     */
    public PVModule(String name, double leistungWatt, double spannungVolt) {
        this.name = name;
        this.leistungWatt = leistungWatt;
        this.spannungVolt = spannungVolt;
    }

    /**
     * Gibt den Namen des Moduls zurück.
     *
     * @return Name des Moduls
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt die Leistung in Watt zurück.
     *
     * @return Leistung in Watt
     */
    public double getLeistungWatt() {
        return leistungWatt;
    }

    /**
     * Gibt die Spannung in Volt zurück.
     *
     * @return Spannung in Volt
     */
    public double getSpannungVolt() {
        return spannungVolt;
    }

    /**
     * Gibt alle Modulinfos als formatierten Text zurück.
     *
     * @return Modulinformationen als String
     */
    public String getInfo() {
        return "Modul: " + name +
                " | Leistung: " + leistungWatt + " W" +
                " | Spannung: " + spannungVolt + " V";
    }
}