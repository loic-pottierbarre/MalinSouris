package javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.event.EventHandler;

public class AttenteConnexion extends Application {

    private BorderPane bpCentre = new BorderPane();
    private Label attenteCo = new Label("Attente d'appareils bluetooths ...");
    private Button desactiver = new Button("Désactiver");

    public void start(Stage stage) throws Exception {
        initBP();
        capterBluetooth();
        stage.setScene(new Scene(bpCentre));
        stage.setResizable(false);
        stage.show();

        EventHandler<ActionEvent> ecouteurDesactiver = event -> {/*Accueil accueil = new Accueil();*/ValiderAppareil validerAppareil = new ValiderAppareil();
            try {
                //accueil.start(stage);
                validerAppareil.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        desactiver.setOnAction(ecouteurDesactiver);
    }

    private void initBP() {
        bpCentre.setPrefSize(400,400);
        bpCentre.setPadding(new Insets(10,10,10,10));
        bpCentre.setCenter(attenteCo);
        bpCentre.setBottom(desactiver);
    }

    private void capterBluetooth() {
        /*
        Insérer ici le code dès qu'un appareil bluetooth se connecte.
         */
    }
}
