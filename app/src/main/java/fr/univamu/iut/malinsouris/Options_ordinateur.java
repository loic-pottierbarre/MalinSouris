package fr.univamu.iut.malinsouris;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpott on 07/12/2017.
 */

public class Options_ordinateur extends Activity {

    TextView titreOptions = null;
    ListView choixOptions = null;
    Button defaultParam = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_ordinateur);

        Intent intent = getIntent();
        titreOptions = (TextView)findViewById(R.id.titreOptions);
        titreOptions.setText("Options de " + intent.getStringExtra(Gestion_principale.EXTRA_NOM_PC));

        ArrayList<String> menu = new ArrayList<>();
        menu.add("Changer la sensibilité");

        choixOptions = (ListView)findViewById(R.id.listeOptions);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Options_ordinateur.this, android.R.layout.simple_list_item_1, menu);
        choixOptions.setAdapter(adapter);

        choixOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Options_ordinateur.this, "Non implémenté", Toast.LENGTH_SHORT).show();
            }
        });

        defaultParam = (Button)findViewById(R.id.defaultParam);
        defaultParam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Options_ordinateur.this, "Non implémenté", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
