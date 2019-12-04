package edu.alsjava.courses.demoluis.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.util.ArrayList;
import java.util.List;

/**
 * Patrón Singleton
 * <p>
 * Los dispositivos deben ser configurados a mano para el OS para que puedan verse en la aplicacion.
 * <p>
 * Esta clase no maneja el Bluetooth completamente, prenderlo y apagarlo es responsabilidad del usuario.
 * <p>
 * Created by aluis on 12/3/19.
 */
public class BluetoothPrinterManager {

    private static volatile BluetoothPrinterManager instance = null;

    private final BluetoothAdapter bluetoothAdapter;

    private BluetoothPrinterManager() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public static BluetoothPrinterManager get() {
        BluetoothPrinterManager result = instance;
        if (result == null) {
            synchronized (BluetoothPrinterManager.class) {
                if (instance == null) {
                    instance = result = new BluetoothPrinterManager();
                }
            }
        }
        return result;
    }

    public boolean hasBluetooth() {
        return bluetoothAdapter != null;
    }

    public boolean isEnable() {
        if (hasBluetooth()) {
            return bluetoothAdapter.isEnabled();
        }
        return false;
    }

    public List<BluetoothDevice> getAllAdapters() {
        return new ArrayList<>(bluetoothAdapter.getBondedDevices());
    }

    public BluetoothDevice getAdapter(String adapterAddress) {
        for (BluetoothDevice bondedDevice : bluetoothAdapter.getBondedDevices()) {
            if (bondedDevice.getAddress().equals(adapterAddress)) {
                return bondedDevice;
            }
        }
        return null;
    }

    public void printDevices() {
        for (BluetoothDevice bluetoothDevice : getAllAdapters()) {
            System.out.println(String.format("Name: %s -- Address %s", bluetoothDevice.getName(), bluetoothDevice.getAddress()));
        }
    }
}
