import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * GUI-Ansicht für den PV-Rechner.
 * Zeigt ein Formular mit Eingabefeldern und berechnet
 * Jahresertrag sowie Amortisationszeit.
 *
 * @author Elvi
 * @version 1.0
 */
public class PVRechnerView {

    /** Berechnungslogik für PV-Werte */
    private PVCalculator calculator;

    /** Eingabefeld für die Leistung in kWp */
    private TextField leistungField;

    /** Eingabefeld für die Sonnenstunden pro Jahr */
    private TextField sonnenstundenField;

    /** Eingabefeld für den Strompreis in €/kWh */
    private TextField strompreisField;

    /** Eingabefeld für die Gesamtkosten der Anlage */
    private TextField kostenField;

    /** Zeigt den berechneten Jahresertrag an */
    private Label ertragsLabel;

    /** Zeigt die berechnete Amortisationszeit an */
    private Label amortLabel;

    /** Zeigt Fehlermeldungen bei falscher Eingabe an */
    private Label fehlerLabel;

    /**
     * Erstellt eine neue PVRechnerView-Instanz.
     */
    public PVRechnerView() {
        this.calculator = new PVCalculator();
    }

    /**
     * Baut die komplette Szene für den PV-Rechner auf.
     *
     * @param stage Das Hauptfenster der Anwendung
     * @return Die fertige JavaFX-Szene
     */
    public Scene erstelleScene(Stage stage) {

        Label titel = new Label("☀ PV Rechner");
        titel.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        titel.setTextFill(Color.web("#f5a623"));

        Label untertitel = new Label("Berechne deinen Solarertrag");
        untertitel.setFont(Font.font("Arial", 14));
        untertitel.setTextFill(Color.web("#aaaaaa"));

        VBox titelBox = new VBox(4, titel, untertitel);
        titelBox.setAlignment(Pos.CENTER);
        titelBox.setPadding(new Insets(0, 0, 20, 0));

        leistungField      = erstelleTextField("z.B. 5.0");
        sonnenstundenField = erstelleTextField("z.B. 1000");
        strompreisField    = erstelleTextField("z.B. 0.30");
        kostenField        = erstelleTextField("z.B. 8000");

        GridPane formular = new GridPane();
        formular.setHgap(12);
        formular.setVgap(14);
        formular.setAlignment(Pos.CENTER);
        formular.add(erstelleLabel("Leistung (kWp):"),      0, 0);
        formular.add(leistungField,                         1, 0);
        formular.add(erstelleLabel("Sonnenstunden/Jahr:"),  0, 1);
        formular.add(sonnenstundenField,                    1, 1);
        formular.add(erstelleLabel("Strompreis (€/kWh):"),  0, 2);
        formular.add(strompreisField,                       1, 2);
        formular.add(erstelleLabel("Anlagenkosten (€):"),   0, 3);
        formular.add(kostenField,                           1, 3);

        fehlerLabel = new Label("");
        fehlerLabel.setTextFill(Color.web("#ff6b6b"));
        fehlerLabel.setFont(Font.font("Arial", 12));

        Button berechnenBtn = new Button("⚡ Berechnen");
        berechnenBtn.setStyle(
                "-fx-background-color: #f5a623;" +
                        "-fx-text-fill: #1a1a2e;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 10 30 10 30;" +
                        "-fx-background-radius: 8;"
        );
        berechnenBtn.setOnAction(e -> berechne());

        ertragsLabel = erstelleErgebnisLabel("--- kWh");
        amortLabel   = erstelleErgebnisLabel("--- Jahre");

        GridPane ergebnisse = new GridPane();
        ergebnisse.setHgap(12);
        ergebnisse.setVgap(10);
        ergebnisse.setAlignment(Pos.CENTER);
        ergebnisse.setStyle(
                "-fx-background-color: #16213e;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 16;"
        );
        ergebnisse.add(erstelleLabel("Jahresertrag:"), 0, 0);
        ergebnisse.add(ertragsLabel,                   1, 0);
        ergebnisse.add(erstelleLabel("Amortisation:"), 0, 1);
        ergebnisse.add(amortLabel,                     1, 1);

        Button zurueckBtn = new Button("← Zurück");
        zurueckBtn.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #aaaaaa;" +
                        "-fx-font-size: 12px;" +
                        "-fx-cursor: hand;"
        );
        zurueckBtn.setOnAction(e -> {
            MainApp mainApp = new MainApp();
            mainApp.start(stage);
        });

        VBox layout = new VBox(16, titelBox, formular, fehlerLabel,
                berechnenBtn, ergebnisse, zurueckBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #1a1a2e;");

        return new Scene(layout, 420, 560);
    }

    /**
     * Liest die Eingaben aus, berechnet die Werte und zeigt die Ergebnisse.
     * Bei ungültiger Eingabe wird eine Fehlermeldung angezeigt.
     */
    private void berechne() {
        fehlerLabel.setText("");
        try {
            double leistung      = Double.parseDouble(leistungField.getText().replace(",", "."));
            double sonnenstunden = Double.parseDouble(sonnenstundenField.getText().replace(",", "."));
            double strompreis    = Double.parseDouble(strompreisField.getText().replace(",", "."));
            double kosten        = Double.parseDouble(kostenField.getText().replace(",", "."));

            double ertrag = calculator.berechneJahresertrag(leistung, sonnenstunden);
            double amort  = calculator.berechneAmortisation(kosten, ertrag, strompreis);

            ertragsLabel.setText(String.format("%.0f kWh", ertrag));
            amortLabel.setText(String.format("%.1f Jahre", amort));

        } catch (NumberFormatException e) {
            fehlerLabel.setText("⚠ Bitte nur Zahlen eingeben (Punkt statt Komma).");
        }
    }

    /**
     * Erstellt ein Eingabefeld mit Platzhaltertext.
     *
     * @param platzhalter Hinweistext im leeren Feld
     * @return Das fertige TextField
     */
    private TextField erstelleTextField(String platzhalter) {
        TextField tf = new TextField();
        tf.setPromptText(platzhalter);
        tf.setPrefWidth(160);
        tf.setStyle(
                "-fx-background-color: #16213e;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: #555555;" +
                        "-fx-background-radius: 6;" +
                        "-fx-padding: 8;"
        );
        return tf;
    }

    /**
     * Erstellt ein Beschriftungs-Label.
     *
     * @param text Der anzuzeigende Text
     * @return Das fertige Label
     */
    private Label erstelleLabel(String text) {
        Label l = new Label(text);
        l.setTextFill(Color.web("#cccccc"));
        l.setFont(Font.font("Arial", 13));
        return l;
    }

    /**
     * Erstellt ein hervorgehobenes Ergebnis-Label.
     *
     * @param startText Anfänglicher Anzeigetext
     * @return Das fertige Label
     */
    private Label erstelleErgebnisLabel(String startText) {
        Label l = new Label(startText);
        l.setTextFill(Color.web("#f5a623"));
        l.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        return l;
    }
}