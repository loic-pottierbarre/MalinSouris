package fr.univamu.iut.malinsouris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by lpott on 06/12/2017.
 */

public class Gestion_principale extends Activity {

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
}
