package fr.univamu.iut.malinsouris;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by lpott on 14/03/2018.
 */

public class Diapo extends Activity {
    Button avancer, revenir, quitter;

    //UUID
    private static final UUID MY_UUID =  UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //Adresse
    private String adresse;

    //Necessaire BT
    private BluetoothAdapter btAdapter;
    private BluetoothSocket btSocket;

    //Flux
    private OutputStream outputStream;
    private InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diapo);

        avancer = (Button)findViewById(R.id.Avancer);
        revenir = (Button)findViewById(R.id.Revenir);
        quitter = (Button)findViewById(R.id.QuitterDiapo);

        final Intent intent = getIntent();
        adresse = intent.getStringExtra("ADRESSE");

        connexion();

        avancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    outputStream.write(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        revenir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    outputStream.write(2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                   outputStream.write(3);
                } catch (IOException e) {
                    e.printStackTrace();
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
        new AlertDialog.Builder(Diapo.this)
                .setTitle(titre)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
    }


}
