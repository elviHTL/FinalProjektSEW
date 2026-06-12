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
 * GUI-Ansicht für das Solar-Quiz Spiel.
 * Zeigt Fragen an, nimmt Antworten entgegen und
 * zeigt am Ende die erreichte Punktzahl.
 *
 * @author Keron
 * @version 1.0
 */
public class SolarGameView {

    /** Spiellogik mit Punktestand und Fortschritt */
    private SolarGame spiel;

    /** Hilfsobjekt mit allen Fragen und Antworten */
    private GameHelper helper;

    /** Zeigt die aktuelle Frage an */
    private Label frageLabel;

    /** Zeigt den aktuellen Punktestand an */
    private Label punkteLabel;

    /** Zeigt den Fragenfortschritt an */
    private Label fortschrittLabel;

    /** Eingabefeld für die Antwort */
    private TextField antwortField;

    /** Zeigt ob die letzte Antwort richtig oder falsch war */
    private Label feedbackLabel;

    /**
     * Erstellt eine neue SolarGameView-Instanz.
     */
    public SolarGameView() {
        this.helper = new GameHelper();
        this.spiel  = new SolarGame("Spieler");
    }

    /**
     * Baut die komplette Szene für das Solar-Quiz auf.
     *
     * @param stage Das Hauptfenster der Anwendung
     * @return Die fertige JavaFX-Szene
     */
    public Scene erstelleScene(Stage stage) {

        Label titel = new Label("🌞 Solar Quiz");
        titel.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        titel.setTextFill(Color.web("#f5a623"));

        fortschrittLabel = new Label("Frage 1 / " + helper.getAnzahlFragen());
        fortschrittLabel.setTextFill(Color.web("#aaaaaa"));
        fortschrittLabel.setFont(Font.font("Arial", 13));

        punkteLabel = new Label("Punkte: 0");
        punkteLabel.setTextFill(Color.web("#f5a623"));
        punkteLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        HBox statusBar = new HBox(20, fortschrittLabel, punkteLabel);
        statusBar.setAlignment(Pos.CENTER);

        frageLabel = new Label(helper.getFrage(0));
        frageLabel.setTextFill(Color.WHITE);
        frageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        frageLabel.setWrapText(true);
        frageLabel.setMaxWidth(320);
        frageLabel.setAlignment(Pos.CENTER);
        frageLabel.setStyle(
                "-fx-background-color: #16213e;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 20;"
        );

        antwortField = new TextField();
        antwortField.setPromptText("Deine Antwort hier...");
        antwortField.setPrefWidth(280);
        antwortField.setStyle(
                "-fx-background-color: #16213e;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: #555555;" +
                        "-fx-background-radius: 6;" +
                        "-fx-padding: 10;"
        );
        antwortField.setOnAction(e -> pruefeAntwort(stage));

        feedbackLabel = new Label("");
        feedbackLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        Button antwortBtn = new Button("✔ Antworten");
        antwortBtn.setStyle(
                "-fx-background-color: #f5a623;" +
                        "-fx-text-fill: #1a1a2e;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 10 30 10 30;" +
                        "-fx-background-radius: 8;"
        );
        antwortBtn.setOnAction(e -> pruefeAntwort(stage));

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

        VBox layout = new VBox(18, titel, statusBar, frageLabel,
                antwortField, feedbackLabel, antwortBtn, zurueckBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #1a1a2e;");

        return new Scene(layout, 420, 520);
    }

    /**
     * Prüft die Antwort, zeigt Feedback und geht zur nächsten Frage.
     *
     * @param stage Das Hauptfenster der Anwendung
     */
    private void pruefeAntwort(Stage stage) {
        String eingabe = antwortField.getText();

        if (eingabe.isEmpty()) {
            feedbackLabel.setText("⚠ Bitte eine Antwort eingeben!");
            feedbackLabel.setTextFill(Color.web("#ff6b6b"));
            return;
        }

        int index = spiel.getAktuelleFrageIndex();

        if (helper.pruefeAntwort(index, eingabe)) {
            spiel.punkteHinzufuegen(10);
            feedbackLabel.setText("✔ Richtig! +10 Punkte");
            feedbackLabel.setTextFill(Color.web("#4caf50"));
        } else {
            feedbackLabel.setText("✘ Falsch! Richtig: " + helper.getAntwort(index));
            feedbackLabel.setTextFill(Color.web("#ff6b6b"));
        }

        spiel.naechsteFrage();
        antwortField.clear();
        punkteLabel.setText("Punkte: " + spiel.getPunkte());

        if (spiel.laeuftNoch(helper.getAnzahlFragen())) {
            int naechster = spiel.getAktuelleFrageIndex();
            frageLabel.setText(helper.getFrage(naechster));
            fortschrittLabel.setText("Frage " + (naechster + 1) +
                    " / " + helper.getAnzahlFragen());
        } else {
            zeigeErgebnis(stage);
        }
    }

    /**
     * Zeigt das Endergebnis nach Abschluss aller Fragen an.
     *
     * @param stage Das Hauptfenster der Anwendung
     */
    private void zeigeErgebnis(Stage stage) {
        Label ergebnisTitel = new Label("🏆 Spiel beendet!");
        ergebnisTitel.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        ergebnisTitel.setTextFill(Color.web("#f5a623"));

        int max = helper.getAnzahlFragen() * 10;
        Label ergebnisLabel = new Label(
                spiel.getSpielerName() + " hat " +
                        spiel.getPunkte() + " / " + max + " Punkte erreicht!"
        );
        ergebnisLabel.setTextFill(Color.WHITE);
        ergebnisLabel.setFont(Font.font("Arial", 15));
        ergebnisLabel.setWrapText(true);
        ergebnisLabel.setAlignment(Pos.CENTER);

        String bewertung;
        if (spiel.getPunkte() == max)          bewertung = "🌟 Perfekt!";
        else if (spiel.getPunkte() >= max / 2) bewertung = "👍 Gut gemacht!";
        else                                   bewertung = "📚 Noch üben!";

        Label bewertungLabel = new Label(bewertung);
        bewertungLabel.setTextFill(Color.web("#f5a623"));
        bewertungLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Button nochmalBtn = new Button("🔄 Nochmal spielen");
        nochmalBtn.setStyle(
                "-fx-background-color: #f5a623;" +
                        "-fx-text-fill: #1a1a2e;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 10 24 10 24;" +
                        "-fx-background-radius: 8;"
        );
        nochmalBtn.setOnAction(e -> {
            this.spiel = new SolarGame("Spieler");
            stage.setScene(erstelleScene(stage));
        });

        Button menuBtn = new Button("← Hauptmenü");
        menuBtn.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #aaaaaa;" +
                        "-fx-font-size: 12px;" +
                        "-fx-cursor: hand;"
        );
        menuBtn.setOnAction(e -> {
            MainApp mainApp = new MainApp();
            mainApp.start(stage);
        });

        VBox layout = new VBox(20, ergebnisTitel, ergebnisLabel,
                bewertungLabel, nochmalBtn, menuBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(60));
        layout.setStyle("-fx-background-color: #1a1a2e;");

        stage.setScene(new Scene(layout, 420, 380));
    }
}