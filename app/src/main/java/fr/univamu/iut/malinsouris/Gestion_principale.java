package fr.univamu.iut.malinsouris;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;


/**
 * Created by lpott on 06/12/2017.
 */

public class Gestion_principale extends Activity {


    //Adresse obtenue de la première activité
    private String adresse;

    //Connexion que l'on instancie pour la première fois
    private Connexion connexion;


    TextView nomOrdi = null;
    ListView choix = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_principale);

        nomOrdi = (TextView) findViewById(R.id.nomOrdi);
        final Intent intent = getIntent();
        nomOrdi.setText(intent.getStringExtra("NOM"));
        adresse = intent.getStringExtra("ADRESSE");

        //PARTIE EXPERIMENTALE POUR LA CONNEXION

        connexion = Connexion.getINSTANCE(); //Ici on initialise la connexion entre l'ordi et le téléphone

        //Boite de dialogue après connection
        new AlertDialog.Builder(Gestion_principale.this)
                .setTitle("Connexion")
                .setMessage("La connexion a été créée !")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });


        //FIN PARTIE EXPERIMENTALE

        ArrayList<String> menu = new ArrayList<>();
        menu.add("Contrôle de la souris");
        menu.add("Obtenir de fichiers");
        menu.add("Lancer une présentation");
        menu.add("Options");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Gestion_principale.this, android.R.layout.simple_list_item_1, menu);
        choix = (ListView)findViewById(R.id.menuChoix) ;
        choix.setAdapter(adapter);
        choix.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        switch(i) {
                    case 0 :
                        Intent intentControleSouris = new Intent(Gestion_principale.this, ControleSouris.class);
                        try {
                            Connexion.getINSTANCE().getOutputStream().write(1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        startActivity(intentControleSouris);
                        break;
                    case 1 :
                        Toast.makeText(Gestion_principale.this, "Non implémenté", Toast.LENGTH_SHORT).show();
                        break;
                    case 2 :
                        Intent intentDiapo = new Intent(Gestion_principale.this, Diapo.class);
                        try {
                            Connexion.getINSTANCE().getOutputStream().write(2);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        intentDiapo.putExtra("NOM", nomOrdi.getText());
                        startActivity(intentDiapo);
                        break;
                    case 3 :
                        Intent intentOptions = new Intent(Gestion_principale.this, Options_ordinateur.class);
                        intentOptions.putExtra("NOM", nomOrdi.getText());
                        startActivity(intentOptions);
                        break;
                    default:
                        break;
                }
            }
        });

    }



}
