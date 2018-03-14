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

    //UUID
    private static final UUID MY_UUID =  UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //Adresse
    private String adresse;

    //Necessaire BT
    BluetoothAdapter btAdapter;
    BluetoothSocket btSocket;

    //Flux
    OutputStream outputStream;
    InputStream inputStream;



    TextView nomOrdi = null;
    ListView choix = null;
    static String EXTRA_NOM_PC = "random";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_principale);

        nomOrdi = (TextView) findViewById(R.id.nomOrdi);
        final Intent intent = getIntent();
        nomOrdi.setText(intent.getStringExtra(MainActivity.EXTRA));
        adresse = nomOrdi.getText().toString();

        //PARTIE EXPERIMENTALE POUR LA CONNEXION
        connexion();

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
                        startActivity(intentControleSouris);
                        break;
                    case 1 :
                        Toast.makeText(Gestion_principale.this, "Non implémenté", Toast.LENGTH_SHORT).show();
                        break;
                    case 2 :
                        Toast.makeText(Gestion_principale.this, "Non implémenté", Toast.LENGTH_SHORT).show();
                        break;
                    case 3 :
                        Intent intentOptions = new Intent(Gestion_principale.this, Options_ordinateur.class);
                        intentOptions.putExtra(Gestion_principale.EXTRA_NOM_PC, nomOrdi.getText());
                        startActivity(intentOptions);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void connexion() {

        btAdapter = BluetoothAdapter.getDefaultAdapter();

        BluetoothDevice PC = btAdapter.getRemoteDevice(adresse);

        try {
            btSocket = PC.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            AlertBox("Erreur", "Erreur lors de la création du socket : connexion()");
        }

        btAdapter.cancelDiscovery();

        try {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
                AlertBox("Erreur", "Erreur lors de l'ouverture du socket");
            } catch (IOException e1) {
                AlertBox("Erreur", "Erreur lors de la fermeture du socket suite à l'erreur empechant son ouverture");
            }
        }

        try {
            outputStream = btSocket.getOutputStream();
            inputStream = btSocket.getInputStream();
        } catch (IOException e) {
            AlertBox("Erreur", "Erreur lors de l'ouverture du flux de sortie");
        }

    }

    //Créée une boite de dialogue pour la gestion des exceptions
    public void AlertBox(String titre, final String message) {
        new AlertDialog.Builder(Gestion_principale.this)
                .setTitle(titre)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
    }



}
