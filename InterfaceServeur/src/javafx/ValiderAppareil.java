package javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.event.EventHandler;


public class ValiderAppareil extends Application {

    private BorderPane bpCentre = new BorderPane();
    private HBox hbSelection = new HBox();
    private Label lAppareilCo = new Label("L'appareil suivant a été capté : ");
    private Label lNomAppareil = new Label("Nom de l'appareil");
    private Button bAccepter = new Button("Accepter");
    private Button bRejeter = new Button("Rejeter");

    public void start(Stage stage) throws Exception {
        initBP();
        initHB();
        stage.setScene(new Scene(bpCentre));
        stage.setResizable(false);
        stage.show();

        EventHandler<ActionEvent> ecouteurAccepter = event -> {AppareilConnecte appareilConnecte = new AppareilConnecte();
            try {
                appareilConnecte.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        bAccepter.setOnAction(ecouteurAccepter);

        EventHandler<ActionEvent> ecouteurRejeter = event -> {AttenteConnexion attenteConnexion = new AttenteConnexion();
            try {
                attenteConnexion.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        bRejeter.setOnAction(ecouteurRejeter);
    }

    private void initHB() {
        hbSelection.getChildren().addAll(bAccepter, bRejeter);
        hbSelection.setAlignment(Pos.CENTER);
    }

    private void initBP() {
        bpCentre.setPrefSize(400,400);
        bpCentre.setPadding(new Insets(10,10,10,10));
        bpCentre.setTop(lAppareilCo);
        bpCentre.setCenter(lNomAppareil);
        bpCentre.setBottom(hbSelection);
    }
}
