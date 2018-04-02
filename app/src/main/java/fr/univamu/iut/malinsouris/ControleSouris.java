package fr.univamu.iut.malinsouris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.ConnectException;

/**
 * Created by lpott on 07/12/2017.
 */

public class ControleSouris extends Activity {
    TextView positionX = null;
    TextView positionY = null;
    Float ancienX = null;
    Float ancienY = null;
    Float deplacementX = null;
    Float deplacementY = null;

    Float x = null;
    byte [] xByte;
    Float y = null;
    byte [] yByte;
    RelativeLayout parentControleSouris = null;
    Button clicDroit;
    Button clicGauche;
    Button quitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controle_souris);

        positionX = (TextView)findViewById(R.id.positionX);
        positionY = (TextView)findViewById(R.id.positionY);
        positionX.setText("X = ");
        positionY.setText("Y = ");
        parentControleSouris = (RelativeLayout)findViewById(R.id.parentControleSouris);
        clicDroit = findViewById(R.id.clicDroit);
        clicGauche = findViewById(R.id.clicGauche);
        quitter = findViewById(R.id.bouttonQuitter);


        parentControleSouris.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                x = motionEvent.getX();
                positionX.setText("X = " + x.toString());

                y = motionEvent.getY();
                positionY.setText("Y = " + y.toString());


                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    deplacementX = null;
                    deplacementY = null;
                    ancienY = null;
                    ancienX = null;
                }

                if(motionEvent.getAction() != MotionEvent.ACTION_DOWN) {
                    if(ancienX != null) {
                        deplacementX = x - ancienX;
                        xByte = Connexion.float2ByteArray(deplacementX);
                        try {
                            System.out.println(deplacementX);
                            Connexion.getINSTANCE().getOutputStream().write(xByte);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if(ancienX != null) {
                        deplacementY = y - ancienY;
                        yByte = Connexion.float2ByteArray(deplacementY);
                        try {
                            System.out.println(deplacementY);
                            Connexion.getINSTANCE().getOutputStream().write(yByte);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

                ancienX = x;
                ancienY = y;
                return true;
            }
        });

        clicDroit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Float toSend = Float.valueOf(20000);
                    Connexion.getINSTANCE().getOutputStream().write(Connexion.float2ByteArray(toSend));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        clicGauche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Float toSend = Float.valueOf(10000);
                    Connexion.getINSTANCE().getOutputStream().write(Connexion.float2ByteArray(toSend));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Float toSend = Float.valueOf(30000);
                    Connexion.getINSTANCE().getOutputStream().write(Connexion.float2ByteArray(toSend));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(ControleSouris.this, Gestion_principale.class);
                startActivity(intent);
            }
        });
    }
}
