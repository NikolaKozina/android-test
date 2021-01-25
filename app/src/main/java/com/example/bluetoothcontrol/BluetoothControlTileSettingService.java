package com.example.bluetoothcontrol;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.widget.Toast;

public class BluetoothControlTileSettingService extends TileService {

    SharedPreferences mSharedPreferences;

    @Override
    public void onClick() {
        super.onClick();
        Tile tile = getQsTile();
        boolean isActive = (tile.getState() == Tile.STATE_ACTIVE);
        tile.updateTile();
    }
    @Override
    public void onTileAdded() {
        super .onTileAdded();
        Tile tile = getQsTile();
        tile.setState(Tile.STATE_ACTIVE);
        tile.setLabel("TEST");
        tile.updateTile();
        Toast.makeText(getApplicationContext(),"tile added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
    }

    @Override
    public void onStartListening() {
        super.onStartListening();

        Tile tile = getQsTile();

        if (tile.getState() == Tile.STATE_ACTIVE) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            int counter = mSharedPreferences.getInt("counter", 0);

            mSharedPreferences.edit().putInt("counter", ++counter).apply();
            tile.setLabel("Count " + counter);
        }

        tile.updateTile();

    }


    @Override
    public void onStopListening() {
        super.onStopListening();
    }
}
