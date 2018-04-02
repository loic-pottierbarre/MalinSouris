package fr.univamu.iut.malinsouris;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
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
import java.util.Set;

public class MainActivity extends FragmentActivity {

    ListView listeOrdis = null;
    Button addOrdi = null;
    TextView textSelection = null;
    private Set<BluetoothDevice> appareilsConnus;
    private final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Toast.makeText(MainActivity.this, "New Device = " + device.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Si le Bluetooth est disponnible sur le téléphone, on l'active automatiquement.
        if (bluetoothAdapter == null) {
            Toast.makeText(MainActivity.this, "Pas de Bluetooth",
                    Toast.LENGTH_SHORT).show();
        } else {

            if (!bluetoothAdapter.isEnabled()) {
                bluetoothAdapter.enable();
            }
        }

        //On récupère la liste des appareils connus, puis on l'affiche
        appareilsConnus = bluetoothAdapter.getBondedDevices();


        final ArrayList<String> ordinateursAdresse = new ArrayList<>();
        final ArrayList<String> ordinateurs = new ArrayList<>();
        for (BluetoothDevice blueDevice : appareilsConnus)
        {
            ordinateursAdresse.add(blueDevice.getAddress());
            ordinateurs.add(blueDevice.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, ordinateurs);
        listeOrdis = (ListView)findViewById(R.id.ListeOrdi);
        listeOrdis.setAdapter(adapter);
        listeOrdis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Gestion_principale.class);
                intent.putExtra("NOM", ordinateurs.get(i));
                intent.putExtra("ADRESSE", ordinateursAdresse.get(i));
                startActivity(intent);
            }
        });


        //TEST BLUETOOTH
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(bluetoothReceiver, filter);


        textSelection = (TextView)findViewById(R.id.TexteSelection) ;

        addOrdi = (Button)findViewById(R.id.addOrdi) ;
        addOrdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast.makeText(MainActivity.this, "Non.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bluetoothAdapter.cancelDiscovery();
        unregisterReceiver(bluetoothReceiver);
    }
}
