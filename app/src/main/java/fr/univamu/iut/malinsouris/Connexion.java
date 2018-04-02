package fr.univamu.iut.malinsouris;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
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

    private static   String adresse = "28:16:AD:BC:E9:D0";

    //INSTANCE
    private static Connexion INSTANCE;

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public static byte [] float2ByteArray (float value)
    {
        return ByteBuffer.allocate(4).putFloat(value).array();
    }

    private Connexion() {

        //this.adresse = adr;

        System.out.println("Je suis ici");
        btAdapter = BluetoothAdapter.getDefaultAdapter();

        BluetoothDevice PC = btAdapter.getRemoteDevice(adresse);

        try {
            System.out.println("PD");
            btSocket = PC.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }

        btAdapter.cancelDiscovery();

        try {
            System.out.println("YO");
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
            System.out.println("Héhé");
            outputStream = btSocket.getOutputStream();
            inputStream = btSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connexion getINSTANCE() {
        if(INSTANCE == null)
            INSTANCE = new Connexion();
        return INSTANCE;
    }

}
