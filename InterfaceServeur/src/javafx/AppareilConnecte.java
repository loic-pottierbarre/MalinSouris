package javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.scene.control.Label;

import javafx.event.EventHandler;

public class AppareilConnecte extends Application {

    private BorderPane bpCentre = new BorderPane();
    private Label lAppareilCo = new Label("L'appareil suivant s'est connecté : ");
    private Label lNomAppareil = new Label("Nom de l'appareil");
    private Button desactiver = new Button("Désactiver");

    public void start(Stage stage) throws Exception {
        initBP();
        stage.setScene(new Scene(bpCentre));
        stage.setResizable(false);
        stage.show();

        EventHandler<ActionEvent> ecouteurDesactiver = event -> {Accueil accueil = new Accueil();
            try {
                accueil.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        desactiver.setOnAction(ecouteurDesactiver);
    }

    private void initBP() {
        bpCentre.setPrefSize(400,400);
        bpCentre.setPadding(new Insets(10,10,10,10));
        bpCentre.setTop(lAppareilCo);
        bpCentre.setCenter(lNomAppareil);
        bpCentre.setBottom(desactiver);
    }
}
