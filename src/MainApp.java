import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Hauptklasse der PV Solar App.
 * Startet die JavaFX-Anwendung und zeigt das Hauptmenü.
 *
 * @author Elvi
 * @version 1.0
 */
public class MainApp extends Application {

    /**
     * Einstiegspunkt der JavaFX-Anwendung.
     *
     * @param args Kommandozeilenargumente
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Baut das Hauptmenü auf und zeigt es im Fenster an.
     *
     * @param stage Das Hauptfenster der JavaFX-Anwendung
     */
    @Override
    public void start(Stage stage) {

        Label titel = new Label("☀ PV Solar App");
        titel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        titel.setTextFill(Color.web("#f5a623"));

        Label untertitel = new Label("Solarenergie berechnen & spielen");
        untertitel.setFont(Font.font("Arial", 14));
        untertitel.setTextFill(Color.web("#aaaaaa"));

        Button rechnerBtn = erstelleButton("⚡  PV Rechner starten");
        Button spielBtn   = erstelleButton("🎮  Solar Quiz starten");

        rechnerBtn.setOnAction(e -> {
            PVRechnerView rechnerView = new PVRechnerView();
            stage.setScene(rechnerView.erstelleScene(stage));
            stage.setTitle("PV Rechner");
        });

        spielBtn.setOnAction(e -> {
            SolarGameView spielView = new SolarGameView();
            stage.setScene(spielView.erstelleScene(stage));
            stage.setTitle("Solar Quiz");
        });

        VBox layout = new VBox(20, titel, untertitel, rechnerBtn, spielBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(60));
        layout.setStyle("-fx-background-color: #1a1a2e;");

        stage.setTitle("PV Solar App");
        stage.setScene(new Scene(layout, 400, 360));
        stage.show();
    }

    /**
     * Erstellt einen einheitlich gestalteten Button.
     *
     * @param text Der anzuzeigende Button-Text
     * @return Der fertige JavaFX-Button
     */
    private Button erstelleButton(String text) {
        Button btn = new Button(text);
        btn.setPrefWidth(260);
        btn.setStyle(
                "-fx-background-color: #16213e;" +
                        "-fx-text-fill: #f5a623;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 14 20 14 20;" +
                        "-fx-background-radius: 10;" +
                        "-fx-cursor: hand;"
        );
        btn.setOnMouseEntered(e -> btn.setStyle(
                "-fx-background-color: #f5a623;" +
                        "-fx-text-fill: #1a1a2e;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 14 20 14 20;" +
                        "-fx-background-radius: 10;" +
                        "-fx-cursor: hand;"
        ));
        btn.setOnMouseExited(e -> btn.setStyle(
                "-fx-background-color: #16213e;" +
                        "-fx-text-fill: #f5a623;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 14 20 14 20;" +
                        "-fx-background-radius: 10;" +
                        "-fx-cursor: hand;"
        ));
        return btn;
    }
}