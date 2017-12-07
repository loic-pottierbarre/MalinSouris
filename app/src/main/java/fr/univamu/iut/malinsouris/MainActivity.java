package fr.univamu.iut.malinsouris;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listeOrdis = null;
    Button addOrdi = null;
    TextView textSelection = null;
    static String EXTRA = "tgducul";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> ordinateurs = new ArrayList<>();
        ordinateurs.add("Ordinateur de Loïc");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, ordinateurs);
        listeOrdis = (ListView)findViewById(R.id.ListeOrdi);
        listeOrdis.setAdapter(adapter);
        listeOrdis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Gestion_principale.class);
                intent.putExtra(EXTRA, ordinateurs.get(i));
                startActivity(intent);
            }
        });

        textSelection = (TextView)findViewById(R.id.TexteSelection) ;

        addOrdi = (Button)findViewById(R.id.addOrdi) ;
        addOrdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Non implémenté", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
