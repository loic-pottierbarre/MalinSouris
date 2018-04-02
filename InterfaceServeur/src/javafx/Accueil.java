package javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.event.EventHandler;


public class Accueil extends Application {

    private BorderPane box = new BorderPane();
    private Button bConnexion = new Button("Activer");

    public void start(Stage stage) throws Exception {
        /*
        *Ici on appelle toutes les fonctions qui configurent la fenêtre
         */
        initFenetre();
        stage.setTitle("Malin Souris Serveur");
        stage.setScene(new Scene(box));

        EventHandler<ActionEvent> ecouteurActiver = event -> {AttenteConnexion attCo = new AttenteConnexion() ;
            try {
                attCo.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        bConnexion.setOnAction(ecouteurActiver);

        stage.show();
    }

    private void initFenetre() {
        box.setPrefSize(400,400);
        box.setPadding(new Insets(10,10,10,10));

        //box.getChildren().add(bConnexion);
        box.setCenter(bConnexion);
    }
}
