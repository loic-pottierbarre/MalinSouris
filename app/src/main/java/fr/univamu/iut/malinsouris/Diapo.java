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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diapo);

        avancer = (Button)findViewById(R.id.Avancer);
        revenir = (Button)findViewById(R.id.Revenir);
        quitter = (Button)findViewById(R.id.QuitterDiapo);



        avancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Connexion.getINSTANCE().getOutputStream().write(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        revenir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Connexion.getINSTANCE().getOutputStream().write(2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                   Connexion.getINSTANCE().getOutputStream().write(3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }



}
