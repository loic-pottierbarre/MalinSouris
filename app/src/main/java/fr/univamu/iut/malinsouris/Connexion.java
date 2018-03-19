package fr.univamu.iut.malinsouris;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by w16002657 on 19/03/18.
 */

public class Connexion {

    private static final UUID MY_UUID =  UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private BluetoothAdapter btAdapter;
    private BluetoothSocket btSocket;

    private OutputStream outputStream;
    private InputStream inputStream;

    private static String adresse;

    //INSTANCE
    private static Connexion INSTANCE = new Connexion();

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    private Connexion() {

        btAdapter = BluetoothAdapter.getDefaultAdapter();

        BluetoothDevice PC = btAdapter.getRemoteDevice(adresse);

        try {
            btSocket = PC.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }

        btAdapter.cancelDiscovery();

        try {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
                e.printStackTrace();
            } catch (IOException e1) {
                e.printStackTrace();
            }
        }

        try {
            outputStream = btSocket.getOutputStream();
            inputStream = btSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setAdresse(String adr) {
        adresse = adr;
    }

    public static Connexion getINSTANCE() {
        return INSTANCE;
    }

}
