/**
 * Berechnet wichtige Werte für eine PV-Anlage.
 *
 * @author Elvi
 * @version 1.0
 */
public class PVCalculator {

    /** Wirkungsgrad des Gesamtsystems (75%) */
    private static final double SYSTEMWIRKUNGSGRAD = 0.75;

    /**
     * Berechnet den geschätzten Jahresertrag einer PV-Anlage.
     *
     * @param leistungKWp    Installierte Leistung in Kilowatt-Peak
     * @param sonnenstunden  Durchschnittliche Sonnenstunden pro Jahr
     * @return Geschätzter Jahresertrag in kWh
     */
    public double berechneJahresertrag(double leistungKWp, double sonnenstunden) {
        return leistungKWp * sonnenstunden * SYSTEMWIRKUNGSGRAD;
    }

    /**
     * Berechnet die Amortisationszeit der Anlage.
     *
     * @param kosten           Gesamtkosten der Anlage in Euro
     * @param jahresertragKWh  Jahresertrag in kWh
     * @param strompreisEuro   Strompreis in Euro pro kWh
     * @return Amortisationszeit in Jahren
     */
    public double berechneAmortisation(double kosten,
                                       double jahresertragKWh,
                                       double strompreisEuro) {
        double ersparnis = jahresertragKWh * strompreisEuro;
        if (ersparnis == 0) return 0;
        return kosten / ersparnis;
    }
}